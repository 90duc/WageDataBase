/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.ui.choice;

import cn.database.bean.impl.Allowance;
import cn.database.bean.impl.AnnualBonus;
import cn.database.bean.impl.Attendance;
import cn.database.bean.impl.Department;
import cn.database.bean.impl.Employee;
import cn.database.bean.impl.Joblevel;
import cn.database.bean.impl.Wage;
import cn.database.core.DataClass;
import cn.database.dao.Dao;
import cn.database.ui.DataBase;
import cn.database.ui.RightFrame;
import cn.database.ui.operation.Box;
import cn.database.ui.operation.Operation;
import java.io.Serializable;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static cn.database.ui.RightFrame.createTable;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author MK
 */
public class ManagerChoice implements IChoice {

    public static final String EMPLOYEE_MANAGE = "员工管理";
    public static final String DEPARTMENT_MANAGE = "部门管理";
    public static final String ATTENDENCE_MANAGE = "考勤管理";
    public static final String ALLOWANCE_MANAGE = "津贴管理";
    public static final String JOBLEVEL_MANAGE = "工种管理";
    public static final String WAGE_MANAGE = "工资管理";
    public static final String ANNUAL_MANAGE = "年终奖管理";
    
    public static final String[] MANAGER_OPERATION = {EMPLOYEE_MANAGE,
        DEPARTMENT_MANAGE, ATTENDENCE_MANAGE, ALLOWANCE_MANAGE,
        JOBLEVEL_MANAGE, WAGE_MANAGE,ANNUAL_MANAGE};

    @ChoiceName(name=EMPLOYEE_MANAGE)
    public  void employee(String path) {
        showView(path, Employee.class);
    }
    
    @ChoiceName(name=DEPARTMENT_MANAGE)
    public  void department(String path) {
        showView(path, Department.class);
    }
    
    @ChoiceName(name=ALLOWANCE_MANAGE)
    public  void allowance(String path) {
        showView(path, Allowance.class);
    }
    
    @ChoiceName(name=ATTENDENCE_MANAGE)
    public  void attendance(String path) {
        showView(path, Attendance.class);
    }
    
    @ChoiceName(name=JOBLEVEL_MANAGE)
    public  void jobLevel(String path) {
        showView(path, Joblevel.class);
    }
    
    @ChoiceName(name=WAGE_MANAGE)
    public  void wage(String path) {
        showView(path, Wage.class);
    }
    
    @ChoiceName(name=ANNUAL_MANAGE)
    public  void annual(String path) {
        showView(path, AnnualBonus.class);
    }
    
    public static<T extends Serializable> void showView(String path ,Class<T> clas) {
        Dao<T> dao =DataClass.getDao(clas);
        List<T> person = dao.list();
        Button addButton = new Button("添加"); 
        Button modifyButton = new Button("修改");
        
        Button deleteButton = new Button("删除");
        
        VBox rightVBox=RightFrame.getRightVBox();
        HBox hBox = new HBox(20,addButton,modifyButton,deleteButton);
        hBox.getStyleClass().add("operate_bar");
        rightVBox.getChildren().clear();
        Label label = new Label(path);
        Separator separator = new Separator();
        Class iClass=DataClass.get(clas, DataClass.BEAN, DataClass.I_BEAN);
        TableView<T> tableView=createTable(person,iClass);
        rightVBox.getChildren().addAll(label, separator, hBox,tableView); 
        
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
            Class setClass=DataClass.get(clas, DataClass.BEAN, DataClass.I_BEAN_SET);
            Box box= Operation.addOperation(setClass);
            box.setOnHidden(e1 ->{
                if (box.isSuccessState()) {
                    tableView.getItems().clear();
                    tableView.getItems().addAll(dao.list());
                }
            });
        });
        
        modifyButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
           T t= tableView.getSelectionModel().getSelectedItem();
           if(t==null){
               Operation.setToolKit(modifyButton, "没有选择数据行");
              return;
            }
            Box box= Operation.modifyOperation(t);
            box.setOnHidden(e1 ->{
                //if (box.isSuccessState()) {
                    tableView.getItems().clear();
                    tableView.getItems().addAll(dao.list());
               // }
            });
        });
        
        deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
           T employee= tableView.getSelectionModel().getSelectedItem();
           if(employee==null){
              Operation.setToolKit(deleteButton, "没有选择数据行");
              return;
            }
            Box box= Operation.deleteOperation(employee);
            box.setOnHidden(e1 ->{
                if (box.isSuccessState()) {
                    tableView.getItems().clear();
                    tableView.getItems().addAll(dao.list());
                }
            });
        });
    }
}
