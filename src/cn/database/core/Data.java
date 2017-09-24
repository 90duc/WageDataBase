package cn.database.core;

import static cn.database.bean.interf.IEmployee.HIRE_TYPE;
import static cn.database.bean.interf.IEmployee.SEX;
import cn.database.bean.impl.Department;
import cn.database.bean.impl.Employee;
import cn.database.bean.impl.Joblevel;
import static cn.database.bean.interf.IDepartment.DEPARTMENT_NAME;
import static cn.database.bean.interf.IEmployee.EMPLOYEE_NAME;
import cn.database.bean.other.ColumnName;
import cn.database.bean.other.Sex;
import cn.database.dao.impl.DepartmentDao;
import cn.database.dao.impl.EmployeeDao;
import cn.database.dao.impl.JobLevelDao;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author MK
 */
public class Data {

    @ColumnName(name = DEPARTMENT_NAME)
    public static List<Department> getDepartment() {
        return new DepartmentDao().list();
    }
    
    @ColumnName(name = EMPLOYEE_NAME)
    public static List<Employee> getEmployee() {
        return new EmployeeDao().list();
    }

    @ColumnName(name = SEX)
    public static List<Sex> getSex() {
        List<Sex> sexs = new ArrayList<>();
        for (Sex sex : Sex.values()) {
            sexs.add(sex);
        }
        return sexs;
    }

    @ColumnName(name = HIRE_TYPE)
    public static List<Joblevel> getJobLevel() {
        return new JobLevelDao().list();
    }

    public static List getList(String name) {
        try {
            for (Method method : Data.class.getDeclaredMethods()) {
                ColumnName columnName = method.getDeclaredAnnotation(ColumnName.class);
                if (columnName != null && columnName.name().equals(name)) {

                   return (List)method.invoke(Data.class, new Object[]{});

                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
