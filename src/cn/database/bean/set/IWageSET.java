/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.set;

import static cn.database.bean.interf.IWage.ALLOWANCE;
import static cn.database.bean.interf.IWage.MONTH;
import static cn.database.bean.interf.IWage.SALARY;
import cn.database.bean.impl.Employee;
import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;
import java.util.Date;

/**
 *
 * @author MK
 */
public interface IWageSET {
    @SET(name=ALLOWANCE,type =SETType.ALLOWANCE)
    void setAllowance(Double allowance);

     @SET(name=EMPLOYEE_NAME,type =SETType.EMPLOYEE)
    void setEmployee(Employee employee);

     @SET(name=MONTH,type =SETType.DATE)
    void setMonth(Date month);

    @SET(name=SALARY,type =SETType.WAGE)
    void setSalary(Double salary);
    
}
