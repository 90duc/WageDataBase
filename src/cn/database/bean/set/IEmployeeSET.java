/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.set;

import static cn.database.bean.interf.IEmployee.BIRTHDAY;
import static cn.database.bean.interf.IEmployee.HIRE_TIME;
import static cn.database.bean.interf.IEmployee.HIRE_TYPE;
import static cn.database.bean.interf.IEmployee.SEX;
import cn.database.bean.impl.Department;
import cn.database.bean.impl.Joblevel;
import static cn.database.bean.interf.IDepartment.DEPARTMENT_NAME;
import cn.database.bean.other.Sex;
import java.util.Date;
import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;

/**
 *
 * @author MK
 */
public interface IEmployeeSET {
    static final String [] COLUMNS={EMPLOYEE_NAME,BIRTHDAY,SEX,DEPARTMENT_NAME,HIRE_TYPE,HIRE_TIME};
    
    @SET(name=EMPLOYEE_NAME,type =SETType.STRING )
    void setName(String name);
    
    @SET(name=SEX,type =SETType.LIST )
    void setSex(Sex sex);
    
    @SET(name=BIRTHDAY,type =SETType.DATE )
    void setBirthday(Date birthday);
    
    @SET(name=DEPARTMENT_NAME,type =SETType.DEPARTMENT )
    void setDepartment(Department department);

    @SET(name=HIRE_TYPE,type =SETType.LIST )
    void setJoblevel(Joblevel joblevel);

    @SET(name=HIRE_TIME,type =SETType.DATE )
     void setHireTime(Date hireTime);

}
