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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author fridr
 */

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_pessoa",unique=true, nullable=false)
    private Integer idPessoa;
    
    @Column(name = "nome")
    private String nome;

    @Column(name = "genero")
    private String genero;
    
    @Column(name = "cpf", unique=true)
    private Integer cpf;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    
    @Column(name = "num_sus")
    private Integer numSus;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Login login;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Contato contato;
    
    public Pessoa(){
    }

    public Pessoa(Integer idPessoa, String nome, String genero, Integer cpf, Date dataNascimento, Integer numSus, Login login, Contato contato) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.genero = genero;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.numSus = numSus;
        this.login = login;
        this.contato = contato;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getNumSus() {
        return numSus;
    }

    public void setNumSus(Integer numSus) {
        this.numSus = numSus;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "idPessoa=" + idPessoa + ", nome=" + nome + ", genero=" + genero + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", numSus=" + numSus + ", login=" + login + ", contato=" + contato + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idPessoa);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.idPessoa, other.idPessoa)) {
            return false;
        }
        return true;
    }
    
}
