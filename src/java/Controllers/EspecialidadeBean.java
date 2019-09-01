package Controllers;

import Models.Especialidade;
import Models.EspecialidadeDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author fridr
 */

@Named(value = "especialidadeBean")
@ManagedBean
@SessionScoped
public class EspecialidadeBean implements Serializable{
    
    private Especialidade especialidade;
    private EspecialidadeDAO especialidadeDao;
    private List<Especialidade> listEspecialidade;
    
    public EspecialidadeBean() {
        this.especialidade = new Especialidade();
        this.especialidadeDao = new EspecialidadeDAO();
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public EspecialidadeDAO getEspecialidadeDao() {
        return especialidadeDao;
    }

    public void setEspecialidadeDao(EspecialidadeDAO especialidadeDao) {
        this.especialidadeDao = especialidadeDao;
    }

    public List<Especialidade> getListEspecialidade() {
        return listEspecialidade;
    }

    public void setListEspecialidade(List<Especialidade> listEspecialidade) {
        this.listEspecialidade = listEspecialidade;
    }
    
    public void mensagem(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
    
    public void init() {
        especialidade = new Especialidade();
    }
    
    public String carregaEspecialidade(Especialidade especialidade){
        this.especialidade = especialidade;
        return "editar";     
    }
    
    public void salvaEspecialidade(){
        especialidadeDao.createEspecialidade(especialidade);
        mensagem("Especialidade criada com Sucesso!", "");
        init();
    }
    
    public void atualizaEspecialidade() {
        especialidadeDao.updateEspecialidade(especialidade);
        mensagem("Especialidade atualizado com sucesso!", "");
        init();      
    }
    
    public void deletaEspecialidade(Especialidade especialidade) {
        especialidadeDao.deleteEspecialidade(especialidade);
        mensagem("Excluído com sucesso", "");        
    }
    
    public List listarEspecialidade(){
        return listEspecialidade = especialidadeDao.getListEspecialidade();
    }
    
    public String carregaLoginId(int id) {
        this.especialidade = especialidadeDao.getById(id);
        return "editar";
    }
    
}
