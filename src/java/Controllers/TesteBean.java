/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Contato;
import Models.Teste;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author fridr
 */

@ManagedBean
public class TesteBean implements Serializable{
    
    private Teste teste;
    private Contato contato;

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
    
    public void init(){
        teste = new Teste();
        contato = new Contato();
    }
    
    
    public void salvar() {
        String texto = "Teste salvo com sucesso!";
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, "");
        
       FacesContext contexto = FacesContext.getCurrentInstance();
       
       contexto.addMessage(null, mensagem);
    }
    
    public void mostra() {
        System.out.println("Chamado");
    }
}
