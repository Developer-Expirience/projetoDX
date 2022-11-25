package sptech.correcao01.dominio;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Entity
public class VagaEmpresaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(name = "dh_vaga")
    private LocalDateTime dataVaga;

//    @JoinColumn(name="vaga_id", insertable = false, updatable = false)
    @ManyToOne
    private Vaga vaga;

//    @JoinColumn(name="id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = true)
    private Usuario usuario;

//    @JoinColumn(name="id_empresa", insertable = false, updatable = false)
    @ManyToOne(optional = true)
    private Empresa empresa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataVaga() {
        return dataVaga;
    }

    public void setDataVaga(LocalDateTime dataVaga) {
        this.dataVaga = dataVaga;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
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
}
