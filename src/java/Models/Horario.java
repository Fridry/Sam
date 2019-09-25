package Models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fridr
 */

@Entity
@Table(name = "horario")
public class Horario implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_horario", unique = true, nullable = false)
    private Integer idHorario;
    
    @Column(name="hora")
    @Temporal(TemporalType.TIME)
    private Date hora;

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Horario{" + "idHorario=" + idHorario + ", hora=" + hora + '}';
    }

    @Override
    public int hashCode() {
        Integer hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idHorario);
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
        final Horario other = (Horario) obj;
        if (!Objects.equals(this.idHorario, other.idHorario)) {
            return false;
        }
        return true;
    }
    
    
}
