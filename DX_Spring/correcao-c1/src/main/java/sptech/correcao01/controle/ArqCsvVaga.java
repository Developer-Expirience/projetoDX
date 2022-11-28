package sptech.correcao01.controle;

import sptech.correcao01.ListaObj;
import sptech.correcao01.dominio.Empresa;
import sptech.correcao01.dominio.Vaga;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ArqCsvVaga {


    public static void gravaArquivoCsv(ListaObj<Vaga> lista, String nomeArq) {

        FileWriter arq = null; // objeto que represent ao arquivo de escrita
        Formatter saida = null; // objeto usado para escrever no arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv"; // acrescenta a extensão csv ao nome do arquivo

        try {
            arq = new FileWriter(nomeArq, true);
            saida = new Formatter(arq);

        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try-catch para gravar o arquivo
        try {
//            for (int i = 0; i < lista.getTamanho(); i++) {
//                Vaga vaga = lista.getElemento(i);
//                saida.format("%d;%s;%d;%.2f\n",
//                        vaga.getIdVaga(), vaga.getTitulo(),
//                        vaga.getTempEstimado(), vaga.getValor());
//            }

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

        // Bloco para ler o
        try {
            System.out.printf("%6S %-14S %11S %6S\n",
                    "idVaga", "descricao", "tempEstimado", "valor");

            while (entrada.hasNext()) {
                int idVaga = entrada.nextInt();
                String descricao = entrada.next();
                int tempEstimado = entrada.nextInt();
                Double valor = entrada.nextDouble();
                System.out.printf("%06d %-14s %11d %6.2f\n",
                        idVaga, descricao, tempEstimado, valor);
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

