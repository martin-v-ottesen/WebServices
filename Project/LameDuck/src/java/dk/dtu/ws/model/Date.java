/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.model;

import org.joda.time.DateTime;

/**
 *
 * @author jacobmulvad
 */
public class Date {
    private int year;
    private int month;
    private int day;
    
    public Date(){
    }
    
    public Date(org.joda.time.DateTime date){
        this.year = date.getYear();
        this.month = date.getMonthOfYear();
        this.day = date.getDayOfMonth();
    }
    
    public Date(int year, int month, int day){
        setYear(year);
        setMonth(month);
        setDay(day);
    }
    
    public int getYear(){
        return this.year;
    }
    
    public void setYear(int year){
        if(year > 2000){
            this.year = year;
        } else this.year = 2015;
    }
    
    public int getMonth(){
        return this.month;
    }
    
    public void setMonth(int month){
        if(month > 0 && month <= 12){
            this.month = month;
        } else this.month = 1;
    }
    
    public int getDay(){
        return this.day;
    }
    
    public void setDay(int day){
        if(day > 0 && day <= 31){
            this.day = day;
        } else this.day = 1;
    }
    
    public DateTime getJodaTime(Date date){
        org.joda.time.DateTime dateAndTime = new DateTime(date.year, date.month, date.day, 12, 0, 0, 0);
        
        return dateAndTime;
    }
}
