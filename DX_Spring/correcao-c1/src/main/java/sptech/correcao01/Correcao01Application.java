package sptech.correcao01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sptech.correcao01.controle.ArqCsvUsuario;
import sptech.correcao01.controle.UsuarioController;
import sptech.correcao01.dominio.Empresa;
import sptech.correcao01.dominio.Usuario;
import sptech.correcao01.dominio.VagaId;

import java.util.ArrayList;

@SpringBootApplication
public class Correcao01Application {

	public static void main(String[] args) {
		SpringApplication.run(Correcao01Application.class, args);
	}
}
