package sptech.correcao01.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.correcao01.dominio.Empresa;


import java.util.List;


public interface EmpresaRepository extends
            JpaRepository<Empresa, Integer> {
    }

