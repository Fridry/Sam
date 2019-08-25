package Controllers;

import Models.Contato;
import Models.ContatoDAO;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Edwski
 */
@Named(value = "contatoBean")
@ManagedBean
@SessionScoped
public class ContatoBean implements Serializable{
    
    private Contato contato;
    private ContatoDAO contatoDao;
    private List<Contato> listContato;
    
    public ContatoBean() {
        this.contato = new Contato();
        this.contatoDao = new ContatoDAO();
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public ContatoDAO getContatoDao() {
        return contatoDao;
    }

    public void setContatoDao(ContatoDAO contatoDao) {
        this.contatoDao = contatoDao;
    }

    public List<Contato> getListContato() {
        return listContato;
    }

    public void setListContato(List<Contato> listContato) {
        this.listContato = listContato;
    }
    
    public void mensagem(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
    
    public String carregaContato(Contato contato){
        this.contato = contato;
        return "editar";     
    }
    
    public void salvaContato(){
        contatoDao.createContato(contato);
        mensagem("Contato criado com Sucesso!", "");
        contato = new Contato();
    }
    
    public void atualizaContato() {
        contatoDao.updateContato(contato);
        mensagem("Contato atualizado com sucesso!", "");
        contato = new Contato();       
    }
    
    public void deletaContato(Contato contato) {
        contatoDao.deleteContato(contato);
        mensagem("Excluído com sucesso", "");        
    }
    
    public List listarContato(){
        return listContato = contatoDao.getListContato();
    }
    
    public String carregaLoginId(int id) {
        this.contato = contatoDao.getById(id);
        return "editar";
    }
    
}
