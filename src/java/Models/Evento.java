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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fridr
 */
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_evento", unique = true, nullable = false)
    private int idEvento;

    @Column(name = "nome_evento")
    private String nomeEvento;

    @Temporal(TemporalType.DATE)
    @Column(name = "dia_evento")
    private Date diaEvento;
    
    @Column(name = "hora_evento")
    @Temporal(TemporalType.TIME)
    private Date horaEvento;
    
    @Column(name = "informacoes")
    private String informacoes;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Local local;

    public Evento() {
    }

    public Evento(int idEvento, String nomeEvento, Date diaEvento, Date horaEvento, String informacoes, Local local) {
        this.nomeEvento = nomeEvento;
        this.diaEvento = diaEvento;
        this.horaEvento = horaEvento;
        this.informacoes = informacoes;
        this.local = local;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public Date getDiaEvento() {
        return diaEvento;
    }

    public void setDiaEvento(Date diaEvento) {
        this.diaEvento = diaEvento;
    }

    public Date getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(Date horaEvento) {
        this.horaEvento = horaEvento;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Evento{" + "idEvento=" + idEvento + ", nomeEvento=" + nomeEvento + ", diaEvento=" + diaEvento + ", horaEvento=" + horaEvento + ", informacoes=" + informacoes + ", local=" + local + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idEvento);
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
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.idEvento, other.idEvento)) {
            return false;
        }
        return true;
    }
    
    

}
