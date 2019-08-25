package Controllers;

import Models.Login;
import Models.LoginDAO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Edwski
 */

@Named(value = "loginBean")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
    private Login login;
    private LoginDAO loginDao;
    private List<Login> listLogin;

    public LoginBean() {
        this.login = new Login();
        this.loginDao = new LoginDAO();
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

    public List<Login> getListLogin() {
        return listLogin;
    }

    public void setListLogin(List<Login> listLogin) {
        this.listLogin = listLogin;
    }
    
    public void mensagem(String summary, String detail) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
    
    public String carregaLogin(Login login){
        this.login = login;
        return "editar";     
    }
    
    public void salvaLogin(){
        loginDao.createLogin(login);
        mensagem("Login criado com Sucesso!", "");
        login = new Login();
    }
    
    public void atualizaLogin() {
        loginDao.updateLogin(login);
        mensagem("Login atualizado com sucesso!", "");
        login = new Login();       
    }
    
    public void deletaLogin(Login login) {
        loginDao.deleteLogin(login);
        mensagem("Excluído com sucesso", "");        
    }
    
    public List listarLogin(){
        return listLogin = loginDao.getListLogin();
    }
    
    public String carregaLoginId(int id) {
        this.login = loginDao.getById(id);
        return "editar";
    }
    
}
