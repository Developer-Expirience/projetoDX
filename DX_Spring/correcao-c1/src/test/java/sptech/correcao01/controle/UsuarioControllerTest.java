package sptech.correcao01.controle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import sptech.correcao01.repositorio.EmpresaRepository;
import sptech.correcao01.repositorio.UsuarioRepository;
import sptech.correcao01.repositorio.VagaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UsuarioController.class)
class UsuarioControllerTest {

    @MockBean
    UsuarioRepository repository;

    @MockBean
    EmpresaRepository empresaRepository;

    @MockBean
    VagaRepository vagaRepository;

    @Autowired
    UsuarioController controller;


    @Test
    @DisplayName("deletando usuário com id inválido")
    void delete() {
        int idTeste = 51;

        when(repository.existsById(idTeste)).thenReturn(false);

        ResponseEntity<> resposta = controller.delete(idTeste);

        assertEquals(404, resposta.getStatusCodeValue());

    }
}