
package cn.database.bean.interf;

import cn.database.bean.other.ColumnName;

/**
 *
 * @author MK
 */
public interface IJobLevel {
    
    static final String JOB_LEVEL = "工作类型号";
    static final String GRADE = "等级";
    static final String BASIC_PAY = "基本工资";
    static final String JOB_NAME = "工作名";
    static final String [] COLUMNS={JOB_LEVEL,JOB_NAME,GRADE,BASIC_PAY};
    
    @ColumnName(name = GRADE)
    Integer getGrade();
    
    
    @ColumnName(name = JOB_LEVEL)
    Integer getId();
    
    
    @ColumnName(name = BASIC_PAY)
    Double getSalary();
   
    
    @ColumnName(name = JOB_NAME)
    String getTypeName();
    
    
}
