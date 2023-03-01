package sptech.correcao01.dominio;


import org.hibernate.validator.constraints.br.CPF;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.security.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable {

    @Id  // do javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotBlank // valida se não é nulo, texto vazio ou texto só com espaços em branco
    @Size(min = 3) // valida um tamanho para o texto
    private String usuario;

    @NotBlank // valida se não é nulo, texto vazio ou texto só com espaços em branco
    private String senha;

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

    private boolean usuarioValidado;

    @NotBlank
    /*@Pattern( // valida usando uma Regex (expressão regular)
        regexp = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}$",
        message = "Indique um telefone válido"
    )*/
    private String telefone;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String usuario, LocalDate dataNascimento, String nome, String email,
                   String cpf, String rua, Integer numero, String cep, String bairro, String cidade, String complemento,
                   String telefone) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.complemento = complemento;
        this.telefone = telefone;
    }
    
    public boolean isUsuarioValidado() {
        return usuarioValidado;
    }

    public void setUsuarioValidado(boolean usuarioValidado) {
        this.usuarioValidado = usuarioValidado;
    }

    public boolean getUsuarioAutenticado(String usuario, String senha){
        if (this.usuario.equals(usuario) && this.senha.equals(senha)){
             setUsuarioValidado(true);
             return usuarioValidado;
        }else {
           setUsuarioValidado(false);
        }
        return usuarioValidado;

    }

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

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", usuario='" + usuario + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", complemento='" + complemento + '\'' +
                ", usuarioValidado=" + usuarioValidado +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
