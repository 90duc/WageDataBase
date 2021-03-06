package cn.database.bean.impl;
// Generated 2017-5-23 16:31:18 by Hibernate Tools 4.3.1


import cn.database.bean.get.IWageGET;
import cn.database.bean.set.IWageSET;
import java.util.Date;
import cn.database.bean.interf.IWage;
import cn.database.core.DateUtil;

/**
 * Wage generated by hbm2java
 */
public class Wage  implements java.io.Serializable, IWage, IWageSET, IWageGET {


     private Integer id;
     private Employee employee;
     private String month;
     private Double salary;
     private Double allowance;

    public Wage() {
    }

    public Wage(Employee employee, String month, Double salary, Double allowance) {
       this.employee = employee;
       this.month = month;
       this.salary = salary;
       this.allowance = allowance;
    }
   
    @Override
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Employee getEmployee() {
        return this.employee;
    }
    
    @Override
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
     @Override
    public String getMonth() {
        return this.month;
    }
  
    public void setMonth(String month) {
        this.month = month;
    }
    @Override
    public Double getSalary() {
        return this.salary;
    }
    
    @Override
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    @Override
    public Double getAllowance() {
        return this.allowance;
    }
    
    @Override
    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    @Override
    public String getEmployeeName() {
        return  employee==null?"":employee.getName();
    }
   
    @Override
    public Date getDate() {
       return DateUtil.parseMonth(month);
    }

    @Override
    public void setMonth(Date month) {
      this.month=DateUtil.parseMonth(month);
    }




}


