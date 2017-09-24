/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.get;

import cn.database.bean.impl.Department;
import cn.database.bean.impl.Joblevel;
import static cn.database.bean.interf.IDepartment.DEPARTMENT_NAME;
import static cn.database.bean.interf.IEmployee.BIRTHDAY;
import static cn.database.bean.interf.IEmployee.HIRE_TIME;
import static cn.database.bean.interf.IEmployee.HIRE_TYPE;
import static cn.database.bean.interf.IEmployee.ID;
import static cn.database.bean.interf.IEmployee.SEX;
import cn.database.bean.other.IDate;

import cn.database.bean.other.Sex;
import java.util.Date;
import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;

/**
 *
 * @author MK
 */
public interface IEmployeeGET extends IDate {
    
    @GET(name = ID)
    Integer getId();
  
    @GET(name = EMPLOYEE_NAME)
    String getName();
    

    @GET(name = BIRTHDAY)
    Date getBirthday();
   

    @GET(name = SEX)
    Sex getSexSex();
    

    @GET(name = DEPARTMENT_NAME)
    Department getDepartment();
  

    @GET(name = HIRE_TIME)
    Date getHireTime();
    

    @GET(name = HIRE_TYPE)
    Joblevel getJoblevel();


    
    
}

