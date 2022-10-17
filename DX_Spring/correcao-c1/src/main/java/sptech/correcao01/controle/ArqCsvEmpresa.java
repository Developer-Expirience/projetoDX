package sptech.correcao01.controle;

import sptech.correcao01.ListaObj;
import sptech.correcao01.dominio.Empresa;
import sptech.correcao01.dominio.Usuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ArqCsvEmpresa {


    public static void gravaArquivoCsv(ListaObj<Empresa> lista, String nomeArq) {

        FileWriter arq = null; // objeto que represent ao arquivo de escrita
        Formatter saida = null; // objeto usado para escrever no arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv"; // acrescenta a extensão csv ao nome do arquivo

        // Bloco try-catch para abri o arquivo

        try {
            arq = new FileWriter(nomeArq, true);
            saida = new Formatter(arq);

        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try-catch para gravar o arquivo
        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Empresa empresa = lista.getElemento(i);
                saida.format("%d;%s;%s;%s;%s;%s;%d;%s;%d;%s;%s;%s\n",
                        empresa.getIdEmpresa(), empresa.getNome(),
                        empresa.getUsuario(), empresa.getEmail(), empresa.getCnpj(),
                        empresa.getTelefone(), empresa.getNumFuncionario(),
                        empresa.getRua(), empresa.getNumero(), empresa.getCep(),
                        empresa.getBairro(), empresa.getCidade());
            }

        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leExibeArquivoCsv(String nomeArq) {
        FileReader arq = null; // objeto que represnet ao arquivo para leitura
        Scanner entrada = null; // objeto usado para ler do arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv";

        // Bloco try-catch para abri o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

        // Bloco para ler o arquivo
        try {
            System.out.printf("%-6S %-14S %-11S %14S %14S %14S %6S '%15S %6S %12S %13S %12S\n",
                    "idEmpresa", "nome", "usuario", "email", "cnpj", "telefone", "numFuncionarios", "rua", "numero",
                    "cep", "bairro", "cidade");

            while (entrada.hasNext()) {
                int idEmpresa = entrada.nextInt();
                String nome = entrada.next();
                String usuario = entrada.next();
                String email = entrada.next();
                String cnpj = entrada.next();
                String telefone = entrada.next();
                int numFuncionarios = entrada.nextInt();
                String rua = entrada.next();
                int numero = entrada.nextInt();
                String cep = entrada.next();
                String bairro = entrada.next();
                String cidade = entrada.next();
                System.out.printf("%06d %-14s %-11s %-14s %-14s %-14s %6d       %15s %6d %-12s %-13s %-12s\n",
                        idEmpresa, nome, usuario, email, cnpj, telefone, numFuncionarios, rua, numero, cep,
                        bairro, cidade);
            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
}

