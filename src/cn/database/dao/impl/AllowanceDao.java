
package cn.database.dao.impl;

import cn.database.bean.impl.Allowance;
import cn.database.core.DateUtil;
import cn.database.dao.Dao;
import cn.database.dao.IEmployeeDao;
import cn.database.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author MK
 */
public class AllowanceDao extends Dao<Allowance> implements IEmployeeDao<Allowance>{
    public static AllowanceDao dao =new AllowanceDao();
    
    public  List<Allowance> get(Integer employeeID, Date startDate,Date endDate) {
       Session session=HibernateUtil.getSession();
       String sql="from "+ Allowance.class.getSimpleName()+" as wage0 where wage0.employee.id="+employeeID+
               " and wage0.day>=\'"+DateUtil.parseDate(startDate)+"\'and wage0.day<=\'"+DateUtil.parseDate(endDate)+"\'";
        try {
             Query< Allowance> q= session.createQuery(sql,  Allowance.class);
               return  q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
       return null;
    }

    public double sum(int id, Date date) {
        Session session=HibernateUtil.getSession();
        double d=0;
        String sql="select sum(wage0.perk*wage0.workHours) from "+ Allowance.class.getSimpleName()+" as wage0 where wage0.employee.id="+id+
               " and wage0.day like \'"+DateUtil.parseMonth(date)+"%\'";
        try {
             Query q= session.createQuery(sql);
               d=(double)q.getSingleResult();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return d;
    }

}
