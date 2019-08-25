package Controllers;

import Models.Horario;
import Models.HorarioDAO;
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

@Named(value = "horarioBean")
@ManagedBean
@SessionScoped
public class HorarioBean implements Serializable{
    
    private Horario horario;
    private HorarioDAO horarioDao;
    private List<Horario> listHorario;
    
    public HorarioBean() {
        this.horario = new Horario();
        this.horarioDao = new HorarioDAO();
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public HorarioDAO getHorarioDao() {
        return horarioDao;
    }

    public void setHorarioDao(HorarioDAO horarioDao) {
        this.horarioDao = horarioDao;
    }

    public List<Horario> getListHorario() {
        return listHorario;
    }

    public void setListHorario(List<Horario> listHorario) {
        this.listHorario = listHorario;
    }
    
    public void mensagem(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
    
    public String carregaHorario(Horario horario){
        this.horario = horario;
        return "editar";     
    }
    
    public void salvaHorario(){
        horarioDao.createHorario(horario);
        mensagem("Horario criado com Sucesso!", "");
        horario = new Horario();
    }
    
    public void atualizaHorario() {
        horarioDao.updateHorario(horario);
        mensagem("Horario atualizado com sucesso!", "");
        horario = new Horario();       
    }
    
    public void deletaHorario(Horario horario) {
        horarioDao.deleteHorario(horario);
        mensagem("Excluído com sucesso", "");        
    }
    
    public List listarHorario(){
        return listHorario = horarioDao.getListHorario();
    }
    
    public String carregaLoginId(int id) {
        this.horario = horarioDao.getById(id);
        return "editar";
    }
    
}
