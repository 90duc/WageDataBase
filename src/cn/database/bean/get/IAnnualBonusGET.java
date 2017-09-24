/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.get;

import cn.database.bean.impl.Employee;
import static cn.database.bean.interf.IAnnualBonus.ANNUAL_BONUS;
import static cn.database.bean.interf.IAnnualBonus.ANNUAL_ID;
import static cn.database.bean.interf.IAnnualBonus.YEAR;
import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;


/**
 *
 * @author MK
 */
public interface IAnnualBonusGET {
  
    
    @GET(name = ANNUAL_ID)
    Integer getId();
  
    @GET(name = ANNUAL_BONUS)
    Double getAward();
   
    @GET(name = EMPLOYEE_NAME)
    Employee getEmployee();
    
    @GET(name = YEAR)
    String getYear();   
    
}
