/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.core;

import cn.database.bean.get.IAllowanceGET;
import cn.database.bean.get.IAnnualBonusGET;
import cn.database.bean.get.IAttendanceGET;
import cn.database.bean.get.IDepartmentGET;
import cn.database.bean.get.IEmployeeGET;
import cn.database.bean.get.IJoblevelGET;
import cn.database.bean.get.IWageGET;
import cn.database.bean.impl.Allowance;
import cn.database.bean.impl.AnnualBonus;
import cn.database.bean.impl.Attendance;
import cn.database.bean.impl.Department;
import cn.database.bean.impl.Employee;
import cn.database.bean.impl.Joblevel;
import cn.database.bean.impl.Wage;
import cn.database.bean.interf.IAllowance;
import cn.database.bean.interf.IAnnualBonus;
import cn.database.bean.interf.IAttendance;
import cn.database.bean.interf.IDepartment;
import cn.database.bean.interf.IEmployee;
import cn.database.bean.interf.IWage;
import cn.database.bean.set.IAllowanceSET;
import cn.database.bean.set.IAnnualBonusSET;
import cn.database.bean.set.IAttendanceSET;
import cn.database.bean.set.IDepartmentSET;
import cn.database.bean.set.IEmployeeSET;
import cn.database.bean.set.IJobLevelSET;
import cn.database.bean.set.IWageSET;
import cn.database.dao.Dao;
import cn.database.dao.impl.AllowanceDao;
import cn.database.dao.impl.AnnualBonusDao;
import cn.database.dao.impl.AttendanceDao;
import cn.database.dao.impl.DepartmentDao;
import cn.database.dao.impl.EmployeeDao;
import cn.database.dao.impl.JobLevelDao;
import cn.database.dao.impl.WageDao;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import cn.database.bean.interf.IJobLevel;

/**
 *
 * @author MK
 */
public class DataClass {

    public static final int BEAN = 0;
    public static final int DAO = 1;
    public static final int I_BEAN = 2;
    public static final int I_BEAN_SET = 3;
    public static final int I_BEAN_GET = 4;

    private static final Class[][] CLASSES = {
        {Employee.class, EmployeeDao.class,
            IEmployee.class, IEmployeeSET.class, IEmployeeGET.class},
        {Department.class, DepartmentDao.class,
            IDepartment.class, IDepartmentSET.class, IDepartmentGET.class},
        {Joblevel.class, JobLevelDao.class,
            IJobLevel.class, IJobLevelSET.class, IJoblevelGET.class},
        {Attendance.class, AttendanceDao.class,
            IAttendance.class, IAttendanceSET.class, IAttendanceGET.class},
        {Allowance.class, AllowanceDao.class,
            IAllowance.class, IAllowanceSET.class, IAllowanceGET.class},
        {Wage.class, WageDao.class,
            IWage.class, IWageSET.class, IWageGET.class},
        {AnnualBonus.class, AnnualBonusDao.class,
            IAnnualBonus.class, IAnnualBonusSET.class, IAnnualBonusGET.class}
    };

    public static Class get(Class clas, int srcIndex, int destIndex) {
        for (int i = 0; i < CLASSES.length; i++) {
            if (CLASSES[i][srcIndex] == clas||clas.getSuperclass()==CLASSES[i][srcIndex]) {
                return CLASSES[i][destIndex];
            }
        }
        throw new RuntimeException(clas.getSimpleName());
    }

     public static Class getBean(Class clas, int srcIndex) {
        for (int i = 0; i < CLASSES.length; i++) {
            if (CLASSES[i][srcIndex] == clas||clas.getSuperclass()==CLASSES[i][srcIndex]) {
                return CLASSES[i][BEAN];
            }
        }
        throw new RuntimeException(clas.getSimpleName());
    }
    public static <T extends Serializable> Dao<T> getDao(Class<T> beanClass) {
        try {
            for (int i = 0; i < CLASSES.length; i++) {
                if (CLASSES[i][BEAN] == beanClass||beanClass.getSuperclass()==CLASSES[i][BEAN]) {
                    Field f = CLASSES[i][DAO].getField(Dao.DAO_NAME);
                  return (Dao<T>)f.get(CLASSES[i][DAO]);
                }

            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new RuntimeException(beanClass.getSimpleName());
    }
    
    public static <T> T clone(T bean) {
        T newBean=null;
        try {
          newBean=(T) bean.getClass().newInstance();
            for (Field  field: bean.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                field.set(newBean, field.get(bean));
            }
  
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newBean;
    }
    
    public static String COLUMNS_NAME="COLUMNS";
    public static String[] getAttributeNames(Class IClass) {
        try {
            Field f = IClass.getField(COLUMNS_NAME);
            return (String[]) f.get(IClass);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(DataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int indexOf(String[] arr, String t) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }
}
