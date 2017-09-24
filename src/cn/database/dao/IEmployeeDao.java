package cn.database.dao;



import cn.database.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author MK
 * @param <T>
 */
public interface IEmployeeDao<T> {

    public Class<T> getEntityClass();

    public default List<T> getByEmployee(int employeeID) {
        Session session = HibernateUtil.getSession();
        Class<T> entityClass = getEntityClass();
        String sql = "from " + entityClass.getSimpleName() + " as entity where entity.employee.id=" + employeeID;
        try {
            Query<T> q = session.createQuery(sql, entityClass);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
