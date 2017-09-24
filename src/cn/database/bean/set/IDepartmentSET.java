/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.set;


import cn.database.bean.interf.IDepartment;

/**
 *
 * @author MK
 */
public interface IDepartmentSET {

    @SET(name=IDepartment.DEPARTMENT_NAME,type =SETType.STRING )
    void setName(String name);
    
}
