package cn.database.bean.interf;

import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;
import cn.database.bean.other.IDate;
import cn.database.bean.other.ColumnName;

/**
 *
 * @author MK
 */
public interface IAttendance extends IDate{

     static final String ATTENDANCE_ID = "出勤号";
    // static final String EMPLOYEE_NAME = "员工名";
     static final String ATTENDANCE_MONTH = "月份";
     static final String LATE = "迟到次数";
     static final String SIGN = "签到次数";
     static final String ABSENCE = "缺席次数";
     static String[] COLUMNS ={ATTENDANCE_ID,EMPLOYEE_NAME,ATTENDANCE_MONTH,LATE,ABSENCE,SIGN};
      
    @ColumnName(name = ATTENDANCE_ID)
    public abstract Integer getId();
   

    @ColumnName(name = EMPLOYEE_NAME)
    public abstract String getEmployeeName();
    
    
    @ColumnName(name = ATTENDANCE_MONTH)
    public abstract String getMonth();
    
    
    @ColumnName(name = LATE)
    public abstract Integer getLate();
    
    @ColumnName(name = SIGN)
    public abstract Integer getSign();
    
    
    @ColumnName(name = ABSENCE)
    public abstract Integer getAbsence();
    


}
