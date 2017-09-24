/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.set;

import cn.database.bean.impl.Employee;
import static cn.database.bean.interf.IAllowance.ALLOWANCE_TYPE;
import static cn.database.bean.interf.IAllowance.WORK_TIME;
import java.util.Date;
import static cn.database.bean.interf.IAllowance.TIME;
import static cn.database.bean.interf.IAllowance.ALLOWANCE_PERK;
import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;

/**
 *
 * @author MK
 */
public interface IAllowanceSET {
   
    @SET(name=TIME,type =SETType.DATE)
    void setDay(Date day);
    
    @SET(name=EMPLOYEE_NAME,type =SETType.EMPLOYEE)
    void setEmployee(Employee employee);
    
    @SET(name=ALLOWANCE_PERK,type =SETType.DOUBLE)
    void setPerk(Double perk);

    @SET(name=ALLOWANCE_TYPE,type =SETType.STRING)
    void setType(String type);

    @SET(name=WORK_TIME,type =SETType.DOUBLE)
    void setWorkHours(Double workHours);
    
}
