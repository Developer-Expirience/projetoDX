package sptech.correcao01.dominio;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
public class Empresa implements Serializable {

//    {
//        "idEmpresa":1,
//            "nome":"teste",
//            "usuario":"testeEmpresa",
//            "senha":"teste",
//            "email":"teste@email.com",
//            "cnpj":"80.117.186/0001-00",
//            "telefone":"11 95116-0848",
//            "numFuncionario":3,
//            "rua":"rua 1",
//            "numero":342,
//            "cep":"08032832",
//            "bairro":"bairro b",
//            "cidade":"cidade c"
//    }
    @Id  // do javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;
    @NotBlank // valida se não é nulo, texto vazio ou texto só com espaços em branco
    @Size(min = 3) // valida um tamanho para o texto
    private String nome;

    @NotBlank // valida se não é nulo, texto vazio ou texto só com espaços em branco
    @Size(min = 3) // valida um tamanho para o texto
    private String usuario;


    @NotBlank // valida se não é nulo, texto vazio ou texto só com espaços em branco
    private String senha;

    @NotBlank(message = "Onde já se viu nenhum email?!") // valida se não é nulo, texto vazio ou texto só com espaços em branco
    @Email
    private String email;

    @NotBlank
    @CNPJ
    private String cnpj;

    private boolean empresaValidado;

    @NotBlank
    @Pattern( // valida usando uma Regex (expressão regular)
            regexp = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}$",
            message = "Indique um telefone válido"
    )
    private String telefone;
    @NotNull
    @Min(1)
    private Integer numFuncionario;


    @NotBlank
    @Size(min = 5)
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

    public Empresa() {
    }

    public Empresa(Integer idEmpresa, String nome, String usuario, String email, String cnpj,
                   String telefone, Integer numFuncionario, String rua, Integer numero, String cep, String bairro,
                   String cidade) {
        this.idEmpresa = idEmpresa;
        this.nome = nome;
        this.usuario = usuario;
        this.email = email;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.numFuncionario = numFuncionario;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
    }
    public boolean isEmpresaValidado() {
        return empresaValidado;
    }

    public void setEmpresaValidado(boolean empresaValidado) {
        this.empresaValidado = empresaValidado;
    }

    public boolean getEmpresaAutenticado(String usuario, String senha){
        if (this.usuario.equals(usuario) && this.senha.equals(senha)){
            setEmpresaValidado(true);
        }
        return empresaValidado;

    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getNumFuncionario() {
        return numFuncionario;
    }

    public void setNumFuncionario(Integer numFuncionario) {
        this.numFuncionario = numFuncionario;
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

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa=" + idEmpresa +
                ", nome='" + nome + '\'' +
                ", usuario='" + usuario + '\'' +
                ", email='" + email + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", empresaValidado=" + empresaValidado +
                ", telefone='" + telefone + '\'' +
                ", numFuncionario=" + numFuncionario +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
