/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class HorarioDAO implements Serializable{
    
    private Session sessao;
    private Transaction trans;
    private List<Horario> list;
    
    public void createHorario(Horario horario) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.save(horario);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
    
    public void deleteHorario(Horario horario) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.delete(horario);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
    
    public void updateHorario(Horario horario) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.update(horario);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }
    
    public List<Horario> getListHorario() {
        try{
        sessao = HibernateUtil.getSessionFactory().openSession();
        trans = sessao.beginTransaction();
        Criteria cri = sessao.createCriteria(Horario.class);
        list = cri.list();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally{
            sessao.close();
        }
        return list;
    }
    
        public Horario getById(int id){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Horario horario = new Horario();
            return horario = (Horario) sessao.get(Horario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sessao.close();
        }
        return null;
    }
    
    
}
