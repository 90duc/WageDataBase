/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.ui.operation;

import cn.database.ui.DataBase;
import cn.database.ui.res.Res;
import java.io.Serializable;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author MK
 */
public class Box extends Stage {

    private final Scene scene;
    private final Button button = new Button();
    private final Button button1 = new Button("取消");
    private boolean state = false;

    public Box(String title, String text, Parent node) {
        button.setText(text);

        button1.setOnAction(e -> {
            this.close();
        });
        HBox hBox = new HBox(10, button, button1);
        hBox.getStyleClass().add("bottom");
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(node);
        borderPane.setBottom(hBox);
        borderPane.getStyleClass().add("stage_frame");
        scene = new Scene(borderPane);
        scene.getStylesheets().add(Res.STAGE_CSS_PATH);
        this.setTitle(title);
        this.setScene(scene);
        this.initOwner(DataBase.stage);
        this.initModality(Modality.APPLICATION_MODAL);

    }

     <T extends Serializable> void setAction(T bean, List<Node> nodes, Func<T> func) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
          
           Result result=func.apply(bean,nodes);
           if (result.result) {
                state = true;
                this.close();
            }else{
               Operation.setToolKit(button, result.text);
           }
        });

    }

    <T extends Serializable> void setAction(T bean,Func2<T> func) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Result result=func.apply(bean);
           if (result.result) {
                state = true;
                this.close();
            }else{
               Operation.setToolKit(button, result.text);
           }
            

        });

    }

    /**
     * @return the state
     */
    public boolean isSuccessState() {
        return state;
    }
}

@FunctionalInterface
interface Func2<T extends Serializable>{
     Result apply(T bean);
}

@FunctionalInterface
interface Func<T extends  Serializable> {

    /**
     *
     * @param setClass
     * @param nodes
     * @return
     */
    Result apply(T setClass,List<Node> nodes);
}
