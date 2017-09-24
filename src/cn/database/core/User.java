
package cn.database.core;

import cn.database.bean.impl.Department;
import cn.database.bean.impl.Employee;
import cn.database.dao.impl.EmployeeDao;
import cn.database.ui.operation.Result;

/**
 *
 * @author MK
 */
public class User {
    private static Integer EMPLOYEE;
    private static LoginType loginType;
   
    public static Result login(String  id,String name,LoginType loginType) {
        Result result=new Result();
       
        try {
             Employee e=EmployeeDao.dao.get(Integer.valueOf(id));
             if (e.getName().equals(name)) {
               result.result=true;
               User.EMPLOYEE=e.getId();
               User.loginType=loginType;
             }else{
                 result.result=false; 
                 result.text="姓名不正确";
             }
        } catch (Exception e) {
            result.result=false; 
            result.text=e.getMessage();
        }
       
      
        return result;      
    }
    
    public static void loginOut() {
       EMPLOYEE=null;
       loginType=null;
    }
    
    public static Employee getUser() {
        return EmployeeDao.dao.get(EMPLOYEE);
    }
    
    public static Department getDepartment() {
        return EmployeeDao.dao.get(EMPLOYEE).getDepartment();
    }
    
    public static Integer getDepartmentID() {
        Department d=EmployeeDao.dao.get(EMPLOYEE).getDepartment();
        return d.getId();
    }
    
    public static int getID() {
        return EMPLOYEE;
    }

    /**
     * @return the loginType
     */
    public static LoginType getLoginType() {
        return loginType;
    }

    public static boolean isManager() {
      return loginType==LoginType.MANAGER;
    }
}
