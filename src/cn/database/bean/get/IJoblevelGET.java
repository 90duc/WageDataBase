
package cn.database.bean.get;

import static cn.database.bean.interf.IJobLevel.BASIC_PAY;
import static cn.database.bean.interf.IJobLevel.GRADE;
import static cn.database.bean.interf.IJobLevel.JOB_LEVEL;
import static cn.database.bean.interf.IJobLevel.JOB_NAME;


/**
 *
 * @author MK
 */
public interface IJoblevelGET {
    
   
    @GET(name = GRADE)
    Integer getGrade();
    
    
    @GET(name = JOB_LEVEL)
    Integer getId();
    
    
    @GET(name = BASIC_PAY)
    Double getSalary();
   
    
    @GET(name = JOB_NAME)
    String getTypeName();
    
    
}
