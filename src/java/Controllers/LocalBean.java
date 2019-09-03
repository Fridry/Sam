package Controllers;

import Models.Contato;
import Models.ContatoDAO;
import Models.Local;
import Models.LocalDAO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author fridr
 */
@Named(value = "localBean")
@ManagedBean
@SessionScoped
public class LocalBean implements Serializable {

    private Local local;
    private LocalDAO localDao;
    private List<Local> listLocal;
    private Contato contato;
    private ContatoDAO contatoDao;

    public LocalBean() {
        this.local = new Local();
        this.localDao = new LocalDAO();
        this.contato = new Contato();
        this.contatoDao = new ContatoDAO();
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public LocalDAO getLocalDao() {
        return localDao;
    }

    public void setLocalDao(LocalDAO localDao) {
        this.localDao = localDao;
    }

    public List<Local> getListLocal() {
        return listLocal;
    }

    public void setListLocal(List<Local> listLocal) {
        this.listLocal = listLocal;
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

    public void mensagem(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }

    public void erro(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }

    public void init() {
        local = new Local();
        contato = new Contato();
    }

    public void carregaLocal(Local local, Contato contato) {
        this.local = local;
        this.contato = contato;
    }

    public void salvaLocal(Contato contato) {
        try {
            this.local.setContato(contato);
            localDao.createLocal(local);
            init();
            mensagem("Local criado com Sucesso!", "");
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao salvar o local.", "");
            e.printStackTrace();
        }
    }
    
    public void fundirLocal(Contato contato) {
        try {
            this.local.setContato(contato);
            localDao.mergeLocal(local);
            init();
            listarLocal();
            mensagem("Local criado com sucesso!", "");
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao criar o local.", "");
            e.printStackTrace();
        }
    }


    public void atualizaLocal() {
        localDao.updateLocal(local);
        mensagem("Local atualizado com sucesso!", "");
        local = new Local();
    }
    
    public void deletaLocal(Local local) {
        try {
            localDao.deleteLocal(local);
            listarLocal();
            mensagem("Excluído com sucesso", "");
        } catch(RuntimeException e) {
            erro("Ocorreu um erro ao excluir o local.", "");
            e.printStackTrace();   
        }
    }
    
    public List listarLocal() {
        try {
            return listLocal = localDao.getListLocal();
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao listar os locais.", "");
            e.printStackTrace();
            return null;
        }
    }

    public String carregaLoginId(int id) {
        this.local = localDao.getById(id);
        return "editar";
    }

}
