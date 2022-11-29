package sptech.correcao01.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import sptech.correcao01.dominio.Vaga;
import sptech.correcao01.repositorio.VagaRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArqTxtVaga {

    @Autowired
    private static VagaRepository repository;
    
            public static void gravaRegistro(String registro, String nomeArq) {
                BufferedWriter saida = null;
    
                // try-catch para abrir o arquivo
                try {
                    saida = new BufferedWriter(new FileWriter(nomeArq,true));
                }
                catch (IOException erro) {
                    System.out.println("Erro ao abrir o arquivo");
                    erro.printStackTrace();
                }
    
                // try-catch para gravar e fechar o arquivo
                try {
                    saida.append(registro + "\n");
                    saida.close();
                }
                catch (IOException erro) {
                    System.out.println("Erro ao gravar o arquivo");
                    erro.printStackTrace();
                }
            }
    
            public static void gravaArquivoTxt(List<Vaga> lista, String nomeArq) {
                int contaRegDados = 0;
    
                // Monta o registro de header
                String header = "VAGA";
                header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                header += "01";
    
                // Grava o registro de header
                gravaRegistro(header, nomeArq);
    
                // Monta e grava os registros de corpo (ou de dados)
                String corpo;
                for (Vaga vaga : lista) {
                    corpo = "02";
                    corpo += String.format("%06d", vaga.getId());
                    corpo += String.format("%-25.25s", vaga.getTitulo());
                    corpo += String.format("%6.2f", vaga.getValor());
                    corpo += String.format("%4d", vaga.getTempEstimado());
                    gravaRegistro(corpo, nomeArq);
                    contaRegDados++;
                }
    
                // Monta e grava o registro de trailer
                String trailer = "01";
                trailer += String.format("%010d", contaRegDados);
    
                gravaRegistro(trailer, nomeArq);
            }
    
            public static void leArquivoTxt(String nomeArq) {
                BufferedReader entrada = null;
                Double valor;
                String tipoRegistro, registro, descricao;
                Integer idVaga, tempEstimado;
                int contaRegDadoLido = 0;
                int qtdRegDadoGravado;
    
                List<Vaga> listaLida = new ArrayList();
    
                // try-catch para abrir o arquivo
                try {
                    entrada = new BufferedReader(new FileReader(nomeArq));
                }
                catch (IOException erro) {
                    System.out.println("Erro ao abrir o arquivo");
                    erro.printStackTrace();
                }
    
                // try-catch para ler e fechar o arquivo
                try {
                    // Leitura o 1o registro do arquivo
                    registro = entrada.readLine();
    
                    while (registro != null) {
                        // Obtém os 2 primeiros caracteres do registro
                        // 1234567890
                        // 01234567890
                        // 00NOTA20222
                        // substring - 1o arg = índice do ínicio do que eu quero obter
                        // substring - 2o arg = índice do final do que eu quero + 1
                        tipoRegistro = registro.substring(0, 2);
                        if (tipoRegistro.equals("00")) {
                            System.out.println("Registro de header");
                            System.out.println("Tipo de arquivo: " + registro.substring(2, 6));
                            System.out.println("Tipo de registro: " + registro.substring(6, 8));
                            System.out.println("Data e hora da gravação: " + registro.substring(8, 27));
                            System.out.println("Versão do documento: " + registro.substring(27, 29));
                        }
                        else if (tipoRegistro.equals("01")) {
                            System.out.println("Registro de trailer");
                            qtdRegDadoGravado = Integer.parseInt(registro.substring(2, 12));
                            System.out.println("Quantidade de reg de dados lidos: " + contaRegDadoLido);
                            System.out.println("Quantidade de reg de dados gravados: " + qtdRegDadoGravado);
                            if (contaRegDadoLido == qtdRegDadoGravado) {
                                System.out.println("Quantidade de registros de dados lidos compatível com "
                                        + "quantidade de registros de dados gravados");
                            }
                            else {
                                System.out.println("Quantidade de registros de dados lidos incompatível com "
                                        + "quantidade de registros de dados gravados");
                            }
                        }
                        else if (tipoRegistro.equals("02")) {
                            System.out.println("Registro de corpo");
                            idVaga = Integer.valueOf(registro.substring(2, 8));
                            descricao = registro.substring(8, 33).trim();
                            valor = Double.valueOf(registro.substring(33, 39).replace(',','.'));
                            tempEstimado = Integer.valueOf(registro.substring(39, 43).trim());
                            contaRegDadoLido++;
    
                            // Instancia um objeto vaga com as informações lidas
                            Vaga vaga = new Vaga(idVaga, descricao, valor, tempEstimado);
    
                            // No Projeto de PI, pode fazer
                            repository.save(vaga);
    
                            // No nosso caso, como não estamos conectados ao banco
                            // vamos adicionar o objeto a na listaLida
                            listaLida.add(vaga);
    
                        }
                        else {
                            System.out.println("Tipo de registro inválido!");
                        }
    
                        // Lê o próximo registro
                        registro = entrada.readLine();
                    }
                    // Fecha o arquivo
                    entrada.close();
                }
                catch (IOException erro) {
                    System.out.println("Erro ao ler o arquivo");
                    erro.printStackTrace();
                }
    
                // Exibe a lista lida
                System.out.println("\nConteúdo da lista lida do arquivo:");
                for (Vaga vaga : listaLida) {
                    System.out.println(vaga);
                }
    
            }
    
    
    
            public static void main(String[] args) {
                List<Vaga> lista = new ArrayList();
    
                lista.add(new Vaga(200010, "Front-End React", 145.10, 8));
                lista.add(new Vaga(100010, "Back-End Java", 245.70, 10));
                lista.add(new Vaga(200230, "Banco de dados Sql", 210.50, 7));

    
                System.out.println("Lista original:");
                for (Vaga vaga : lista) {
                    System.out.println(vaga);
                }
    
                gravaArquivoTxt(lista, "vagas.txt");
                //leArquivoTxt("vagas.txt");
        }

}
