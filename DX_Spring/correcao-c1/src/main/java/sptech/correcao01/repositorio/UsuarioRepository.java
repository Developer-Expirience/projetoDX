package sptech.correcao01.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.correcao01.dominio.Usuario;



    public interface UsuarioRepository extends
            JpaRepository<Usuario, Integer> {

    }

