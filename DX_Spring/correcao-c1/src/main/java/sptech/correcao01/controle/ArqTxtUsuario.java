package sptech.correcao01.controle;

import org.springframework.beans.factory.annotation.Autowired;
import sptech.correcao01.dominio.Usuario;
import sptech.correcao01.repositorio.UsuarioRepository;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArqTxtUsuario {

    @Autowired
    private static UsuarioRepository repository;

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

    public static void gravaArquivoTxt(List<Usuario> lista, String nomeArq) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "USUARIO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de corpo (ou de dados)
        String corpo;
        for (Usuario usuario : lista) {
            corpo = "02";
            corpo += String.format("%06d", usuario.getIdUsuario());
            corpo += String.format("%-15.15s", usuario.getUsuario());
            corpo += String.format("%-10.10s", usuario.getDataNascimento());
            corpo += String.format("%-15.15s", usuario.getNome());
            corpo += String.format("%-23.23s", usuario.getEmail());
            corpo += String.format("%-11.11s", usuario.getCpf());
            corpo += String.format("%-20.20s", usuario.getRua());
            corpo += String.format("%04d", usuario.getNumero());
            corpo += String.format("%-8.8s", usuario.getCep());
            corpo += String.format("%-20.20s", usuario.getBairro());
            corpo += String.format("%-10.10s", usuario.getCidade());
            corpo += String.format("%-20.20s", usuario.getComplemento());
            corpo += String.format("%-13.13s", usuario.getTelefone());
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
        String nome, userUsuario, email, telefone, cep, rua, bairro, cidade, dataNasc, complemento, cpf;
        Integer idUsuario, numero;
        int contaRegDadoLido = 0;
        int qtdRegDadoGravado;

        List<Usuario> listaLida = new ArrayList();

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
                    idUsuario = Integer.valueOf(registro.substring(2, 8));
                    userUsuario = registro.substring(8, 23).trim();
                    dataNasc = registro.substring(23, 33).trim();
                    nome = registro.substring(33, 48).trim();
                    email = registro.substring(48, 71).trim();
                    cpf = registro.substring(71, 82).trim();
                    rua = registro.substring(82, 102).trim();
                    numero = Integer.valueOf(registro.substring(102,106));
                    cep = registro.substring(106, 114).trim();
                    bairro = registro.substring(114, 134).trim();
                    cidade = registro.substring(134, 144).trim();
                    complemento = registro.substring(144, 164).trim();
                    telefone = registro.substring(164, 177).trim();
                    contaRegDadoLido++;
                    LocalDate localDate = LocalDate.parse(dataNasc);

                    // Instancia um objeto usuario com as informações lidas
                    Usuario usuario = new Usuario(idUsuario, userUsuario, localDate, nome, email, cpf, rua, numero, cep,
                            bairro, cidade, complemento, telefone);

                    // No Projeto de PI, pode fazer
                    repository.save(usuario);

                    // No nosso caso, como não estamos conectados ao banco
                    // vamos adicionar o objeto a na listaLida
                    listaLida.add(usuario);

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
        for (Usuario usuario : listaLida) {
            System.out.println(usuario);
        }

    }



    public static void main(String[] args) {
        List<Usuario> lista = new ArrayList();

        lista.add(new Usuario(123456, "erick", LocalDate.now().minusMonths(5),
                "Erick", "erick@gmail.com", "12345678990", "Rua Sete de Setembro",
                123, "12345678", "Bairro da Luz", "São Paulo",
                "Apartamento 14", "11 91234-4321"));

        lista.add(new Usuario(333456, "raul", LocalDate.now().minusMonths(7),
                "Raul", "raul@gmail.com", "33345691320", "Avenida Nove de Julho",
                300, "87654321", "Bairro da Cidade", "São Paulo",
                "Apartamento 40", "11 93344-4895"));




        gravaArquivoTxt(lista, "usuarios.txt");
        //leArquivoTxt("usuarios.txt");
    }
}
