package sptech.correcao01.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.correcao01.dominio.Usuario;
import sptech.correcao01.dominio.Vaga;

import java.util.List;


public interface UsuarioRepository extends
            JpaRepository<Usuario, Integer> {
        //get by id
        List<Vaga> findByIdUsuario(int idUsuario);
    }

