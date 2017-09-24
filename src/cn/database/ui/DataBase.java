
package cn.database.ui;

import cn.database.core.LoginType;
import cn.database.core.User;
import cn.database.ui.operation.Operation;
import cn.database.ui.operation.Result;
import cn.database.ui.res.Res;
import cn.database.util.HibernateUtil;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;


/**
 *
 * @author MK
 */
public class DataBase extends Application {
    
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        
        stage=primaryStage; 
        login();
        //User.login("2", "李思",LoginType.MANAGER);
       // initView();
        primaryStage.setTitle("工资管理系统");
        
           
        
        primaryStage.show();
        Timer  timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
             
                 HibernateUtil.getConfiguration();
                 timer.cancel();
              
            }
        }, 10);
        primaryStage.setOnCloseRequest(e ->{
             System.exit(0);
        });
       
       
  }
    public  void login() {
         GridPane gpane = new GridPane();
        Label label = new Label("员工号：");
        TextField textField = new TextField();
        Label label1 = new Label("姓名：");
        TextField textField1 = new TextField();
        gpane.add(label, 0, 0);
        gpane.add(textField, 1, 0);
        gpane.add(label1, 0, 1);
        gpane.add(textField1, 1, 1);
        gpane.getStyleClass().add("grid-pane");
        
        Button button = new Button("员工登录");
        button.setOnAction(e ->{
            Result result=User.login(textField.getText(), textField1.getText(),LoginType.EMPLOYEE);
            if(result.result)
               initView();
            else{
                Operation.setToolKit(button, result.text);
            }
        });
        
        Button button1 = new Button("管理员登录");
        button1.setOnAction(e ->{
            Result result=User.login(textField.getText(), textField1.getText(),LoginType.MANAGER);
            if(result.result)
               initView();
            else{
                Operation.setToolKit(button1, result.text);
            }
        });
        
        HBox hBox = new HBox();
        hBox.getChildren().addAll(button,button1);
        hBox.getStyleClass().add("login_buttton");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(gpane,hBox);
        vBox.getStyleClass().add("frame");
       
        Scene scene = new Scene(vBox, 780, 540);
       
        scene.getStylesheets().add(Res.LOGIN_CSS_PATH);
        stage.setScene(scene);
        stage.setResizable(false);
    }
    
    public  void initView() {
        
        WebView webView=new  WebView();
        webView.getStyleClass().add("web_wiew");
        webView.setMinWidth(400);
        
        WebEngine engine=  webView.getEngine();
        JSObject window = (JSObject) engine.executeScript("window");  
        URL url=DataBase.class.getResource("/cn/database/ui/res/home.html");
        engine.load(url.toString());
         
        window.setMember("app",this); 
        Label label = new Label(User.getUser().toString()+" |");
        label.getStyleClass().add("login_label");

        Button button = new Button("退出");
        button.setOnAction(e ->{
             loginOut();
        });
 
        VBox topHBox = new VBox(webView);
        topHBox.getStyleClass().add("top");
        
        ScrollPane scrollPane=new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(LeftFrame.build());
        scrollPane.getStyleClass().add("scrol_left");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(scrollPane,RightFrame.build());
       
        VBox mainVBox = new VBox();
        mainVBox.getChildren().addAll(topHBox,hBox);
        Scene scene =new Scene(mainVBox, 1100, 880);
        scene.getStylesheets().add(Res.VIEW_CSS_PATH);
        stage.setScene(scene);
        stage.setResizable(true);
    }
    
    public  void loginOut() {
        User.loginOut();
        login();
    }
    
    public String getUserName() {
        return User.getUser().toString();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
       launch(args);
    }
    
}

