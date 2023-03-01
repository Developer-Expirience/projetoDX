package sptech.correcao01.controle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import sptech.correcao01.ListaObj;
import sptech.correcao01.dominio.Empresa;
import sptech.correcao01.dominio.FileInfo;
import sptech.correcao01.dominio.ImportacaoService;
import sptech.correcao01.dominio.Usuario;
import sptech.correcao01.repositorio.EmpresaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private ImportacaoService service;

    @Autowired
    private EmpresaRepository repository;
    private int contador;

    private final ClasseTeste csv = new ClasseTeste();
    @PostMapping
    public ResponseEntity<Empresa> post(
            @RequestBody Empresa novaEmpresa) {
        contador++;
        novaEmpresa.setIdEmpresa(contador);
        ListaObj<Empresa> lista = new ListaObj<>(getContador());
        lista.adiciona(novaEmpresa);
        ArqCsvEmpresa.gravaArquivoCsv(lista,"empresas");
        repository.save(novaEmpresa); // faz um insert ou update, dependendo de a chave primária existe ou não no banco
        return ResponseEntity.status(201).body(novaEmpresa);
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> get() {
        List<Empresa> lista = repository.findAll(); // faz um "select * from" da tabela
//        ArqCsvEmpresa.leExibeArquivoCsv("empresas");
        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> get(
            @PathVariable int id) {
/*
Se o findById() encontrar valor, ele será usado no corpo da resposta e o status da resposta será 200
Caso contrário, o status da resposta será 404 e não haverá corpo na resposta
 */
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable int id) {
/*
O existsById() faz um "select count(*)..." para saber se o id existe na tabela
 */
        if (repository.existsById(id)) {
            repository.deleteById(id);
// O deleteById() faz um "delete from... where id=..."
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> put(
            @PathVariable int id, @RequestBody Empresa empresa) {
        if (repository.existsById(id)) {
            empresa.setIdEmpresa(id);
            repository.save(empresa); // faz um "update" pois o id existe
            return ResponseEntity.status(200).body(empresa);
        }
        return ResponseEntity.status(404).build();
    }
    @PostMapping("/login/{empresa}/{senha}")
    public ResponseEntity postLogin(@PathVariable String empresa, @PathVariable String senha){
        List<Empresa> lista = repository.findAll();
        for (Empresa e: lista){
            if (e.getEmpresaAutenticado(empresa, senha)){
                e.setEmpresaValidado(true);
                repository.save(e);
                return ResponseEntity.status(200).body(true);
            }
        }
        return ResponseEntity.status(404).body(false);
    }

    @PostMapping("/logoff/{empresa}")
    public ResponseEntity postLogoff(@PathVariable String empresa){
        List<Empresa> lista = repository.findAll();
        for (Empresa e: lista){
            if (e.isEmpresaValidado()){
                e.setEmpresaValidado(false);
                repository.save(e);
                return ResponseEntity.status(200).body(e.isEmpresaValidado());
            }
        }
        return ResponseEntity.status(404).build();
    }
    @GetMapping("/empresa-logado")
    public ResponseEntity getEmpresaLogado(@RequestParam(required = false) String empresa){
        List<Empresa> lista = repository.findAll();
        List<Empresa> listaLogado = new ArrayList<>();
        if (!(empresa == null)){
            for (Empresa e: lista){
                if (empresa.equals(e.getUsuario())){
                    return ResponseEntity.status(200).body(e);
                }
            }
        }else {
            for (Empresa e: lista){
                if (e.isEmpresaValidado()){
                    listaLogado.add(e);
                }
            }
        }
        return listaLogado.isEmpty()? ResponseEntity.status(404).build() :ResponseEntity.status(200).body(listaLogado);
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            service.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body((message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body((message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = service.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(EmpresaController.class, "getFile", path.getFileName().toString()).build().toString();

            System.out.println(url);
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = service.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    public int getContador() {
        return contador;
    }
}
