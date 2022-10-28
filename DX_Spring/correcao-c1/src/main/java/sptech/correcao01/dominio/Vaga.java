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
    @ManyToOne(optional = false)
    private Usuario usuario;

    @JoinColumn(name="id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa;

    @CreationTimestamp
    @Column(name = "dh_vaga")
    private LocalDateTime dataVaga;

    private String seneriodade;

    private String tipoTecnologia;
    @NotBlank
    private String descricao;

    @NotNull
    private Double valor;

    @NotNull
    private Integer tempEstimado;


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

    public String getSeneriodade() {
        return seneriodade;
    }

    public void setSeneriodade(String seneriodade) {
        this.seneriodade = seneriodade;
    }

    public String getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(String tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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


}
