/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.set;

import static cn.database.bean.interf.IAttendance.ABSENCE;
import static cn.database.bean.interf.IAttendance.LATE;
import cn.database.bean.impl.Employee;
import java.util.Date;
import static cn.database.bean.interf.IAttendance.SIGN;
import static cn.database.bean.interf.IAttendance.ATTENDANCE_MONTH;
import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;

/**
 *
 * @author MK
 */
public interface IAttendanceSET {

    @SET(name=ABSENCE,type =SETType.INT )
    void setAbsence(Integer absence);
    
    @SET(name=EMPLOYEE_NAME,type =SETType.EMPLOYEE )
    void setEmployee(Employee employee);
    @SET(name=LATE,type =SETType.INT )
    void setLate(Integer late);
    @SET(name=ATTENDANCE_MONTH,type =SETType.DATE )
    void setMonth(Date month);
    @SET(name=SIGN,type =SETType.INT)
    void setSign(Integer sign);
    
}
