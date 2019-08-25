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
public class EventoDAO implements Serializable{
    
    private Session sessao;
    private Transaction trans;
    private List<Evento> list;
    
    public void createEvento(Evento evento) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.save(evento);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
    
    public void deleteEvento(Evento evento) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.delete(evento);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
    
    public void updateEvento(Evento evento) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.update(evento);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
    
    public List<Evento> getListEvento() {
        try{
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        Criteria cri = sessao.createCriteria(Evento.class);
        list = cri.list();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally{
            sessao.close();
        }
        return list;
    }
    
        public Evento getById(int id){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Evento evento = new Evento();
            return evento = (Evento) sessao.get(Evento.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sessao.close();
        }
        return null;
    }
    
    
}
