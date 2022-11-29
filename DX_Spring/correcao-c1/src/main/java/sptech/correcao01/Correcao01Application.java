package sptech.correcao01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sptech.correcao01.dominio.ImportacaoService;

import javax.annotation.Resource;

@SpringBootApplication
public class Correcao01Application implements CommandLineRunner {

	@Resource
	ImportacaoService storageService;

	public static void main(String[] args) {
		SpringApplication.run(Correcao01Application.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
//    storageService.deleteAll();
		storageService.init();
	}
}
