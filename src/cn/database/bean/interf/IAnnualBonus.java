/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.interf;

import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;
import cn.database.bean.other.ColumnName;

/**
 *
 * @author MK
 */
public interface IAnnualBonus {
    static final String ANNUAL_ID = "年终奖号";
    static final String ANNUAL_BONUS = "年终奖";
  //  static final String EMPLOYEE_NAME = "员工名";
    static final String YEAR = "年";

    static String[] COLUMNS={ANNUAL_ID,EMPLOYEE_NAME,YEAR,ANNUAL_BONUS};
    
    @ColumnName(name = ANNUAL_ID)
    Integer getId();
  
    @ColumnName(name = ANNUAL_BONUS)
    Double getAward();
   
    @ColumnName(name = EMPLOYEE_NAME)
    String getEmployeeName();
    
    @ColumnName(name = YEAR)
    String getYear();   
    
}
