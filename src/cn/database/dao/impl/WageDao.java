/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.dao.impl;

import cn.database.bean.impl.Wage;
import cn.database.core.DateUtil;
import cn.database.dao.Dao;
import cn.database.dao.IEmployeeDao;
import cn.database.util.HibernateUtil;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author MK
 */
public class WageDao extends Dao<Wage> implements IEmployeeDao<Wage>{

    public static WageDao dao =new WageDao();
    
    public Wage get(Integer employeeID, Date date) {
       Session session=HibernateUtil.getSession();
       String sql="from "+entityClass.getSimpleName()+" as wage0 where wage0.employee.id="+employeeID+" and wage0.month=\'"+DateUtil.parseMonth(date)+"\'";
        try {
             Query<Wage> q= session.createQuery(sql, Wage.class);
               return  q.getSingleResult();
        } catch (Exception e) {
           // e.printStackTrace();
        }
 
       return null;
    }

    public double getAnnualBonus(int id, String year) {
       Session session=HibernateUtil.getSession();
       String  hql ="select sum(wage0.salary+wage0.allowance)/12 from Wage wage0 where wage0.employee.id="+id+" and wage0.month like \'"+year+"%\'";
       double  d=0;
               
       try {
             Query q= session.createQuery(hql);
              d=  (double)q.getSingleResult();
        } catch (Exception e) {
           //e.printStackTrace();
        }
      return d;
    }

}
