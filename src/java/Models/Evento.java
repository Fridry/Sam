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
    private Integer idEvento;

    @Column(name = "nome_evento")
    private String nomeEvento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataHoraEvento")
    private Date dataHoraEvento;
    
    @Column(name = "informacoes")
    private String informacoes;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private Local local;

    public Evento() {
    }

    public Evento(Integer idEvento, String nomeEvento, Date dataHoraEvento, String informacoes, Local local) {
        this.idEvento = idEvento;
        this.nomeEvento = nomeEvento;
        this.dataHoraEvento = dataHoraEvento;
        this.informacoes = informacoes;
        this.local = local;
    }
    
    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public Date getDataHoraEvento() {
        return dataHoraEvento;
    }

    public void setDataHoraEvento(Date dataHoraEvento) {
        this.dataHoraEvento = dataHoraEvento;
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
        return "Evento{" + "idEvento=" + idEvento + ", nomeEvento=" + nomeEvento + ", dataHoraEvento=" + dataHoraEvento + ", informacoes=" + informacoes + ", local=" + local + '}';
    }

    @Override
    public int hashCode() {
        Integer hash = 7;
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
