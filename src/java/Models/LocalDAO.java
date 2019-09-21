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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

/**
 *
 * @author fridr
 */
public class LocalDAO implements Serializable {

    private Session sessao;
    private Transaction trans;
    private List<Local> list;

    public void createLocal(Local local) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.save(local);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }

    public void deleteLocal(Local local) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.delete(local);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }

    public void updateLocal(Local local) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.update(local);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }

    public List<Local> getListLocal() {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Criteria cri = sessao.createCriteria(Local.class);
            list = cri.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return list;
    }

    public Local getById(int id) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Local local = new Local();
            return local = (Local) sessao.get(Local.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return null;
    }

    public void mergeLocal(Local local) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.merge(local);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }

}
