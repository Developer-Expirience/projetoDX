package sptech.correcao01.dominio;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String senioridade;
    @NotNull
    private String titulo;

     @NotNull
     private String descricao;
    private String urlImagem;
    public Vaga() {
    }


    public Vaga(Integer idVaga, String descricao, String senioridade, String titulo, String urlImagem){
        this.id = idVaga;
        this.titulo = titulo;
        this.descricao = descricao;
        this.senioridade = senioridade;
        this.urlImagem = urlImagem;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSenioridade() {
        return senioridade;
    }

    public void setSenioridade(String senioridade) {
        this.senioridade = senioridade;
    }



    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "id=" + id +
                ", senioridade='" + senioridade + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", urlImagem='" + urlImagem + '\'' +
                '}';
    }
}

