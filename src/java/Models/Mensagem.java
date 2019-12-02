/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author fridr
 */

@Entity
@Table(name = "mensagem")
public class Mensagem implements Serializable{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_mensagem",unique=true, nullable=false)
    private Integer idMensagem;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Pessoa de;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Pessoa para;
    
    @Column(name = "assunto")
    private String assunto;
    
    @Column(name = "mensagem")
    private String mensagem;

    public Mensagem(Integer idMensagem, Pessoa de, Pessoa para, String assunto, String mensagem) {
        this.idMensagem = idMensagem;
        this.de = de;
        this.para = para;
        this.assunto = assunto;
        this.mensagem = mensagem;
    }

    public Mensagem() {
    }

    public Integer getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Pessoa getDe() {
        return de;
    }

    public void setDe(Pessoa de) {
        this.de = de;
    }

    public Pessoa getPara() {
        return para;
    }

    public void setPara(Pessoa para) {
        this.para = para;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.idMensagem);
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
        final Mensagem other = (Mensagem) obj;
        if (!Objects.equals(this.idMensagem, other.idMensagem)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "idMensagem=" + idMensagem + ", de=" + de + ", para=" + para + ", assunto=" + assunto + ", mensagem=" + mensagem + '}';
    }
    
}
