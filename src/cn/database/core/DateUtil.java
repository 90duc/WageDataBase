/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MK
 */
public class DateUtil {
   static DateFormat monthFormat= new SimpleDateFormat("yyyy-MM");
   static DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
   public static Date parseMonth(String date) {
       try {
           return monthFormat.parse(date);
       } catch (Exception ex) {
          // Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }

   public static String parseMonth(Date date) {
       if (date==null) {
           return null;
       }
       return monthFormat.format(date);
      
   }
   public static String parseDate(Date date) {
       return dateFormat.format(date);
      
   }
   public static LocalDate toLocalDate(Date date){
       if (date==null) {
            return null;
       }
       if(date instanceof java.sql.Date){
           return ((java.sql.Date) date).toLocalDate();
       }
      return LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault())
              .toLocalDate();
   }
 
    public static Date toDate(LocalDate date) {
        if (date==null) {
            return null;
        }
        Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
    
    public static Date toMonth(LocalDate date) {
       try {
           if (date==null) {
               return null;
           }
           return monthFormat.parse(monthFormat.format(toDate(date)));
       } catch (ParseException ex) {
          // Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }

    public static Date parseDate(String date) {
       try {
           return dateFormat.parse(date);
       } catch (ParseException ex) {
           Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }
}
