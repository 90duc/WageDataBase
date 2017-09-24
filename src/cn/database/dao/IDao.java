
package cn.database.dao;

import java.util.List;

/**
 *
 * @author MK
 * @param <T>
 */
public interface IDao<T> {
     public static final String DAO_NAME ="dao";
     
     public T get(Integer id);
     public T load(Integer id);
     public boolean save(T t);
     public boolean saveOrUpdate(T t);
     public boolean update(T t);
     public boolean delete(T t);     
     public List<T> list();
}
