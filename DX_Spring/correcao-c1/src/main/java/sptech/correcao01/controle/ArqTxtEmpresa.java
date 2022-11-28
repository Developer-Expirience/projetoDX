package sptech.correcao01.controle;

import org.springframework.beans.factory.annotation.Autowired;
import sptech.correcao01.dominio.Empresa;
import sptech.correcao01.repositorio.EmpresaRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArqTxtEmpresa {

    @Autowired
    private static EmpresaRepository repository;
    
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
    
            public static void gravaArquivoTxt(List<Empresa> lista, String nomeArq) {
                int contaRegDados = 0;
    
                // Monta o registro de header
                String header = "EMPRESA";
                header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                header += "01";
    
                // Grava o registro de header
                gravaRegistro(header, nomeArq);
    
                // Monta e grava os registros de corpo (ou de dados)
                String corpo;
                for (Empresa empresa : lista) {
                    corpo = "02";
                    corpo += String.format("%06d", empresa.getIdEmpresa());
                    corpo += String.format("%-15.15s", empresa.getNome());
                    corpo += String.format("%-15.15s", empresa.getUsuario());
                    corpo += String.format("%-23.23s", empresa.getEmail());
                    corpo += String.format("%-18.18s", empresa.getCnpj());
                    corpo += String.format("%-13.13s", empresa.getTelefone());
                    corpo += String.format("%08d", empresa.getNumFuncionario());
                    corpo += String.format("%-20.20s", empresa.getRua());
                    corpo += String.format("%04d", empresa.getNumero());
                    corpo += String.format("%-8.8s", empresa.getCep());
                    corpo += String.format("%-20.20s", empresa.getBairro());
                    corpo += String.format("%-10.10s", empresa.getCidade());
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
                String registro, tipoRegistro;
                String nome, usuario, email, cnpj, telefone, cep, rua, bairro, cidade;
                Integer  idEmpresa, numFuncionario, numero;
                int contaRegDadoLido = 0;
                int qtdRegDadoGravado;
    
                List<Empresa> listaLida = new ArrayList();
    
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
                            idEmpresa = Integer.valueOf(registro.substring(2, 8));
                            nome = registro.substring(8, 23).trim();
                            usuario = registro.substring(23, 38).trim();
                            email = registro.substring(38, 61).trim();
                            cnpj = registro.substring(61, 79).trim();
                            telefone = registro.substring(79, 92).trim();
                            numFuncionario = Integer.valueOf(registro.substring(92, 100));
                            rua = registro.substring(100, 120).trim();
                            numero = Integer.valueOf(registro.substring(120,124));
                            cep = registro.substring(124, 132).trim();
                            bairro = registro.substring(132, 152).trim();
                            cidade = registro.substring(152, 162).trim();
                            contaRegDadoLido++;
    
                            // Instancia um objeto Empresa com as informações lidas
                            Empresa empresa = new Empresa( idEmpresa, nome, usuario, email, cnpj, telefone,
                                    numFuncionario, rua, numero, cep, bairro, cidade);
    
                            // No Projeto de PI, pode fazer
                                repository.save(empresa);

                            // No nosso caso, como não estamos conectados ao banco
                            // vamos adicionar o objeto a na listaLida
                            listaLida.add(empresa);
    
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
                for (Empresa a : listaLida) {
                    System.out.println(a);
                }
    
            }
    
    
    
            public static void main(String[] args) {
                List<Empresa> lista = new ArrayList();
    
                lista.add(new Empresa( 106714,"João Victor", "JV",
                        "Estrutura.de.Dados@gmail", "03.733.596/0001-27", "11 91111-1111",
                        10000600, "Rua Primeiro de Setembro", 2456, "20202099", "Gru", "Rio"));
                lista.add(new Empresa( 123456,"Erick Cardazo", "EC",
                        "pw.de.sp@gmail", "03.733.596/0001-27", "11 91111-1111",
                        10000600, "Ruapprimerihotyrhcop",3456, "20202099", "Gru", "Rio"));
    
                System.out.println("Lista original:");
                for (Empresa empresa : lista) {
                    System.out.println(empresa);
                }
    
                gravaArquivoTxt(lista, "Empresas.txt");
                //leArquivoTxt("Empresas.txt");
        }

}
