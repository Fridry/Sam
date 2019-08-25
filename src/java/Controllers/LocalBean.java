package Controllers;

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
public class LocalBean implements Serializable{
    
    private Local local;
    private LocalDAO localDao;
    private List<Local> listLocal;
    
    public LocalBean() {
        this.local = new Local();
        this.localDao = new LocalDAO();
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
    
    public void mensagem(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
    
    public String carregaLocal(Local local){
        this.local = local;
        return "editar";     
    }
    
    public void salvaLocal(){
        localDao.createLocal(local);
        mensagem("Local criado com Sucesso!", "");
        local = new Local();
    }
    
    public void atualizaLocal() {
        localDao.updateLocal(local);
        mensagem("Local atualizado com sucesso!", "");
        local = new Local();       
    }
    
    public void deletaLocal(Local local) {
        localDao.deleteLocal(local);
        mensagem("Excluído com sucesso", "");        
    }
    
    public List listarLocal(){
        return listLocal = localDao.getListLocal();
    }
    
    public String carregaLoginId(int id) {
        this.local = localDao.getById(id);
        return "editar";
    }
    
}
