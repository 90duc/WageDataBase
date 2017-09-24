/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.ui;

import cn.database.core.User;
import cn.database.ui.choice.DepartmentChoice;
import cn.database.ui.choice.ManagerChoice;
import cn.database.ui.choice.ChoiceAction;
import cn.database.ui.choice.PersonChoice;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author MK
 */
public class LeftFrame {
    
    static final String[]   EMPLOYEE_TITLE_NAME ={ChoiceAction.EMPLOYEE_MENU,ChoiceAction.DEPARTMENT_MENU};
    static final String[][] EMPLOYEE_TITLE_INFO = {PersonChoice.EMPLOYEE_OPERATION, DepartmentChoice.DEPARTMENT_OPERATION};
    
    static final String[][] MANAGER_TITLE_INFO = {PersonChoice.EMPLOYEE_OPERATION, DepartmentChoice.DEPARTMENT_OPERATION, ManagerChoice.MANAGER_OPERATION};
    static TitledPane[] titledPanes;
   
    public static Pane build() {

        Label menuLabel = new Label("功能菜单");
      
        menuLabel.getStyleClass().add("menulabel");
        String[][] titles ;
        String[] titleNames ;
        if (User.isManager()) {
            titles=MANAGER_TITLE_INFO;
            titleNames=ChoiceAction.TITLE_NAMES;
        }else{
            titles=EMPLOYEE_TITLE_INFO;
            titleNames=EMPLOYEE_TITLE_NAME;
        }
        titledPanes = new TitledPane[titles.length];
        for (int i = 0; i < titles.length; i++) {
            titledPanes[i] = createTitlePane(titleNames[i], titles[i]);
        }

        VBox menuVBox = new VBox();
        menuVBox.getStyleClass().add("left");
        menuVBox.getChildren().addAll(titledPanes);

        VBox leftVBox = new VBox();
        //leftVBox.getStyleClass().add("left");
        leftVBox.getChildren().addAll(menuLabel, menuVBox);
        return leftVBox;
    }

    private static TitledPane createTitlePane(String title, String[] ops) {
        // titledPane.getStyleClass().add("title_text");
        TitledPane titledPane = new TitledPane();
        VBox contentVBox = new VBox();
        Label[] labels = new Label[ops.length];
        for (int i = 0; i < ops.length; i++) {
            String op = ops[i];
            Label label = new Label(ops[i]);
            labels[i]=label;
            label.getStyleClass().add("choice_button");
            label.setOnMouseClicked(e -> {
                ChoiceAction.operation(title, op);
                 for (Label aLabel : labels) {
                        aLabel.getStyleClass().remove("fouse_button");
                    } 
                 // if(!titledPane.getStyleClass().contains("fouse_button"))
                  label.getStyleClass().add("fouse_button");
            });
         
            contentVBox.getChildren().add(label);
        }
        contentVBox.getStyleClass().add("no_padding");
       titledPane.expandedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
           //System.out.println(newValue);
           if (!newValue&&!titledPane.isFocused()) {
               for (Label label : labels) {
                   label.getStyleClass().remove("fouse_button");
               }
           }
        });
        titledPane.setText(title);
        titledPane.setContent(contentVBox);
        titledPane.setExpanded(false);
        titledPane.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                for (TitledPane pane : titledPanes) {
                    if (pane != titledPane) {
                        pane.setExpanded(false);                        
                        pane.getStyleClass().remove("foused_title_pane");
                    }                   
                }
                if(!titledPane.getStyleClass().contains("foused_title_pane"))
                  titledPane.getStyleClass().add("foused_title_pane");
                RightFrame.init(title);
            }
        });
        
        return titledPane;
    }

}
