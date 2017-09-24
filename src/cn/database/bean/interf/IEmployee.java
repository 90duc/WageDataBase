/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.interf;

import static cn.database.bean.interf.IDepartment.DEPARTMENT_NAME;
import cn.database.bean.other.IDate;
import cn.database.bean.other.ColumnName;

/**
 *
 * @author MK
 */
public interface IEmployee extends IDate {
    static final String ID = "员工号";
    static final String EMPLOYEE_NAME = "员工名";
    static final String BIRTHDAY = "生日";
    static final String SEX = "性别";
   // static final String DEPARTMENT_NAME = "部门名";
    static final String HIRE_TIME = "雇佣时间";
    static final String HIRE_TYPE = "雇佣类型";
    static final String [] COLUMNS={ID,EMPLOYEE_NAME,SEX,BIRTHDAY,DEPARTMENT_NAME,HIRE_TYPE,HIRE_TIME};
    
    @ColumnName(name = ID)
    Integer getId();
  
    @ColumnName(name = EMPLOYEE_NAME)
    String getName();
    

    @ColumnName(name = BIRTHDAY)
    String getBirthDayString();
   

    @ColumnName(name = SEX)
    String getSexString();
    

    @ColumnName(name = DEPARTMENT_NAME)
    String getDepartmentName();
  

    @ColumnName(name = HIRE_TIME)
    String getHireTimeString();
    

    @ColumnName(name = HIRE_TYPE)
    String getJobLevelString();


    
    
}

