package sptech.correcao01.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UsuarioRepository extends
            JpaRepository<Usuario, Integer> {

    }

