package Models;

import Util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author fridr
 */
public class PessoaDAO implements Serializable {

    private Session sessao;
    private Transaction trans;
    private List<Pessoa> list;

    public void createPessoa(Pessoa pessoa) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.save(pessoa);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }

    public void deletePessoa(Pessoa pessoa) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.delete(pessoa);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }

    public void updatePessoa(Pessoa pessoa) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.update(pessoa);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }

    public List<Pessoa> getListPessoa() {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Criteria cri = sessao.createCriteria(Pessoa.class);
            list = cri.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return list;
    }

    public Pessoa getById(int id) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Pessoa pessoa = new Pessoa();
            return pessoa = (Pessoa) sessao.get(Pessoa.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return null;
    }

    public Pessoa search(int id) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Criteria cri = sessao.createCriteria(Pessoa.class);
            cri.add(Restrictions.idEq(id));
            Pessoa resultado = (Pessoa) cri.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return null;
    }

    public void mergePessoa(Pessoa pessoa) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            sessao.merge(pessoa);
            trans.commit();
        } catch (PersistenceException e) {
            trans.rollback();
        } finally {
            sessao.close();
        }
    }

    public List<Pessoa> searchByNumSus(String numSus) {
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Criteria cri = sessao.createCriteria(Pessoa.class);
            cri.add(Restrictions.ilike("numSus", numSus, MatchMode.START));
            return cri.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessao.close();
        }
        return null;
    }
}
