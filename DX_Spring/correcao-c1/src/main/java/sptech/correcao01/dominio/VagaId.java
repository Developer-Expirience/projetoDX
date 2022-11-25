package sptech.correcao01.dominio;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VagaId implements Serializable {

    @Column(name = "id_usuario")
    private Integer usuarioId;

    @Column(name = "id_empresa")
    private Integer empresaId;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VagaId)) return false;

        VagaId vagaId = (VagaId) o;

        if (!Objects.equals(usuarioId, vagaId.usuarioId)) return false;
        return Objects.equals(empresaId, vagaId.empresaId);
    }

    @Override
    public int hashCode() {
        int result = usuarioId != null ? usuarioId.hashCode() : 0;
        result = 31 * result + (empresaId != null ? empresaId.hashCode() : 0);
        return result;
    }

}
