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
    private Double valor;
    @NotNull
    private String senioridade;
    @NotNull
    private String titulo;
    @NotNull
    private String tecnologia;
    @NotNull
    private Integer tempEstimado;


    public Vaga() {
    }


    public Vaga(Integer idVaga, String descricao, Double valor, Integer tempEstimado) {
        this.id = idVaga;
        this.titulo = descricao;
        this.valor = valor;
        this.tempEstimado = tempEstimado;
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

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }


    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getTempEstimado() {
        return tempEstimado;
    }

    public void setTempEstimado(Integer tempEstimado) {
        this.tempEstimado = tempEstimado;
    }


    @Override
    public String toString() {
        return "Vaga{" +
                "idVaga=" + id +
                ", descricao='" + titulo + '\'' +
                ", valor=" + valor +
                ", tempEstimado=" + tempEstimado +
                '}';
    }
}
