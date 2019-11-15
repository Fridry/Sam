package Controllers;

import Models.Contato;
import Models.ContatoDAO;
import Models.Login;
import Models.LoginDAO;
import Models.Pessoa;
import Models.PessoaDAO;
import com.itextpdf.text.Element;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Header;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.GrayColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author fridr
 */
@Named(value = "pessoaBean")
@ManagedBean
@SessionScoped
public class PessoaBean implements Serializable {

    private Pessoa pessoa;
    private PessoaDAO pessoaDao;
    private Contato contato;
    private ContatoDAO contatoDao;
    private Login login;
    private LoginDAO loginDao;
    private List<Pessoa> listPessoa;

    public PessoaBean() {
        this.pessoa = new Pessoa();
        this.pessoaDao = new PessoaDAO();
        this.contato = new Contato();
        this.contatoDao = new ContatoDAO();
        this.login = new Login();
        this.loginDao = new LoginDAO();
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

    public List<Pessoa> getListPessoa() {
        return listPessoa;
    }

    public void setListPessoa(List<Pessoa> listPessoa) {
        this.listPessoa = listPessoa;
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

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public LoginDAO getLoginDao() {
        return loginDao;
    }

    public void setLoginDao(LoginDAO loginDao) {
        this.loginDao = loginDao;
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
        pessoa = new Pessoa();
        contato = new Contato();
        login = new Login();
    }

    public String carregaPessoa(Pessoa pessoa, Contato contato, Login login) {
        init();
        this.pessoa = pessoa;
        this.contato = contato;
        this.login = login;
        return "editar";
    }

    public String salvaPessoa(Contato contato, Login login) {
        try {
            this.pessoa.setLogin(login);
            this.pessoa.setContato(contato);
            pessoaDao.createPessoa(pessoa);
            mensagem("usuário cadastrado com Sucesso!", "");
            this.pessoa = new Pessoa();
            this.contato = new Contato();
            this.login = new Login();
            return "/listas/listaPessoas";
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao cadastra o usuário.", "");
            e.printStackTrace();
        }

        return null;
    }

    public void salvaPessoa() {
        pessoaDao.createPessoa(pessoa);
        mensagem("Pessoa criada com Sucesso!", "");
        this.pessoa = new Pessoa();
    }

    public void fundirPessoa(Contato contato, Login login) {
        try {
            this.pessoa.setLogin(login);
            this.pessoa.setContato(contato);
            pessoaDao.mergePessoa(pessoa);
            init();
            listarPessoa();
            mensagem("Pessoa criada com sucesso!", "");
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao criar a pessoa.", "");
            e.printStackTrace();
        }
    }

    public void atualizaPessoa() {
        pessoaDao.updatePessoa(pessoa);
        mensagem("Pessoa atualizada com sucesso!", "");
        pessoa = new Pessoa();
    }

    public void deletaPessoa(Pessoa pessoa) {
        pessoaDao.deletePessoa(pessoa);
        mensagem("Excluído com sucesso", "");
    }

    public List listarPessoa() {
        return listPessoa = pessoaDao.getListPessoa();
    }

    public String carregaPessoaId(int id) {
        this.pessoa = pessoaDao.getById(id);
        return "editar";
    }

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

    public int contador() {
        int total = 0;
        for (Pessoa obj : listPessoa) {
            total++;
        }
        return total;
    }

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

        Paragraph p = new Paragraph("Relatório de usuários registrados no sistema");
        p.setAlignment(Element.ALIGN_CENTER);
        pdf.add(p);
        pdf.add(Chunk.NEWLINE);

    }

}
