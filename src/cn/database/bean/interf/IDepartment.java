/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.interf;

import cn.database.bean.other.ColumnName;

/**
 *
 * @author MK
 */
public interface IDepartment {
    public static final String DEPARTMENT_ID = "部门号";
    public static final String DEPARTMENT_NAME = "部门名";
    public static String[] COLUMNS ={DEPARTMENT_ID,DEPARTMENT_NAME};
    
    
    @ColumnName(name=DEPARTMENT_ID)
   public abstract Integer getId();
   
    @ColumnName(name=DEPARTMENT_NAME)
   public abstract String getName();
    
    
}
