package cn.database.dao;

import cn.database.util.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author MK
 * @param <T>
 */
public abstract class Dao<T extends Serializable> implements IDao<T> {

    protected final Class<T> entityClass;

    public Dao() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T get(Integer id) {
        Session session = HibernateUtil.getSession();

        T t = (T) session.get(entityClass, id);
     
        return t;
    }

    @Override
    public T load(java.lang.Integer id) {
        Session session = HibernateUtil.getSession();
      
        T t = (T) session.load(entityClass, id);
     
        return t;
    }

    @Override
    public boolean save(T t) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            session.save(t);
            session.getTransaction().commit();
           
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return false;
    }

    @Override
    public boolean saveOrUpdate(T t) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            session.saveOrUpdate(t);
            session.getTransaction().commit();
        
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return false;
    }

    @Override
    public boolean update(T t) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            session.update(t);
            session.getTransaction().commit();
          
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public boolean delete(T t) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        try {
            session.delete(t);
            session.getTransaction().commit();
          
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public List<T> list() {

        List<T> userList = null;
        Session session = HibernateUtil.getSession();
   
        try {
            Query query = session.createQuery("from " + entityClass.getSimpleName());
            userList = query.getResultList();
         
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return userList;
    }

    public Class getEntityClass() {
        return entityClass;
    }

}
