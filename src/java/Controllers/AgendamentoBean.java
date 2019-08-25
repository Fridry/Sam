package Controllers;

import Models.Agendamento;
import Models.AgendamentoDAO;
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

@Named(value = "agendamentoBean")
@ManagedBean
@SessionScoped
public class AgendamentoBean implements Serializable{
    
    private Agendamento agendamento;
    private AgendamentoDAO agendamentoDao;
    private List<Agendamento> listAgendamento;
    
    public AgendamentoBean() {
        this.agendamento = new Agendamento();
        this.agendamentoDao = new AgendamentoDAO();
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public AgendamentoDAO getAgendamentoDao() {
        return agendamentoDao;
    }

    public void setAgendamentoDao(AgendamentoDAO agendamentoDao) {
        this.agendamentoDao = agendamentoDao;
    }

    public List<Agendamento> getListAgendamento() {
        return listAgendamento;
    }

    public void setListAgendamento(List<Agendamento> listAgendamento) {
        this.listAgendamento = listAgendamento;
    }
    
    public void mensagem(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
    
    public String carregaAgendamento(Agendamento agendamento){
        this.agendamento = agendamento;
        return "editar";     
    }
    
    public void salvaAgendamento(){
        agendamentoDao.createAgendamento(agendamento);
        mensagem("Agendamento criado com Sucesso!", "");
        agendamento = new Agendamento();
    }
    
    public void atualizaAgendamento() {
        agendamentoDao.updateAgendamento(agendamento);
        mensagem("Agendamento atualizado com sucesso!", "");
        agendamento = new Agendamento();       
    }
    
    public void deletaAgendamento(Agendamento agendamento) {
        agendamentoDao.deleteAgendamento(agendamento);
        mensagem("Excluído com sucesso", "");        
    }
    
    public List listarAgendamento(){
        return listAgendamento = agendamentoDao.getListAgendamento();
    }
    
    public String carregaLoginId(int id) {
        this.agendamento = agendamentoDao.getById(id);
        return "editar";
    }
    
    
}
