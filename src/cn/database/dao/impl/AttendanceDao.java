/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.dao.impl;

import cn.database.bean.impl.Attendance;
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
public class AttendanceDao extends Dao<Attendance> implements IEmployeeDao<Attendance>{
    
    public static AttendanceDao dao =new AttendanceDao();
    
    public Attendance get(Integer id, Date date) {
      Session session=HibernateUtil.getSession();
       String sql="from "+Attendance.class.getSimpleName()+" as wage0 where wage0.employee.id="+id+" and wage0.month=\'"+DateUtil.parseMonth(date)+"\'";
        try {
             Query<Attendance> q= session.createQuery(sql, Attendance.class);
               return  q.getSingleResult();
        } catch (Exception e) {
           // e.printStackTrace();
        }
 
       return null;
    }


    
}
