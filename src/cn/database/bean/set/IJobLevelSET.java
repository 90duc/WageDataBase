/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.set;

import static cn.database.bean.interf.IJobLevel.BASIC_PAY;
import static cn.database.bean.interf.IJobLevel.GRADE;
import static cn.database.bean.interf.IJobLevel.JOB_NAME;

/**
 *
 * @author MK
 */
public interface IJobLevelSET {
    
    @SET(name=GRADE,type =SETType.INT)        
    void setGrade(Integer grade);
    
    @SET(name=BASIC_PAY,type =SETType.DOUBLE)           
    void setSalary(Double salary);
    
    @SET(name=JOB_NAME,type =SETType.STRING)
    void setTypeName(String typeName);
    
}
