package cn.database.core;

import cn.database.bean.impl.Attendance;
import cn.database.bean.impl.Employee;
import cn.database.bean.impl.Joblevel;
import cn.database.bean.impl.Wage;
import cn.database.dao.impl.AllowanceDao;
import cn.database.dao.impl.AttendanceDao;
import cn.database.dao.impl.WageDao;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author MK
 */
public class EmployeeUtil {

    public static double getAllowance(Employee employee, Date date) {

         if (employee == null) {
            throw new RuntimeException("缺少员工");
        }
        
        if (date == null) {
            throw new RuntimeException("缺少日期");
        }
        
        return formatDouble(AllowanceDao.dao.sum(employee.getId(), date));

    }

    public static double getWage(Employee employee, Date date) {
        
        if (employee == null) {
            throw new RuntimeException("缺少员工");
        }
        
        if (date == null) {
            throw new RuntimeException("缺少日期");
        }
        
        Attendance attendance = AttendanceDao.dao.get(employee.getId(), date);
        if (attendance == null) {
            return 0.0;
        }
        double basic = employee.getJoblevel().getSalary();
        double day = basic / (attendance.getLate() + attendance.getSign() + attendance.getAbsence());
        return formatDouble(day * (attendance.getSign() + attendance.getLate() * 0.8));

    }

    public static double getAward(Employee employee, String year) {
         if (employee == null) {
            throw new RuntimeException("缺少员工");
        }
         try {
            Integer.parseInt(year);
        } catch (Exception e) {
            throw new RuntimeException("年错误");
        }
        
        
            
        
        
        return formatDouble(WageDao.dao.getAnnualBonus(employee.getId(), year));

    }

    public static double formatDouble(double d) {
        return (double) Math.round(d * 100) / 100;
    }
}
