/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.interf;

import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;
import cn.database.bean.other.IDate;
import cn.database.bean.other.ColumnName;

/**
 *
 * @author MK
 */
public interface IAllowance extends IDate{
    
   public static final String ALLOWANCE_ID = "津贴号";
  // public static final String EMPLOYEE_NAME = "员工名";
   public static final String TIME = "日期";
   public static final String ALLOWANCE_TYPE = "津贴类型";
   public static final String WORK_TIME = "工作时长";
   public static final String ALLOWANCE_PERK = "津贴";
   
   public static String[] COLUMNS ={ALLOWANCE_ID,EMPLOYEE_NAME,
        TIME,ALLOWANCE_TYPE,WORK_TIME,ALLOWANCE_PERK};
   
    
   @ColumnName(name = ALLOWANCE_ID)
   public abstract Integer getId();
   
    
    @ColumnName(name = EMPLOYEE_NAME)
   public abstract String getEmployeeName();
    
    
    @ColumnName(name = TIME)
    public abstract String getDateString();
   
    
    @ColumnName(name = ALLOWANCE_TYPE)
    public abstract String getType();
    
    
    @ColumnName(name = WORK_TIME)
    public abstract Double getWorkHours();
    
    
    @ColumnName(name = ALLOWANCE_PERK)
    public abstract Double getPerk();
    
    
  
}
interface  Func<T>{
    T get();
}