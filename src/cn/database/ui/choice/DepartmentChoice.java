/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.ui.choice;

import cn.database.bean.impl.Allowance;
import cn.database.bean.impl.AnnualBonus;
import cn.database.bean.interf.IAttendance;
import cn.database.bean.interf.IDepartment;
import cn.database.bean.interf.IEmployee;
import cn.database.bean.interf.IWage;
import cn.database.bean.impl.Attendance;
import cn.database.bean.impl.Department;
import cn.database.bean.impl.Employee;
import cn.database.bean.impl.Wage;
import cn.database.bean.interf.IAllowance;
import cn.database.bean.interf.IAnnualBonus;
import cn.database.core.DateUtil;
import cn.database.core.User;
import cn.database.dao.impl.AllowanceDao;
import cn.database.dao.impl.AnnualBonusDao;
import cn.database.dao.impl.AttendanceDao;
import cn.database.dao.impl.DepartmentDao;
import cn.database.dao.impl.WageDao;
import cn.database.ui.RightFrame;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MK
 */
public class DepartmentChoice implements IChoice {

    public static final String DEPARTMENT_INFO = "部门信息";
    public static final String DEPARTMENT_EMPLOYEE = "部门员工";
    public static final String DEPARTMENT_WAGE = "部门工资";
    public static final String DEPARTMENT_ATTEND = "部门考勤";
    public static final String DEPARTMENT_ALLOWANCE = "部门津贴";
    public static final String DEPARTMENT_ANNUAL = "部门年终奖";
    public static final String[] DEPARTMENT_OPERATION = {DEPARTMENT_INFO, DEPARTMENT_EMPLOYEE, DEPARTMENT_ATTEND, DEPARTMENT_ALLOWANCE, DEPARTMENT_WAGE, DEPARTMENT_ANNUAL};

    @ChoiceName(name = DEPARTMENT_INFO)
    public void departmentInfo(String path) {
        Department person = User.getDepartment();

        RightFrame.init(path, person, IDepartment.class);
    }

    @ChoiceName(name = DEPARTMENT_EMPLOYEE)
    public void departmentEployee(String path) {
        Department person = User.getDepartment();
        RightFrame.init(path, person.getName(), person.getEmployees(), IEmployee.class);
    }

    @ChoiceName(name = DEPARTMENT_WAGE)
    public void departmentWage(String path) {
        Department person = User.getDepartment();
        WageDao dao = new WageDao();
        List<Wage> wages = new ArrayList<>();
       
        for (Object object : person.getEmployees()) {
            Employee e = (Employee) object;
            List<Wage> w = dao.getByEmployee(e.getId());
            if (w != null) {
                wages.addAll(w);
            }
        }
        RightFrame.init(path, person.getName(), wages, IWage.class, ChoiceAction::sortByMonth);
    }

    @ChoiceName(name = DEPARTMENT_ATTEND)
    public void departmentAttend(String path) {
        Department person = User.getDepartment();
        AttendanceDao dao = new AttendanceDao();
        List<Attendance> wages = new ArrayList<>();
       // Date date = DateUtil.parseDate("2017-1-1");
        for (Object object : person.getEmployees()) {
            Employee e = (Employee) object;
            List<Attendance> w = dao.getByEmployee(e.getId());
            if (w != null) {
                wages.addAll(w);
            }
        }
        RightFrame.init(path, person.getName(), wages, IAttendance.class, ChoiceAction::sortByMonth);

    }

    @ChoiceName(name = DEPARTMENT_ALLOWANCE)
    public void departmentAllowance(String path) {
        Department person = User.getDepartment();
        AllowanceDao dao = new AllowanceDao();
        List<Allowance> wages = new ArrayList<>();
       
        for (Object object : person.getEmployees()) {
            Employee e = (Employee) object;
            List<Allowance> w = dao.getByEmployee(e.getId());
            wages.addAll(w);
        }
        RightFrame.init(path, person.getName(), wages, IAllowance.class, ChoiceAction::sortByDate);

    }

    @ChoiceName(name = DEPARTMENT_ANNUAL)
    public void departmentAnnual(String path) {
        Department person =User.getDepartment();
        AnnualBonusDao dao = new AnnualBonusDao();
        List<AnnualBonus> wages = new ArrayList<>();
        Date date = new Date();
        for (Object object : person.getEmployees()) {
            Employee e = (Employee) object;
            List<AnnualBonus> w = dao.getByEmployee(e.getId());
            if (w != null) {
                wages.addAll(w);
            }
        }
        RightFrame.init(path, person.getName(), wages, IAnnualBonus.class);

    }
}
