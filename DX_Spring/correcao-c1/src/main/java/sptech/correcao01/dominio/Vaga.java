package sptech.correcao01.dominio;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Vaga {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @EmbeddedId
    private VagaId idVaga;

    @JoinColumn(name="id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = true)
    private Usuario usuario;

    @JoinColumn(name="id_empresa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa;

    @CreationTimestamp
    @Column(name = "dh_vaga")
    private LocalDateTime dataVaga;


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


    public Integer getIdVaga() {
        return id;
    }

    public void setIdVaga(Integer idVaga) {
        this.id = idVaga;
    }


    public VagaId getId() {
        return idVaga;
    }

    public void setId(VagaId id) {
        this.idVaga = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public LocalDateTime getDataVaga() {
        return dataVaga;
    }

    public void setDataVaga(LocalDateTime dataVaga) {
        this.dataVaga = dataVaga;
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
                "id=" + id +
                ", idVaga=" + idVaga +
                ", usuario=" + usuario +
                ", empresa=" + empresa +
                ", dataVaga=" + dataVaga +
                ", valor=" + valor +
                ", senioridade='" + senioridade + '\'' +
                ", titulo='" + titulo + '\'' +
                ", tecnologia='" + tecnologia + '\'' +
                ", tempEstimado=" + tempEstimado +
                '}';
    }
}
