package sptech.correcao01.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.correcao01.dominio.Vaga;
import sptech.correcao01.dominio.VagaEmpresaUsuario;

import java.util.List;

public interface VagaEmpresaUsuarioRepository extends JpaRepository<VagaEmpresaUsuario, Integer> {

    List<VagaEmpresaUsuario> findByUsuarioIdUsuario(Integer id);
    @Query("update VagaEmpresaUsuario set idUsuario=2 where id = ?1")
    void updateByIdVaga(Integer id);
    @Modifying
    @Query("delete from VagaEmpresaUsuario where usuario_id_usuario = ?1 and vaga_id = ?2")
    void deleteByIdUsuarioAndIdVaga(Integer id, Integer idVaga);
}
