package Controllers;

import Models.Especialidade;
import Models.EspecialidadeDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class EspecialidadeBean implements Serializable {

    private Especialidade especialidade;
    private EspecialidadeDAO especialidadeDao;
    private List<Especialidade> listEspecialidades;

    public EspecialidadeBean() {
        this.especialidade = new Especialidade();
        this.especialidadeDao = new EspecialidadeDAO();
        listarEspecialidade();
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
        return listEspecialidades;
    }

    public void setListEspecialidade(List<Especialidade> listEspecialidades) {
        this.listEspecialidades = listEspecialidades;
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
        especialidade = new Especialidade();
    }
    
    public String redireciona() {
        return "listaEspecialidade";
    }

    public String carregaEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
        return "editar";
    }

    public String salvaEspecialidade() {
        try {
            especialidadeDao.createEspecialidade(especialidade);
            init();
            listarEspecialidade();
            mensagem("Especialidade criada com Sucesso!", "");
            return "../";
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao salvar a Especialidade.", "");
            e.printStackTrace();
            
        }
        return "../";
    }
    
    public void fundirEspecialidade() {
        try {
            especialidadeDao.mergeEspecialidade(especialidade);
            init();
            listarEspecialidade();
            mensagem("Especialidade atualizada com Sucesso!", "");
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao atualizar a Especialidade.", "");
            e.printStackTrace();
        }
    }

    public void atualizaEspecialidade() {
        especialidadeDao.updateEspecialidade(especialidade);
        mensagem("Especialidade atualizado com sucesso!", "");
        init();
    }

    public void deletaEspecialidade(Especialidade especialidade) {
        try {
            especialidadeDao.deleteEspecialidade(especialidade);
            listarEspecialidade();
            mensagem("Excluído com sucesso", "");
        } catch(RuntimeException e) {
            erro("Ocorreu um erro ao excluir a Especialidade.", "");
            e.printStackTrace();
            
        }

    }

    public List listarEspecialidade() {
        try {
            return listEspecialidades = especialidadeDao.getListEspecialidade();
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao listar as Especialidades.", "");
            e.printStackTrace();
            return null;
        }
    }

    /*
    @PostConstruct
    public void listaDeEspecialidades() {
        try {
            listEspecialidades = especialidadeDao.getListEspecialidade();
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao listar as Especialidades.", "");
            e.printStackTrace();
        }
    }
     */

    public String carregaEspecialidadeId(int id) {
        this.especialidade = especialidadeDao.getById(id);
        return "editar";
    }

}
