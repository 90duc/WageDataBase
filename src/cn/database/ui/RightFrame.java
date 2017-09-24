package cn.database.ui;
;
import cn.database.ui.choice.Function;
import cn.database.ui.choice.ChoiceAction;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import cn.database.bean.other.ColumnName;
import cn.database.core.DataClass;
import javafx.scene.control.ScrollPane;

/**
 *
 * @author MK
 */
public class RightFrame {

    private static VBox rightVBox = new VBox();

    public static Pane build() {
        rightVBox.getChildren().clear();
        Label label = new Label("工资管理");
        rightVBox.getChildren().add(label);
        rightVBox.getStyleClass().add("right");
       
        return rightVBox;
    }

    public static <T> void init(String path, List< T> list, Class<? super T> t) {
        rightVBox.getChildren().clear();
        Label label = new Label(path);
        Separator separator = new Separator();
       rightVBox.getChildren().addAll(label, separator, createTable(list, t));
    }

    public static <T> void init(String path, String departmentName, Collection<T> list, Class<? super T> t) {
        rightVBox.getChildren().clear();
        Label label = new Label(path);
        Separator separator = new Separator();
        Label label1 = new Label(departmentName);
        rightVBox.getChildren().addAll(label, separator, label1, createTable(list, t));
    }

    public static <T> void init(String path, T t, Class<T> ct) {
        rightVBox.getChildren().clear();
        Label label = new Label(path);
        Separator separator = new Separator();
        VBox vBox = new VBox();
        for (Method method : ct.getDeclaredMethods()) {
            ColumnName typeView = method.getAnnotation(ColumnName.class);
            Label label1;
            try {
                label1 = new Label(typeView.name() + "："
                        + method.invoke(t, new Object[]{}));
                vBox.getChildren().add(label1);
            } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException ex) {
                Logger.getLogger(ChoiceAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        vBox.getStyleClass().add("white_back");
        rightVBox.getChildren().addAll(label, separator, vBox);

    }

    public static <T, V> TableView<T> createTable(Collection<T> list, Class< ? super T> IClass) {
        TableView<T> tableView = new TableView<>();
        try {
      
            String[] columnNames = DataClass.getAttributeNames(IClass);
            TableColumn<T, ?>[] columns = new TableColumn[columnNames.length];
            for (Method method : IClass.getDeclaredMethods()) {
                ColumnName typeView = method.getAnnotation(ColumnName.class);
                if (typeView != null) {
                    TableColumn<T, V> tableColumn = new TableColumn<>(typeView.name());
                    tableColumn.setMinWidth(100);
                    tableColumn.setCellValueFactory((TableColumn.CellDataFeatures<T, V> param) -> {
                        try {
                            return new SimpleObjectProperty<>((V) method.invoke(param.getValue(), new Object[]{}));
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            Logger.getLogger(RightFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return new SimpleObjectProperty<>();
                    });
                    columns[DataClass.indexOf(columnNames, typeView.name())] = tableColumn;
                }
            }
            tableView.getColumns().addAll(columns);

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(RightFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableView.getItems().addAll(list);
        return tableView;
    }

    public static <T> void init(String path, List<T> list, Class<? super T> t, Function<List<T>, TableView<T>, LocalDate> func) {

        rightVBox.getChildren().clear();
        Label label = new Label(path);
        Separator separator = new Separator();
        DatePicker datePicker = new DatePicker();
        DatePicker datePicker2 = new DatePicker();
        Button button = new Button("查询");
        TableView<T> tableView = createTable(list, t);
        button.setOnAction(e -> {
            func.sort(list, tableView, datePicker.getValue(), datePicker2.getValue());
        });
        HBox hBox = new HBox(10, new Label("起始日期"), datePicker, new Label("截止日期"), datePicker2, button);
        hBox.getStyleClass().add("date_hbox");
        rightVBox.getChildren().addAll(label, separator, hBox, tableView);
    }

    public static <T> void init(String path,String departmentName, List<T> list, Class<? super T> t, Function<List<T>, TableView<T>, LocalDate> func) {

        rightVBox.getChildren().clear();
        Label label = new Label(path);
        Separator separator = new Separator();
         Label label1 = new Label(departmentName);
        DatePicker datePicker = new DatePicker();
        DatePicker datePicker2 = new DatePicker();
        Button button = new Button("查询");
        TableView<T> tableView = createTable(list, t);
        button.setOnAction(e -> {
            func.sort(list, tableView, datePicker.getValue(), datePicker2.getValue());
        });
        HBox hBox = new HBox(10, new Label("起始日期"), datePicker, new Label("截止日期"), datePicker2, button);
        hBox.getStyleClass().add("date_hbox");
        rightVBox.getChildren().addAll(label, separator,label1, hBox, tableView);
    }
    /**
     * @return the rightVBox
     */
    public static VBox getRightVBox() {
        return rightVBox;
    }

    public static void init(String path, Exception ex) {
        rightVBox.getChildren().clear();
        Label label = new Label(path);
        Separator separator = new Separator();
        VBox vBox = new VBox();
        Label label1 = new Label("没有数据");
        vBox.getChildren().add(label1);
        vBox.getStyleClass().add("no_data");
        rightVBox.getChildren().addAll(label, separator, vBox);
    }

    static void init(String title) {
         rightVBox.getChildren().clear();
        Label label = new Label(title);
        rightVBox.getChildren().add(label);
    }

}
