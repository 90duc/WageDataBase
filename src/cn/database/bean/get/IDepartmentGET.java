/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.get;

import static cn.database.bean.interf.IDepartment.DEPARTMENT_ID;
import static cn.database.bean.interf.IDepartment.DEPARTMENT_NAME;


/**
 *
 * @author MK
 */
public interface IDepartmentGET {
 
    @GET(name=DEPARTMENT_ID)
   public abstract Integer getId();
   
    @GET(name=DEPARTMENT_NAME)
   public abstract String getName();
    
    
}
