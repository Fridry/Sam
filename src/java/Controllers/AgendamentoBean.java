package Controllers;

import Models.Agendamento;
import Models.AgendamentoDAO;
import Models.Especialidade;
import Models.EspecialidadeDAO;
import Models.Horario;
import Models.HorarioDAO;
import Models.Local;
import Models.LocalDAO;
import Models.Pessoa;
import Models.PessoaDAO;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private Horario horario;
    private HorarioDAO horarioDao;
    private List<Horario> horarios;

    public AgendamentoBean() {
        this.agendamento = new Agendamento();
        this.agendamentoDao = new AgendamentoDAO();
        this.local = new Local();
        this.localDao = new LocalDAO();
        this.pessoa = new Pessoa();
        this.pessoaDao = new PessoaDAO();
        this.horario = new Horario();
        this.horarioDao = new HorarioDAO();
        this.especialidade = new Especialidade();
        this.especialidadeDao = new EspecialidadeDAO();
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

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
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
        horario = new Horario();
        try {
            locais = localDao.getListLocal();
            horarios = horarioDao.getListHorario();
            pessoas = pessoaDao.getListPessoa();
            especialidades = especialidadeDao.getListEspecialidade();
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao listar.", "");
            e.printStackTrace();
        }
    }

    public String carregaAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
        return "editar";
    }

    public void salvaAgendamento() {
        try {
            agendamento.setStatus("Agendado");
            agendamentoDao.createAgendamento(agendamento);
            mensagem("Agendamento criado com Sucesso!", "");
            agendamento = new Agendamento();
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao Realizar o agendamento.", "");
            e.printStackTrace();
        }
    }

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

    public void atualizaAgendamento() {
        agendamentoDao.updateAgendamento(agendamento);
        mensagem("Agendamento atualizado com sucesso!", "");
        agendamento = new Agendamento();
    }

    public void deletaAgendamento(Agendamento agendamento) {
        agendamentoDao.deleteAgendamento(agendamento);
        mensagem("Excluído com sucesso", "");
    }

    public List listarAgendamento() {
        return agendamentos = agendamentoDao.getListAgendamento();
    }

    public String carregaAgendamentoId(int id) {
        this.agendamento = agendamentoDao.getById(id);
        return "editar";
    }

    public String salvaAgendamentoPessoa(Pessoa pessoa) {
        try {
            agendamento.setPessoa(pessoa);
            agendamento.setStatus("Agendado");
            agendamentoDao.createAgendamento(agendamento);
            mensagem("Agendamento criado com Sucesso!", "");
            init();
            agendamento = new Agendamento();
            return "listas/listaAgendamentos";
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao Realizar o agendamento.", "");
            e.printStackTrace();
        }

        return null;
    }

    public void dataToString(Date data) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(data);
        System.out.println(strDate);
    }

    public List carregaHoraByData(Date data) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(data);
            horarios = horarioDao.getListHorarioData(strDate);
            mensagem("Deu certo", "");
            return horarios;
        } catch (RuntimeException e) {
            erro("Ocorreu um erro.", "");
            e.printStackTrace();
        }

        return horarios;
    }

}
