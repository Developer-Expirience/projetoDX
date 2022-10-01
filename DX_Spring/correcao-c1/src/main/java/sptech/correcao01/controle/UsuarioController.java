package sptech.correcao01.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.correcao01.dominio.Usuario;
import sptech.correcao01.repositorio.UsuarioRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @PostMapping
    public ResponseEntity<Usuario> post(
            @RequestBody Usuario novoUsuario) {
        repository.save(novoUsuario); // faz um insert ou update, dependendo de a chave primária existe ou não no banco
        return ResponseEntity.status(201).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> get() {
        List<Usuario> lista = repository.findAll(); // faz um "select * from" da tabela
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
}


