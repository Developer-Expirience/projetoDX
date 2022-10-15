package sptech.correcao01.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.correcao01.dominio.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Integer> {
}
