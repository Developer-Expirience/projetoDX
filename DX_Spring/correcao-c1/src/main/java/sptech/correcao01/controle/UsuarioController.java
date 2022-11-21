package sptech.correcao01.controle;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.correcao01.ListaObj;
import sptech.correcao01.dominio.*;
import sptech.correcao01.repositorio.EmpresaRepository;
import sptech.correcao01.repositorio.UsuarioRepository;
import sptech.correcao01.repositorio.VagaEmpresaUsuarioRepository;
import sptech.correcao01.repositorio.VagaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    /*
    */

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private VagaEmpresaUsuarioRepository vagaEmpresaUsuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private VagaRepository vagaRepository;

    private int contador;

    private List<Usuario> usuarios;

    private PilhaObj<Integer> pilhaDesfazer;
    private FilaObj<Integer> filaVagasDoUsuario;
    private FilaObj<Integer> filaDeCandidatos;

    public UsuarioController() {
        this.usuarios = new ArrayList<>();
        this.pilhaDesfazer = new PilhaObj<>(10);
        this.filaVagasDoUsuario = new FilaObj<>(10);
        this.filaDeCandidatos = new FilaObj<>(10);
    }

    @PostMapping
    public ResponseEntity<Usuario> post(
            @RequestBody Usuario novoUsuario) {
        contador++;
//        Usuario u = novoUsuario;
        novoUsuario.setIdUsuario(contador);
        ListaObj<Usuario> lista = new ListaObj<>(getContador());
        lista.adiciona(novoUsuario);
        ArqCsvUsuario.gravaArquivoCsv(lista,"usuarios");
        usuarios.add(novoUsuario);
        repository.save(novoUsuario);// faz um insert ou update, dependendo de a chave primária existe ou não no banco
        return ResponseEntity.status(201).body(novoUsuario);

    }

    @PostMapping("/login/{usuario}/{senha}")
    public ResponseEntity postLogin(@PathVariable String usuario, @PathVariable String senha){
        List<Usuario> lista = repository.findAll();
        for (Usuario u: lista){
            if (u.getUsuarioAutenticado(usuario, senha)){
                u.setUsuarioValidado(true);
                repository.save(u);
                return ResponseEntity.status(200).body(true);
            }
        }
        return ResponseEntity.status(404).body(false);
    }

    @PostMapping("/logoff/{usuario}")
    public ResponseEntity postLogoff(@PathVariable String usuario){
        List<Usuario> lista = repository.findAll();
        for (Usuario u: lista){
            if (u.isUsuarioValidado()){
                u.setUsuarioValidado(false);
                repository.save(u);
                return ResponseEntity.status(200).body(u.isUsuarioValidado());
            }
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/usuario-logado")
    public ResponseEntity getUsuarioLogado(@RequestParam(required = false) String usuario){
        List<Usuario> lista = repository.findAll();
        List<Usuario> listaLogado = new ArrayList<>();
        if (!(usuario == null)){
            for (Usuario u: lista){
                if (usuario.equals(u.getUsuario())){
                    return ResponseEntity.status(200).body(u);
                }
            }
        }else {
            for (Usuario u: lista){
                if (u.isUsuarioValidado()){
                    listaLogado.add(u);
                }
            }
        }
        return listaLogado.isEmpty()? ResponseEntity.status(404).build() :ResponseEntity.status(200).body(listaLogado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> get() {
        List<Usuario> lista = repository.findAll(); // faz um "select * from" da tabela
        ArqCsvUsuario.leExibeArquivoCsv("usuarios");
        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> get(
            @PathVariable int id) {
/*
Se o findById() encontrar valor, ele será usado no corpo da resposta e o status da resposta será 200
Caso contrário, o status da resposta será 404 e não haverá corpo na resposta
 */
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping("/candidatar-vaga")
    public ResponseEntity canditarAVaga(@RequestBody VagaEmpresaUsuario vagaEmpresaUsuario){
        //Vaga vaga, VagaId vagaId

        if (!vagaRepository.existsById(vagaEmpresaUsuario.getVaga().getId())){
            return ResponseEntity.status(404).body("Id da vaga não existe");
        }

        if (!repository.existsById(vagaEmpresaUsuario.getUsuario().getIdUsuario())){
            return ResponseEntity.status(404).body("Id da vaga não existe");
        }

        if (!empresaRepository.existsById(vagaEmpresaUsuario.getEmpresa().getIdEmpresa())){
            return ResponseEntity.status(404).body("Id da vaga não existe");
        }
        filaVagasDoUsuario.insert(vagaEmpresaUsuario.getVaga().getId());
        filaDeCandidatos.insert(vagaEmpresaUsuario.getUsuario().getIdUsuario());
        pilhaDesfazer.push(vagaEmpresaUsuario.getVaga().getId());
        return ResponseEntity.status(201).body(vagaEmpresaUsuarioRepository.save(vagaEmpresaUsuario));

    }

    @GetMapping("/vagas/{idUsuario}")
    public ResponseEntity<List<VagaEmpresaUsuario>> getVagasPorUsuario(@PathVariable Integer idUsuario){

        return repository.existsById(idUsuario)
                ? ResponseEntity.status(200).body(vagaEmpresaUsuarioRepository.findByUsuarioIdUsuario(idUsuario))
                : ResponseEntity.status(404).build();
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
    public ResponseEntity<Usuario> put(
            @PathVariable int id, @RequestBody Usuario usuario) {
        if (repository.existsById(id)) {
            usuario.setIdUsuario(id);
            repository.save(usuario); // faz um "update" pois o id existe
            return ResponseEntity.status(200).body(usuario);
        }
        return ResponseEntity.status(404).build();
    }
    public int getContador() {
        return contador;
    }

}


