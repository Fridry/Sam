package Models;

import Util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fridr
 */
public class AgendamentoDAO implements Serializable{
    
    private Session sessao;
    private Transaction trans;
    private List<Agendamento> list;
    
    public void createAgendamento(Agendamento agendamento) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.save(agendamento);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
    
    public void deleteAgendamento(Agendamento agendamento) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.delete(agendamento);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
    
    public void updateAgendamento(Agendamento agendamento) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.update(agendamento);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
    
    public List<Agendamento> getListAgendamento() {
        try{
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        Criteria cri = sessao.createCriteria(Agendamento.class);
        list = cri.list();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally{
            sessao.close();
        }
        return list;
    }
    
        public Agendamento getById(int id){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Agendamento agendamento = new Agendamento();
            return agendamento = (Agendamento) sessao.get(Agendamento.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sessao.close();
        }
        return null;
    }
    
    
}
