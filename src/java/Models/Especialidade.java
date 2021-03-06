package Models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author fridr
 */

@Entity
@Table(name = "especialidade")
public class Especialidade implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_especialidade", unique = true, nullable = false)
    private Integer idEspecialidade;
    
    @Column(name="tipo_especialidade")
    private String tipoEspecialidade;

    public Especialidade() {
    }

    public Especialidade(Integer idEspecialidade, String tipoEspecialidade) {
        this.idEspecialidade = idEspecialidade;
        this.tipoEspecialidade = tipoEspecialidade;
    }

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getTipoEspecialidade() {
        return tipoEspecialidade;
    }

    public void setTipoEspecialidade(String tipoEspecialidade) {
        this.tipoEspecialidade = tipoEspecialidade;
    }

    @Override
    public String toString() {
        return "Especialidade{" + "idEspecialidade=" + idEspecialidade + ", tipoEspecialidade=" + tipoEspecialidade + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.idEspecialidade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Especialidade other = (Especialidade) obj;
        if (!Objects.equals(this.idEspecialidade, other.idEspecialidade)) {
            return false;
        }
        return true;
    }    
    
}
