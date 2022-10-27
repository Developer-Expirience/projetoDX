package sptech.correcao01.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.correcao01.dominio.Vaga;

import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Integer> {
    //get by id
        List<Vaga> findByIdVaga(int idVaga);
    //get by senioridade
        List<Vaga> findBySenioridade(String senioridade);
    //get by tecnologia
        List<Vaga> findByTecnologia(String tecnologia);
    // range do salario
    //
}
