package Controllers;

import Models.Contato;
import Models.ContatoDAO;
import Models.Login;
import Models.LoginDAO;
import Models.Pessoa;
import Models.PessoaDAO;
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
    }

    public String carregaPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return "editar";
    }

    public void salvaPessoa(Contato contato, Login login) {
        try {
            this.pessoa.setLogin(login);
            this.pessoa.setContato(contato);
            pessoaDao.createPessoa(pessoa);
            mensagem("usuário cadastrado com Sucesso!", "");
            this.pessoa = new Pessoa();
            this.contato = new Contato();
            this.login = new Login();
        } catch (RuntimeException e) {
            erro("Ocorreu um erro ao cadastra o usuário.", "");
            e.printStackTrace();
        }
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

    public String carregaLoginId(int id) {
        this.pessoa = pessoaDao.getById(id);
        return "editar";
    }
}
