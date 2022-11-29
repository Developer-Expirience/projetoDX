package sptech.correcao01.controle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import sptech.correcao01.dominio.Usuario;
import sptech.correcao01.repositorio.EmpresaRepository;
import sptech.correcao01.repositorio.UsuarioRepository;
import sptech.correcao01.repositorio.VagaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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
    @DisplayName("delete usuário com id inválido retorna 404")
    void deleteComIdInvalidoRetorna404() {
        int idTeste = 51;

        when(repository.existsById(idTeste)).thenReturn(false);

        ResponseEntity<Void> resposta = controller.delete(idTeste);

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());

    }

    @Test
    @DisplayName("delete usuário com id válido retorna 200")
    void deleteComIdValidoRetorna200() {
        Integer idTeste = 11;
        when(repository.existsById(idTeste)).thenReturn(true);

        ResponseEntity<Void> resposta = controller.delete(idTeste);

        verify(repository, times(1)).deleteById(idTeste);
        assertEquals(200, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());

    }

    @Test
    @DisplayName("atualização com id inválido retorna 404")
    void putComIdInvalidoRetorna404() {
        int idTeste = 51;
        when(repository.existsById(idTeste)).thenReturn(false);

        ResponseEntity<Usuario> resposta = controller.put(idTeste, mock(Usuario.class));

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("atualização com id válido retorna 200")
    void putComIdValidoRetorna200() {
        int idTeste = 1;
        Usuario usuarioTeste = new Usuario();
        when(repository.existsById(idTeste)).thenReturn(true);

        ResponseEntity<Usuario> resposta = controller.put(idTeste, usuarioTeste);

        verify(repository, times(1)).save(usuarioTeste);
        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        assertEquals(idTeste, resposta.getBody().getIdUsuario());
    }

    @Test
    @DisplayName("post com usuário válido retorna 201")
    void postComUsuarioValido() {
        Usuario usuario = mock(Usuario.class);

        when(repository.save(usuario)).thenReturn(usuario);

        ResponseEntity<Usuario> resposta = controller.post(usuario);

        assertEquals(201, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());

    }

    @Test
    @DisplayName("get usuario com id inválido retorna 404")
    void getComIdInvalidoRetorna404() {
        int idTeste = 51;

        when(repository.findById(idTeste)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> resposta = controller.get(idTeste);

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());

    }

    @Test
    @DisplayName("get usuario com id válido retorna 200")
    void getComIdValidoRetorna200() {
        int idTeste = 1;

        when(repository.findById(idTeste)).thenReturn(Optional.of(mock(Usuario.class)));

        ResponseEntity<Usuario> resposta = controller.get(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());

    }

    @Test
    @DisplayName("post login passando usuario inválido retorna 404")
    void postLoginPassandoUsuarioInvalidoRetorna404() {
        String usuarioTeste = "usuarioErrado";
        String senhaTeste = "senhaCerta";
        boolean errado = false;

        ResponseEntity resposta = controller.postLogin(usuarioTeste, senhaTeste);

        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals(errado, resposta.getBody());

    }

    @Test
    @DisplayName("post login passando usuario válido e senha inválida retorna 404")
    void postLoginPassandoSenhaInvalidaRetorna404() {
        Usuario usuario = mock(Usuario.class);
        String senhaTeste = "senhaCerta";
        boolean errado = false;

        ResponseEntity resposta = controller.postLogin(usuario.getUsuario(), senhaTeste);

        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals(errado, resposta.getBody());

    }

}