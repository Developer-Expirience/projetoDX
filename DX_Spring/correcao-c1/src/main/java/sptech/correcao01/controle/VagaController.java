package sptech.correcao01.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.correcao01.ListaObj;
import sptech.correcao01.dominio.Usuario;
import sptech.correcao01.dominio.Vaga;
import sptech.correcao01.repositorio.VagaRepository;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    /*
        {
        "idVaga": 0,
        "descricao": "",
        "valor": 0.00,
        "senioridade": "",
        "titulo": "",
        "tecnologia": "",
        "tempEstimado": 0
        }
     */

    @Autowired
    private VagaRepository repository;

    private  int contador;


    @PostMapping
    public ResponseEntity<Vaga> post(
            @RequestBody Vaga novoVaga) {
        contador++;
        Vaga v = novoVaga;
//        v.setIdVaga(contador);
        ListaObj<Vaga> lista = new ListaObj<>(getContador());

        lista.adiciona(v);
//        ArqCsvVaga.gravaArquivoCsv(lista,"vagas");
        repository.save(novoVaga);// faz um insert ou update, dependendo de a chave primária existe ou não no banco
        return ResponseEntity.status(201).body(novoVaga);
    }

    @GetMapping
    public ResponseEntity<List<Vaga>> get() {
        List<Vaga> lista = repository.findAll(); // faz um "select * from" da tabela
       // ArqCsvVaga.leExibeArquivoCsv("vagas");
        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{idVaga}")
    public ResponseEntity<Vaga> get(
            @PathVariable int idVaga) {
        return ResponseEntity.of(repository.findById(idVaga));
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
    public ResponseEntity<Vaga> put(
            @PathVariable int id, @RequestBody Vaga vaga) {
        if (repository.existsById(id)) {
//            vaga.setIdVaga(id);
            repository.save(vaga); // faz um "update" pois o id existe
            return ResponseEntity.status(200).body(vaga);
        }
        return ResponseEntity.status(404).build();
    }
    public int getContador() {
        return contador;
    }
//  vagas/procurar-salario?minSalario=2000&maxSalario=5000
    @GetMapping("/procurar-salario")
    public ResponseEntity <List<Vaga>> procurarSalario(@RequestParam(defaultValue = "0") Double minSalario, @RequestParam(defaultValue = "1000000000000") Double maxSalario){
        List<Vaga> result = repository.findBySalarioBetween(minSalario,maxSalario);
        return ResponseEntity.status(200).body(result);
    }
    @GetMapping("/procurar-senioridade/{senioridade}")
    public ResponseEntity<List<Vaga>> getPorSenioridade(@PathVariable String senioridade){
        List<Vaga> vagasSenioridade = repository.findBySenioridade(senioridade);
        return vagasSenioridade.isEmpty()?ResponseEntity.status(204).build():ResponseEntity.status(200).body(vagasSenioridade);
    }
    @GetMapping("/procurar-tecnologia/{tecnologia}")
    public ResponseEntity<List<Vaga>> getPorTecnologia(@PathVariable String tecnologia){
        List<Vaga> vagasTecnologia = repository.findByTecnologiaContaining(tecnologia);
        return vagasTecnologia.isEmpty()?ResponseEntity.status(204).build():ResponseEntity.status(200).body(vagasTecnologia);
    }
    @GetMapping("/procurar-titulo/{titulo}")
    public ResponseEntity<List<Vaga>> getPorTitulo(@PathVariable String titulo){
        List<Vaga> vagasTitulo = repository.findByTituloContaining(titulo);
        return vagasTitulo.isEmpty()?ResponseEntity.status(204).build():ResponseEntity.status(200).body(vagasTitulo);
    }
}
