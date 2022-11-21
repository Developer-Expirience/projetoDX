package sptech.correcao01.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.correcao01.dominio.Vaga;
import sptech.correcao01.dominio.VagaEmpresaUsuario;

import java.util.List;

public interface VagaEmpresaUsuarioRepository extends JpaRepository<VagaEmpresaUsuario, Integer> {

    List<VagaEmpresaUsuario> findByUsuarioIdUsuario(Integer id);

}
