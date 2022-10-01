package sptech.correcao01.dominio;


import org.hibernate.validator.constraints.br.CPF;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Usuario {

    @Id  // do javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotBlank // valida se não é nulo, texto vazio ou texto só com espaços em branco
    @Size(min = 3) // valida um tamanho para o texto
    private String usuario;

    @NotBlank // valida se não é nulo, texto vazio ou texto só com espaços em branco
    private String senha;

    // @NotBlank // ERRO COMUM! NotBlank é só para String. Obrigatório p/ os demais tipos é @NotNull
//    @NotNull // do pacote javax.validation - valida se o campo está presente e não é null
//    @Min(0) // valida se o valor é pelo menos 0
    // @DecimalMin("0.01") // para numeros reais (Double, Float, BigDecimal)
//    @Negative
//    @NegativeOrZero
//    @Positive
//    @PositiveOrZero
//    private Integer filhos;

    @NotBlank
    @Past // indica que só serão aceitas data passadas (antes de hoje)
//    @PastOrPresent
//    @Future
//    @FutureOrPresent
    private LocalDate dataNascimento; // formato: "aaaa-mm-dd"

    @NotBlank // valida se não é nulo, texto vazio ou texto só com espaços em branco
    private String nome;

    // outros exemplos abaixo
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String rua;

    @NotNull
    private Integer numero;

    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;

    @NotBlank
    @Size(min = 3)
    private String bairro;

    @NotBlank
    @Size(min = 3)
    private String cidade;

    @NotBlank
    @Size(min = 5)
    private String complemento;

    @NotBlank
    @Pattern( // valida usando uma Regex (expressão regular)
        regexp = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}$",
        message = "Indique um telefone válido"
    )
    private String telefone;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

//    public String getSenha() {
//        return senha;
//    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
