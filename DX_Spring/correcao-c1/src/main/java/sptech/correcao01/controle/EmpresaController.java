package sptech.correcao01.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.correcao01.ListaObj;
import sptech.correcao01.dominio.Empresa;
import sptech.correcao01.dominio.Usuario;
import sptech.correcao01.repositorio.EmpresaRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {


    @Autowired
    private EmpresaRepository repository;
    private int contador;
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
        ArqCsvEmpresa.leExibeArquivoCsv("empresas");
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

    public int getContador() {
        return contador;
    }
}
