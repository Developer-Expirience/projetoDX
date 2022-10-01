package sptech.correcao01.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.correcao01.dominio.Empresa;



    public interface EmpresaRepository extends
            JpaRepository<Empresa, Integer> {
    }

