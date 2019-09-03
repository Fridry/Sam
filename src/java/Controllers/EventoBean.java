package Controllers;

import Models.Evento;
import Models.EventoDAO;
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

@Named(value = "eventoBean")
@ManagedBean
@SessionScoped
public class EventoBean implements Serializable{
    
    private Evento evento;
    private EventoDAO eventoDao;
    private List<Evento> listEvento;
    private Local local;
    private LocalDAO localDao;
    
    public EventoBean() {
        this.evento = new Evento();
        this.eventoDao = new EventoDAO();
        this.local = new Local();
        this.localDao = new LocalDAO();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public EventoDAO getEventoDao() {
        return eventoDao;
    }

    public void setEventoDao(EventoDAO eventoDao) {
        this.eventoDao = eventoDao;
    }

    public List<Evento> getListEvento() {
        return listEvento;
    }

    public void setListEvento(List<Evento> listEvento) {
        this.listEvento = listEvento;
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
        evento = new Evento();
        local = new Local();
    }
    
    public String carregaEvento(Evento evento){
        this.evento = evento;
        return "editar";     
    }
    
    public void salvaEvento(){
        try {
        eventoDao.createEvento(evento);
        mensagem("Evento criado com Sucesso!", "");
        evento = new Evento();
        } catch(RuntimeException e) {
            erro("Ocorreu um erro ao agendar o evento.", "");
            e.printStackTrace();
        }
    }
    
    public void fundirEvento(Local local) {
        try {
            this.evento.setLocal(local);
            eventoDao.mergeEvento(evento);
            init();
            listarEvento();
            mensagem("Local criado com sucesso!", "");
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao criar o local.", "");
            e.printStackTrace();
        }
    }
    
    public void atualizaEvento() {
        eventoDao.updateEvento(evento);
        mensagem("Evento atualizado com sucesso!", "");
        evento = new Evento();       
    }
    
    public void deletaEvento(Evento evento) {
        eventoDao.deleteEvento(evento);
        mensagem("Excluído com sucesso", "");        
    }
    
    public List listarEvento(){
        return listEvento = eventoDao.getListEvento();
    }
    
    public String carregaLoginId(int id) {
        this.evento = eventoDao.getById(id);
        return "editar";
    }
}
