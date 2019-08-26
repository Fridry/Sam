/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author fridr
 */

@Entity
@Table(name = "teste")
public class Teste implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id",unique=true, nullable=false)
    private int id;
    
    @Column(length = 50, nullable = false)
    private String nome;
        
    @ManyToOne
    @JoinColumn(nullable = false)
    private Contato contato;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
    
    
    
    
    
}
