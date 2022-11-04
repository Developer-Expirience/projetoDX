package sptech.correcao01.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.correcao01.dominio.Vaga;

import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Integer> {
    //get by Titulo Vaga
        List<Vaga> findByTituloContaining(String titulo);
    //get by senioridade
        List<Vaga> findBySenioridade(String senioridade);
    //get by tecnologia
        List<Vaga> findByTecnologiaContaining(String tecnologia);

    // range do salario
    //
       @Query("SELECT obj FROM Vaga obj WHERE obj.valor >= :minSalario AND obj.valor <= :maxSalario")
        List<Vaga> findBySalarioBetween(Double minSalario, Double maxSalario);
}
