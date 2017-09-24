/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.ui.choice;

import cn.database.bean.interf.IAllowance;
import cn.database.bean.interf.IEmployee;
import cn.database.bean.interf.IWage;
import cn.database.bean.impl.Allowance;
import cn.database.bean.impl.AnnualBonus;
import cn.database.bean.impl.Attendance;
import cn.database.bean.impl.Employee;
import cn.database.bean.impl.Wage;
import cn.database.bean.interf.IAnnualBonus;
import cn.database.dao.impl.AllowanceDao;
import cn.database.dao.impl.AttendanceDao;
import cn.database.dao.impl.EmployeeDao;
import cn.database.dao.impl.WageDao;
import cn.database.ui.RightFrame;
import java.util.List;
import cn.database.bean.interf.IAttendance;
import cn.database.core.User;
import cn.database.dao.impl.AnnualBonusDao;

/**
 *
 * @author MK
 */
public class PersonChoice implements IChoice{

    public static final String PERSON_INFO = "个人信息";
    public static final String WAGE_INFO = "工资信息";
    public static final String ATTENDENCE_INFO = "考勤信息";
    public static final String ALLOWANCE_INFO = "津贴信息";
    public static final String ANNUAL_INFO = "年终奖金";
    public static final String[] EMPLOYEE_OPERATION = {PERSON_INFO, WAGE_INFO,ATTENDENCE_INFO, ALLOWANCE_INFO,ANNUAL_INFO};

  
    @ChoiceName(name =PERSON_INFO)
    public void personInfo(String path) {
        Employee person = User.getUser();
       
        RightFrame.init(path, person,IEmployee.class);
    }
    @ChoiceName(name =ALLOWANCE_INFO)
    public void personAllowance(String path) {
    
        List<Allowance> attendances =AllowanceDao.dao.getByEmployee(User.getID());
        RightFrame.init(path, attendances, IAllowance.class, ChoiceAction::sortByDate);
    }
    
    @ChoiceName(name =ANNUAL_INFO)
    public  void personAnnual(String path) {
        List<AnnualBonus> attendances = AnnualBonusDao.dao.getByEmployee(User.getID());
        RightFrame.init(path, attendances, IAnnualBonus.class);
    }
    
    @ChoiceName(name =ATTENDENCE_INFO)
    public void personAttend(String path) {
        List<Attendance> attendances = AttendanceDao.dao.getByEmployee(User.getID());
        RightFrame.init(path, attendances, IAttendance.class, ChoiceAction::sortByMonth);

    }
    @ChoiceName(name =WAGE_INFO)
    public void personWage(String path) {
        List<Wage> attendances = WageDao.dao.getByEmployee(User.getID());
        RightFrame.init(path, attendances, IWage.class, ChoiceAction::sortByMonth);

    }

    

}
