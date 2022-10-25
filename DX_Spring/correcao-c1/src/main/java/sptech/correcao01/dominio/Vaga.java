package sptech.correcao01.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVaga;

    private String seneriodade;

    private String tipoTecnologia;
    @NotBlank
    private String descricao;

    @NotNull
    private Double valor;

    @NotNull
    private Integer tempEstimado;

    public Integer getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Integer idVaga) {
        this.idVaga = idVaga;
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
