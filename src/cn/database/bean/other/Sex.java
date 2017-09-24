/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.bean.other;

import java.util.NoSuchElementException;

/**
 *
 * @author MK
 */
public enum Sex {
    FEMALE("女","female"),MALE("男","male"),OTHER("其他","ohter");

   
    
    private final String name ;
    private final String value ;
    private Sex(String name,String value){
        this.name=name;
        this.value=value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    
    public static String getName(String value){
        for (Sex sex : Sex.values()) {
            if (sex.value.equals(value)) {
                return sex.name;
            }
        }
        return null;
    }
    
    public static String getValue(String name){
        for (Sex sex : Sex.values()) {
            if (sex.name.equals(name)) {
                return sex.value;
            }
        }
        return null;
    }

    public static Sex valueTo(String value) {
        for (Sex sex : Sex.values()) {
            if (sex.value.equals(value)) {
                return sex;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return  name ;
    }
    
    
}
