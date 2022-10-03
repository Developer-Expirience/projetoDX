package sptech.correcao01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sptech.correcao01.controle.ArqCsvUsuario;
import sptech.correcao01.controle.UsuarioController;
import sptech.correcao01.dominio.Usuario;

import java.util.ArrayList;

@SpringBootApplication
public class Correcao01Application {

	public static void main(String[] args) {
		UsuarioController usuarioController = new UsuarioController();
		ListaObj<Usuario> lista = new ListaObj<>(usuarioController.getContador());
		SpringApplication.run(Correcao01Application.class, args);
		ArqCsvUsuario.gravaArquivoCsv(lista, "usuarios");
		ArqCsvUsuario.leExibeArquivocsv("usuarios");
	}

}
