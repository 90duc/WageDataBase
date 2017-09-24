package cn.database.bean.get;


import cn.database.bean.impl.Employee;
import static cn.database.bean.interf.IAttendance.ABSENCE;
import static cn.database.bean.interf.IAttendance.ATTENDANCE_ID;
import static cn.database.bean.interf.IAttendance.LATE;
import static cn.database.bean.interf.IAttendance.SIGN;
import cn.database.bean.other.IDate;
import java.util.Date;
import static cn.database.bean.interf.IAttendance.ATTENDANCE_MONTH;
import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;

/**
 *
 * @author MK
 */
public interface IAttendanceGET extends IDate{

     
    @GET(name = ATTENDANCE_ID)
    public abstract Integer getId();
   

    @GET(name = EMPLOYEE_NAME)
    public abstract Employee getEmployee();
    
    
    @GET(name = ATTENDANCE_MONTH)
    public abstract Date getDate();
    
    
    @GET(name = LATE)
    public abstract Integer getLate();
    
    @GET(name = SIGN)
    public abstract Integer getSign();
    
    
    @GET(name = ABSENCE)
    public abstract Integer getAbsence();
    


}
