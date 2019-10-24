package Controllers;

import Models.Agendamento;
import Models.AgendamentoDAO;
import Models.Especialidade;
import Models.EspecialidadeDAO;
import Models.Local;
import Models.LocalDAO;
import Models.Pessoa;
import Models.PessoaDAO;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author fridr
 */
@Named(value = "agendamentoBean")
@ManagedBean
@SessionScoped
public class AgendamentoBean implements Serializable {

    private Agendamento agendamento;
    private AgendamentoDAO agendamentoDao;
    private List<Agendamento> agendamentos;
    private Local local;
    private LocalDAO localDao;
    private List<Local> locais;
    private Especialidade especialidade;
    private EspecialidadeDAO especialidadeDao;
    private List<Especialidade> especialidades;
    private Pessoa pessoa;
    private PessoaDAO pessoaDao;
    private List<Pessoa> pessoas;

    private ScheduleModel eventModel;

    public AgendamentoBean() {
        this.agendamento = new Agendamento();
        this.agendamentoDao = new AgendamentoDAO();
        this.local = new Local();
        this.localDao = new LocalDAO();
        this.pessoa = new Pessoa();
        this.pessoaDao = new PessoaDAO();
        this.especialidade = new Especialidade();
        this.especialidadeDao = new EspecialidadeDAO();
        this.eventModel = new DefaultScheduleModel();
        init();
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

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
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

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
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

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaDAO getPessoaDao() {
        return pessoaDao;
    }

    public void setPessoaDao(PessoaDAO pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public void mensagem(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }

    public void erro(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }

    @PostConstruct
    public void init() {
        agendamento = new Agendamento();
        local = new Local();
        especialidade = new Especialidade();
        pessoa = new Pessoa();
        eventModel = new DefaultScheduleModel();
        try {
            agendamentos = agendamentoDao.getListAgendamento();
            locais = localDao.getListLocal();
            pessoas = pessoaDao.getListPessoa();
            especialidades = especialidadeDao.getListEspecialidade();

            for (Agendamento obj : agendamentos) {
                String nome = obj.getPessoa().getNome();
                Date dia = obj.getData();
                Date hora = obj.getHora();
                String datahora = dia + " " + hora;
                
                //Date dataAgenda = dataHoraConversor(datahora);
                
                Date dataAgenda = dataHoraConversor(datahora);
                
            eventModel.addEvent(new DefaultScheduleEvent(nome, dataAgenda, dataAgenda));

            //Parametros String, Data inicial, Data final, *Boolean - Dia Todo*
            // Data inicial e final devem ser iguais os horários devem ser diferentes - ÓBVIO

        }
    }
    catch (RuntimeException e) {
            erro("Ocorreu um erro ao listar.", "");
        e.printStackTrace();
    }
}
    
    public Date dataHoraConversor(String dataHora) {
        try {
            SimpleDateFormat  data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            Date dataAgenda = data.parse(dataHora);
            System.out.println(dataAgenda);
            return dataAgenda;
        } catch (ParseException ex) {
            Logger.getLogger(AgendamentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public void onDateSelect(SelectEvent selectEvent) {
        agendamento = new Agendamento();
        agendamento.setData((Date) selectEvent.getObject());
        agendamento.setHora((Date) selectEvent.getObject());        
    }
    
    public void onEventSelect(SelectEvent selectEvent) {
        agendamento = new Agendamento();
        agendamento.setData((Date) selectEvent.getObject());
        agendamento.setHora((Date) selectEvent.getObject());
    }
    
    public String carregaAgendamento(Agendamento agendamento) {
        init();
        this.agendamento = agendamento;
        return "editar";
    }
    
    //Salva os dados no banco
    public String salvaAgendamento() {
        try {
            agendamento.setStatus("Agendado");
            agendamentoDao.createAgendamento(agendamento);
            mensagem("Agendamento criado com Sucesso!", "");
            agendamento = new Agendamento();
            return "/listas/listaAgendamentos";
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao Realizar o agendamento.", "");
            e.printStackTrace();
        }
        
        return null;
    }

    //Salva ou atualiza os dados no banco
    public void fundirAgendamento() {
        try {
            agendamento.setStatus("Agendado");
            agendamentoDao.mergeAgendamento(agendamento);
            init();
            listarAgendamento();
            mensagem("Agendamento criado com sucesso!", "");
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao agendar.", "");
            e.printStackTrace();
        }
    }
    
    //Exclui os dados
    public void deletaAgendamento(Agendamento agendamento) {
        agendamentoDao.deleteAgendamento(agendamento);
        mensagem("Excluído com sucesso", "");
    }

    public List<Agendamento> listarAgendamento() {
        return agendamentos = agendamentoDao.getListAgendamento();
    }

    public String carregaAgendamentoId(int id) {
        this.agendamento = agendamentoDao.getById(id);
        return "editar";
    }

    //Salva agendamentos - função usada no modal *Agendar* na listagem de usuarios
    public String salvaAgendamentoPessoa(Pessoa pessoa) {
        try {
            agendamento.setPessoa(pessoa);
            agendamento.setStatus("Agendado");
            agendamentoDao.createAgendamento(agendamento);
            mensagem("Agendamento criado com Sucesso!", "");
            init();
            agendamento = new Agendamento();
            return "/listas/listaAgendamentos";
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao Realizar o agendamento.", "");
            e.printStackTrace();
        }

        return null;
    }
    
    
    //Carrega uma lista de horários disponíveis baseados na data selecionada
    /*public List<Horario> carregaHoraByData(Date data) {
        try {
            horario = new Horario();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(data);
            List<Horario> dataHoras = horarioDao.getListHorarioData(strDate);
            mensagem("Deu certo", "");
            return dataHoras;
        } catch (RuntimeException e) {
            erro("Ocorreu um erro.", "");
            e.printStackTrace();
        }

        return null;
    }*/
    
    public List<Pessoa> carregaUsuariosOnComplete(String numSus) {
        try {
            pessoas = pessoaDao.searchByNumSus(numSus);
            return pessoas;
        } catch (RuntimeException e) {
            erro("Ocorreu um erro.", "");
            e.printStackTrace();
        }

        return pessoas;
    }
    
   /* public List<Horario> carregaHorarioOnComplete(Date data) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(data);
            horarios = horarioDao.getListHorarioData(strDate);
            return horarios;
        } catch (RuntimeException e) {
            erro("Ocorreu um erro.", "");
            e.printStackTrace();
        }

        return horarios;
    }*/
}
