/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.dao.impl;

import cn.database.bean.impl.AnnualBonus;
import cn.database.dao.Dao;
import cn.database.dao.IEmployeeDao;
import cn.database.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author MK
 */
public class AnnualBonusDao extends Dao<AnnualBonus> implements IEmployeeDao<AnnualBonus>{

    public static AnnualBonusDao dao =new AnnualBonusDao();
    
    public AnnualBonus get(Integer id, String year) {
      Session session=HibernateUtil.getSession();
       String sql="from "+ AnnualBonus.class.getSimpleName()+" as wage0 where wage0.employee.id="+id+
               " and wage0.year=\'"+year+"\'";
        try {
             Query<AnnualBonus> q= session.createQuery(sql,  AnnualBonus.class);
               return  q.getSingleResult();
        } catch (Exception e) {
           // e.printStackTrace();
        }
      return null;
    }


    
}
