package Controllers;

import Models.Agendamento;
import Models.AgendamentoDAO;
import Models.Especialidade;
import Models.EspecialidadeDAO;
import Models.Local;
import Models.LocalDAO;
import Models.Pessoa;
import Models.PessoaDAO;
import Relatorios.Relatorio;
import com.itextpdf.text.Element;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
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
    private ScheduleEvent eventoDefault;

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
        this.eventoDefault = new DefaultScheduleEvent();
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

    public ScheduleEvent getEventoDefault() {
        return eventoDefault;
    }

    public void setEventoDefault(ScheduleEvent eventoDefault) {
        this.eventoDefault = eventoDefault;
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
                Date dataHora = obj.getDataHora();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataHora);
                calendar.add(Calendar.MINUTE, 30);
                Date dataHoraFim = calendar.getTime();

                eventModel.addEvent(new DefaultScheduleEvent(nome, dataHora, dataHoraFim));
            }
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao listar.", "");
            e.printStackTrace();
        }
    }

    public void onDateSelect(SelectEvent selectEvent) {
        agendamento = new Agendamento();
        agendamento.setDataHora((Date) selectEvent.getObject());
    }

    public void onEventSelect(SelectEvent selectEvent) {
        eventoDefault = (DefaultScheduleEvent) selectEvent.getObject();
        agendamento = (Agendamento) eventoDefault.getData();
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
            eventModel.updateEvent(eventoDefault);
            mensagem("Agendamento criado com Sucesso!", "");
            init();
            return "";
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
            eventModel.updateEvent(eventoDefault);
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

    public void removerEvento() {
        eventModel.deleteEvent(eventoDefault);
        agendamentoDao.deleteAgendamento(agendamento);
        mensagem("Excluído com sucesso", "");
        agendamento = new Agendamento();
        eventModel = new DefaultScheduleModel();
    }

    public void carregaAgendaEspecialidade(Especialidade especialidade) {
        //System.out.println(especialidade);
        agendamentos = agendamentoDao.getListAgendamentoEspecialidade(especialidade);

        for (Agendamento obj : agendamentos) {
            String nome = obj.getPessoa().getNome();
            Date dataHora = obj.getDataHora();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataHora);
            calendar.add(Calendar.MINUTE, 30);
            Date dataHoraFim = calendar.getTime();

            eventModel.addEvent(new DefaultScheduleEvent(nome, dataHora, dataHoraFim));
        }
    }

    public int contador() {
        int total = 0;
        for (Agendamento obj : agendamentos) {
            total++;
        }
        return total;
    }
    
    /*public void gerarRelatório() {
        Relatorio relatorio = new Relatorio();
    }*/
    
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "images" + File.separator + "samlogo.png";

        Image logoSam = Image.getInstance(logo);
        logoSam.scaleToFit(70, 70);
        logoSam.setAlignment(Element.ALIGN_CENTER);
        pdf.add(logoSam);

        Paragraph titulo = new Paragraph("SAM - Sistema de agendamento médico");
        titulo.setAlignment(Element.ALIGN_CENTER);
        pdf.add(titulo);

        LineSeparator line = new LineSeparator();
        line.setOffset(-5);
        line.setLineColor(Color.BLACK);
        pdf.add(line);
        pdf.add(Chunk.NEWLINE);

        Paragraph p = new Paragraph("Relatório de consultas agendadas");
        p.setAlignment(Element.ALIGN_CENTER);
        pdf.add(p);
        pdf.add(Chunk.NEWLINE);

    }

}
