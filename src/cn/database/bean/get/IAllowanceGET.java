/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.get;



import cn.database.bean.impl.Employee;
import static cn.database.bean.interf.IAllowance.ALLOWANCE_ID;
import static cn.database.bean.interf.IAllowance.ALLOWANCE_TYPE;
import static cn.database.bean.interf.IAllowance.WORK_TIME;
import cn.database.bean.other.IDate;
import static cn.database.bean.interf.IAllowance.TIME;
import java.util.Date;
import static cn.database.bean.interf.IAllowance.ALLOWANCE_PERK;
import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;

/**
 *
 * @author MK
 */
public interface IAllowanceGET extends IDate{
    
  
    
   @GET(name = ALLOWANCE_ID)
   public abstract Integer getId();
   
    
    @GET(name = EMPLOYEE_NAME)
   public abstract Employee getEmployee();
    
    
    @GET(name = TIME)
    public abstract Date getDay();
   
    
    @GET(name = ALLOWANCE_TYPE)
    public abstract String getType();
    
    
    @GET(name = WORK_TIME)
    public abstract Double getWorkHours();
    
    
    @GET(name = ALLOWANCE_PERK)
    public abstract Double getPerk();
    
    
  
}
