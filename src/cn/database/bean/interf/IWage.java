
package cn.database.bean.interf;

import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;
import cn.database.bean.other.IDate;
import cn.database.bean.other.ColumnName;

/**
 *
 * @author MK
 */
public interface IWage extends IDate{
    
    static final String WAGE_ID = "工资号";
    //static final String EMPLOYEE_NAME = "员工名";
    static final String MONTH = "月份";
    static final String SALARY = "工资";
    static final String ALLOWANCE = "津贴";
    static final String [] COLUMNS={WAGE_ID,EMPLOYEE_NAME,MONTH,SALARY,ALLOWANCE};
    
    @ColumnName(name = ALLOWANCE)
    Double getAllowance();
   
    
    @ColumnName(name = WAGE_ID)
    Integer getId();
    
    
    @ColumnName(name = SALARY)
    Double getSalary();
    
    
    @ColumnName(name = EMPLOYEE_NAME)
    String getEmployeeName();
    
    
    @ColumnName(name = MONTH)
    String getMonth();
    
}
