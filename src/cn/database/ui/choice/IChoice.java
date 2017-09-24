/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.ui.choice;

import cn.database.ui.RightFrame;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author MK
 */
public interface IChoice {

    public default void operation(String path, String op) {
        for (Method method : this.getClass().getDeclaredMethods()) {
            ChoiceName typeView = method.getDeclaredAnnotation(ChoiceName.class);
            if (typeView != null && op.equals(typeView.name())) {
                try {
                    method.invoke(this, new Object[]{path});
                } catch (Exception ex) {
                    //Logger.getLogger(PersonChoice.class.getName()).log(Level.SEVERE, null, ex);
                    RightFrame.init(path, ex);
                }
                break;
            }
        }

    }
}
