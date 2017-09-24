/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.ui.operation;

import cn.database.bean.get.GET;
import cn.database.bean.impl.AnnualBonus;
import cn.database.bean.impl.Department;
import cn.database.bean.impl.Employee;
import cn.database.bean.impl.Wage;
import cn.database.bean.other.ColumnName;
import cn.database.core.DateUtil;
import cn.database.core.Data;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import cn.database.bean.set.SETType;
import cn.database.core.DataClass;
import java.io.Serializable;
import cn.database.bean.set.SET;
import cn.database.core.EmployeeUtil;
import static cn.database.core.EmployeeUtil.formatDouble;
import java.util.Date;
import javafx.geometry.Bounds;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;

/**
 *
 * @author MK
 */
public class Operation {

    public static <T, V extends Serializable> Box addOperation(Class<T> setClass) {
        List<Node> nodeLsit = new ArrayList<>();
        GridPane gpane = new GridPane();
        gpane.getStyleClass().add("grid-pane");
        String[] rows = DataClass.getAttributeNames(DataClass.get(setClass, DataClass.I_BEAN_SET, DataClass.I_BEAN));
        DatePicker datePicker=null;
        Node[] nodes=new Node[rows.length];
        for (Method method : setClass.getDeclaredMethods()) {
            SET set = method.getDeclaredAnnotation(SET.class);
            if (set != null) {
                Node node = null;
                switch (set.type()) {
                    case SETType.INT:
                    case SETType.DOUBLE:
                    case SETType.STRING:
                        // default:
                        node = new TextField();
                        break;

                    case SETType.DATE:
                        datePicker = new DatePicker();
                        node=datePicker;
                        break;

                    case SETType.LIST:
                    case SETType.EMPLOYEE:
                    case SETType.DEPARTMENT:
                        ComboBox cb = new ComboBox();
                        cb.getItems().addAll(Data.getList(set.name()));
                        node = cb;
                        break;
                    
                   /* case SETType.ALLOWANCE:
                      TextField  tf = new TextField();
                      tf.setEditable(false);
                      datePicker.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
                     
                      });
                      node=tf;
                        break;
                    case SETType.WAGE:
                      TextField  wtf = new TextField();
                      wtf.setEditable(false);
                      node=wtf;
                        break;
                    case SETType.AWARD:
                      TextField  atf = new TextField();
                      atf.setEditable(false);
                      node=atf;
                        break;*/
                }
                int row = DataClass.indexOf(rows, set.name());
                if (node != null) {
                    node.setUserData(method);
                    nodes[row]=node;
                    Label label = new Label(set.name());
                    
                    gpane.add(label, 0, row);
                    gpane.add(node, 1, row);
                }else{
                    node=new Label();
                    node.setUserData(method);
                    nodes[row]=node;
                }
            }

        }
        for (Node node : nodes) {
            if(node!=null)
            nodeLsit.add(node);
        }
        VBox vBox = new VBox(gpane);
        vBox.setAlignment(Pos.CENTER);
        Box box = new Box("添加", "添加", vBox);
        V bean = null;
        try {
            bean = (V) DataClass.getBean(setClass, DataClass.I_BEAN_SET).newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        box.setAction(bean, nodeLsit, Operation::save);
        box.show();
        return box;
    }

    static <T extends Serializable> Result save(T bean, List<Node> nodes) {
        Result result = new Result();
        SET set =null;
        try {
            for (Node node : nodes) {
                Method method = ((Method) node.getUserData());
                 set = method.getDeclaredAnnotation(SET.class);
                Object[] param = new Object[1];
                switch (set.type()) {
                    case SETType.INT:
                        String si = ((TextField) node).getText();
                        /*if (si.isEmpty()) {
                            continue;
                        }*/
                        param[0] = Integer.valueOf(si);
                        break;

                    case SETType.DOUBLE:
                        String sd = ((TextField) node).getText();
                        /*if (sd.isEmpty()) {
                            continue;
                        }*/
                        param[0] = formatDouble(Double.valueOf(sd));
                        break;

                    case SETType.STRING:
                    default:
                        String s = ((TextField) node).getText();
                        if (s == null || s.isEmpty()) {
                           throw new RuntimeException("缺少");
                        }
                        param[0] = s;

                        break;

                    case SETType.DATE:
                        LocalDate date = ((DatePicker) node).getValue();
                        param[0] = DateUtil.toDate(date);
                        break;

                    case SETType.LIST:
                    case SETType.EMPLOYEE:
                        Object value = ((ComboBox) node).getValue();
                        param[0] = value;

                        break;
                    case SETType.DEPARTMENT:
                        Department department = (Department) ((ComboBox) node).getValue();
                        department.getEmployees().add(bean);
                        param[0] = department;

                        break;
                    case SETType.ALLOWANCE:
                        Wage w = (Wage) bean;
                        param[0] = EmployeeUtil.getAllowance(w.getEmployee(), w.getDate());

                        break;
                    case SETType.WAGE:
                        Wage w1 = (Wage) bean;
                        param[0] = EmployeeUtil.getWage(w1.getEmployee(), w1.getDate());

                        break;
                    case SETType.AWARD:
                        AnnualBonus an = (AnnualBonus) bean;
                        param[0] = EmployeeUtil.getAward(an.getEmployee(), an.getYear());
                        break;
                }
                method.invoke(bean, param);
            }

            result.result = DataClass.getDao((Class<T>) bean.getClass()).save(bean);
            if (!result.result) {
                result.text = "更新失败";
            }

        } catch (Exception ex) {
            result.result = false;
            result.text = set.name()+" "+ex.getLocalizedMessage();
            
          // Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static < V extends Serializable> Box modifyOperation(V bean) {

        List<Node> nodes = new ArrayList<>();
        GridPane gpane = new GridPane();
        gpane.getStyleClass().add("grid-pane");

        try {
            Class iClass = DataClass.get(bean.getClass(), DataClass.BEAN, DataClass.I_BEAN);
            Method m = iClass.
                    getDeclaredMethod("getId", new Class[]{});
            ColumnName columnName = m.getDeclaredAnnotation(ColumnName.class);

            Label idLabel = new Label(columnName.name());
            TextField idField = new TextField();
            idField.setEditable(false);
            idField.setText(m.invoke(bean, new Object[]{}).toString());

            gpane.add(idLabel, 0, 0);
            gpane.add(idField, 1, 0);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] rows = DataClass.getAttributeNames(DataClass.get(bean.getClass(), DataClass.BEAN, DataClass.I_BEAN));

        Class setClass = DataClass.get(bean.getClass(), DataClass.BEAN, DataClass.I_BEAN_SET);
        Class getClass = DataClass.get(bean.getClass(), DataClass.BEAN, DataClass.I_BEAN_GET);
        Method[] getMethods = getClass.getDeclaredMethods();
        for (Method method : setClass.getDeclaredMethods()) {
            SET set = method.getDeclaredAnnotation(SET.class);
            if (set != null) {
                Label label = new Label(set.name());
                Object value = getValue(bean, getMethods, set.name());
                Node node;
                switch (set.type()) {
                    case SETType.INT:
                    case SETType.DOUBLE:
                    default:
                        TextField vtf = new TextField();
                        if (value != null) {
                            vtf.setText(value.toString());
                        }
                        node = vtf;
                        break;

                    case SETType.STRING:
                    
                        TextField tf = new TextField();
                        tf.setText((String) value);
                        node = tf;
                        break;

                    case SETType.DATE:            
                        DatePicker dp = new DatePicker();
                        dp.setValue(DateUtil.toLocalDate((Date) value));
                        node = dp;
                        break;

                    case SETType.LIST:
                    case SETType.EMPLOYEE:
                    case SETType.DEPARTMENT:
                        ComboBox cb = new ComboBox();
                        cb.getItems().addAll(Data.getList(set.name()));
                        cb.setValue(value);
                        node = cb;
                        break;
                }
                node.setUserData(method);
                nodes.add(node);
                int row = DataClass.indexOf(rows, set.name());
                gpane.add(label, 0, row);
                gpane.add(node, 1, row);

            }

        }
        VBox vBox = new VBox(gpane);
        vBox.setAlignment(Pos.CENTER);
        Box box = new Box("修改", "更新", vBox);

        box.setAction(bean, nodes, Operation::update);
        box.show();
        return box;

    }
 static <T extends Serializable> Result update(T bean, List<Node> nodes) {
       Result result=new Result();
       SET set=null;
        try {
            for (Node node : nodes) {
                Method method = ((Method) node.getUserData());
                set = method.getDeclaredAnnotation(SET.class);
                Object[] param = new Object[1];
                switch (set.type()) {
                    case SETType.INT:
                        String si = ((TextField) node).getText();
                        /*if (si .isEmpty()) {
                            continue;
                        }*/
                        param[0] = Integer.valueOf(si);
                        break;
                    
                    case SETType.DOUBLE:
                    case SETType.ALLOWANCE:
                    case SETType.WAGE:
                    case SETType.AWARD:                   
                        String sd = ((TextField) node).getText();
                        /*if (sd .isEmpty()) {
                            continue;
                        }*/
                        param[0] = formatDouble(Double.valueOf(sd));
                        break;

                    case SETType.STRING:
                    default:
                        String s = ((TextField) node).getText();
                        if (s == null || s.isEmpty()) {
                            throw new RuntimeException("缺少");
                        }
                        param[0] = s;

                        break;

                    case SETType.DATE:
                        LocalDate date = ((DatePicker) node).getValue();
                        param[0] = DateUtil.toDate(date);
                        break;
                        
                    case SETType.LIST:
                    case SETType.EMPLOYEE:
                        Object value = ((ComboBox) node).getValue();
                        /*if (value == null) {
                            continue;
                        }*/
                        param[0] = value;

                        break;
                     case SETType.DEPARTMENT:
                        ((Employee)bean).getDepartment().getEmployees().remove(bean);
                        Department department = (Department) ((ComboBox) node).getValue();
                        department.getEmployees().add(bean);
                        param[0] = department;
                }
                method.invoke(bean, param);
            }

           
            result.result=DataClass.getDao((Class<T>) bean.getClass()).update(bean);
            if (!result.result) {
                result.text="更新失败";
            }
            
        } catch (Exception ex) {
           result.result=false;
           result.text=set.name() +" "+ex.getLocalizedMessage() ;
           //Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private static Object getValue(Object bean, Method[] methods, String op) {
        Object o = null;
        for (Method method : methods) {
            GET get = method.getDeclaredAnnotation(GET.class);
            if (get != null && get.name().equals(op)) {
                try {
                    o = method.invoke(bean, new Object[]{});
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return o;
    }

    public static < V extends Serializable> Box deleteOperation(V bean) {

        GridPane gpane = new GridPane();
        gpane.getStyleClass().add("grid-pane");

        Class iClass = DataClass.get(bean.getClass(), DataClass.BEAN, DataClass.I_BEAN);
        String[] rows = DataClass.getAttributeNames(DataClass.get(bean.getClass(), DataClass.BEAN, DataClass.I_BEAN));

        for (Method method : iClass.getDeclaredMethods()) {
            ColumnName set = method.getDeclaredAnnotation(ColumnName.class);
            if (set != null) {

                Label label = new Label(set.name());
                Object value = null;
                try {
                    value = method.invoke(bean, new Object[]{});
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
                }
                TextField node = new TextField();
                node.setEditable(false);
                if (value != null) {
                    node.setText(value.toString());
                }
                int row = DataClass.indexOf(rows, set.name());
                gpane.add(label, 0, row);
                gpane.add(node, 1, row);

            }

        }
        VBox vBox = new VBox(gpane);
        vBox.setAlignment(Pos.CENTER);
        Box box = new Box("删除", "确定", vBox);
        box.setAction(bean, Operation::delete);
        box.show();
        return box;

    }

    static <T extends Serializable> Result delete(T bean) {
        Result result = new Result();
        result.result = DataClass.getDao((Class<T>) bean.getClass()).delete(bean);
        result.text = "数据被引用";
        return result;
    }
    private static final Tooltip TOOLTIP = new Tooltip();

    public static void setToolKit(Control node, String tip) {
        TOOLTIP.setText(tip);
        Bounds bounds = node.localToScreen(node.getBoundsInLocal());
        TOOLTIP.show(node, bounds.getMinX(), bounds.getMaxY() + 20);
        TOOLTIP.setAutoHide(true);
    }
}
