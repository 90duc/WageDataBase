
package cn.database.bean.get;

import cn.database.bean.impl.Employee;
import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;
import static cn.database.bean.interf.IWage.ALLOWANCE;
import static cn.database.bean.interf.IWage.MONTH;
import static cn.database.bean.interf.IWage.SALARY;
import static cn.database.bean.interf.IWage.WAGE_ID;
import cn.database.bean.other.IDate;
import java.util.Date;

/**
 *
 * @author MK
 */
public interface IWageGET extends IDate{
    
  
    @GET(name = ALLOWANCE)
    Double getAllowance();
   
    
    @GET(name = WAGE_ID)
    Integer getId();
    
    
    @GET(name = SALARY)
    Double getSalary();
    
    
    @GET(name = EMPLOYEE_NAME)
    Employee getEmployee();
    
    
    @GET(name = MONTH)
    Date getDate();
    
}
