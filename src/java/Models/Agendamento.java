/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author fridr
 */
@Entity
@Table(name = "agendamento")
public class Agendamento implements Serializable {
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_agendamento", unique = true, nullable = false)
    private int idAgendamento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Pessoa pessoa;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Especialidade especialidade;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Horario horario;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Local local;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
    
    @Column(name="status")
    private String status;
    
    public Agendamento() {
    }

    public Agendamento(Especialidade especialidade, Horario horario, Local local, Pessoa pessoa, Date data, String status) {
       this.especialidade = especialidade;
       this.horario = horario;
       this.local = local;
       this.pessoa = pessoa;
       this.data = data;
       this.status = status;
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Agendamento{" + "idAgendamento=" + idAgendamento + ", pessoa=" + pessoa + ", especialidade=" + especialidade + ", horario=" + horario + ", local=" + local + ", data=" + data + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idAgendamento);
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
        final Agendamento other = (Agendamento) obj;
        if (!Objects.equals(this.idAgendamento, other.idAgendamento)) {
            return false;
        }
        return true;
    }
    
    
}
