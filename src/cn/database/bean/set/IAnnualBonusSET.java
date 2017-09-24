/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.set;

import static cn.database.bean.interf.IAnnualBonus.ANNUAL_BONUS;
import static cn.database.bean.interf.IAnnualBonus.YEAR;
import cn.database.bean.impl.Employee;
import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;

/**
 *
 * @author MK
 */
public interface IAnnualBonusSET {

    @SET(name=ANNUAL_BONUS,type =SETType.AWARD )
    void setAward(Double award);
     @SET(name=EMPLOYEE_NAME,type =SETType.EMPLOYEE )
    void setEmployee(Employee employee);
    @SET(name=YEAR,type =SETType.STRING )
    void setYear(String year);
    
}
