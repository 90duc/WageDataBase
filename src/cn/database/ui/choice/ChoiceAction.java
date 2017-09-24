/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.ui.choice;

import cn.database.bean.other.IDate;
import cn.database.core.DateUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author MK
 */
public class ChoiceAction {

    public static final String EMPLOYEE_MENU = "员工菜单";
    public static final String DEPARTMENT_MENU = "部门菜单";
    public static final String MANAGER_MENU = "管理员菜单";
    public static final String[] TITLE_NAMES = {EMPLOYEE_MENU, DEPARTMENT_MENU, MANAGER_MENU};

    public static final IChoice[] OPERATIONS = new IChoice[TITLE_NAMES.length];

    static {
        OPERATIONS[0] = new PersonChoice();
        OPERATIONS[1] = new DepartmentChoice();
        OPERATIONS[2] = new ManagerChoice();
    }

    public static void operation(String title, String op) {
        for (int i = 0; i < TITLE_NAMES.length; i++) {
            if (TITLE_NAMES[i].equals(title)) {
                OPERATIONS[i].operation(getPath(title, op), op);
                break;
            }
        }
    }

    public static String getPath(String title, String op) {
        return title + ">" + op;
    }

    public static <T extends IDate> void sortByDate(List<T> list, TableView<T> table, LocalDate date1, LocalDate date2) {
        List<T> aList = null;
        Date date;
        if (date1 != null) {
            Date sDate = DateUtil.toDate(date1);
            aList = new ArrayList<>();
            for (T attendance : list) {
                date = attendance.getDate();
                if (date != null && date.compareTo(sDate) >= 0) {
                    aList.add(attendance);
                }
            }
            list = aList;
        }
        if (date2 != null) {
            Date eDate = DateUtil.toDate(date1);
            aList = new ArrayList<>();
            for (T attendance : list) {
                date = attendance.getDate();
                if (date != null && date.compareTo(eDate) <= 0) {
                    aList.add(attendance);
                }
            }
            list = aList;
        }

        table.getItems().clear();
        table.getItems().addAll(list);

    }
     public static <T extends IDate> void sortByMonth(List<T> list, TableView<T> table, LocalDate date1, LocalDate date2) {
        List<T> aList = null;
        Date date;
        if (date1 != null) {
            Date sDate = DateUtil.toMonth(date1);
            aList = new ArrayList<>();
            for (T attendance : list) {
                date = attendance.getDate();
                if (date != null && date.compareTo(sDate) >= 0) {
                    aList.add(attendance);
                }
            }
            list = aList;
        }
        if (date2 != null) {
            Date eDate = DateUtil.toDate(date1);
            aList = new ArrayList<>();
            for (T attendance : list) {
                date = attendance.getDate();
                if (date != null && date.compareTo(eDate) <= 0) {
                    aList.add(attendance);
                }
            }
            list = aList;
        }

        table.getItems().clear();
        table.getItems().addAll(list);

    }
}
