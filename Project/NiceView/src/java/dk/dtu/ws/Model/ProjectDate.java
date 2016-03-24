/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.Model;

import java.util.Arrays;
import org.joda.time.DateTime;

/**
 *
 * @author jacobmulvad
 */
public class ProjectDate {
    private int year;
    private int month;
    private int day;
    private int[] month31 = {1,3,5,7,8,10};
    private int[] month30 = {4,6,9,11};
    
    public ProjectDate(){
        this.year = 2015;
        this.month = 11;
        this.day = 30;
    }
    
    public ProjectDate next(ProjectDate start) {        
        if (start.getDay() >= 28 && start.getMonth() == 2){
            this.day = 1;
            this.month = 3;
            this.year = start.getYear();
        } else if (start.getDay() >= 30 && Arrays.asList(month30).contains(start.getMonth()) ){
            this.day = 1;
            this.month = start.getMonth()+1;
            this.year = start.getYear();
        } else if (start.getDay() >= 31 && Arrays.asList(month31).contains(start.getMonth()) ){
            this.day = 1;
            this.month = start.getMonth()+1;
            this.year = start.getYear();
        } else if (start.getDay() >= 31 && start.getMonth() == 12 ){
            this.day = 1;
            this.month = 1;
            this.year = start.getYear()+1;
        } else {
            this.day = start.day+1;
            this.month = start.month;
            this.year = start.getYear();
        }
        return this;        
    }
    
    public boolean equals(ProjectDate test){
        return this.day == test.getDay() && this.month == test.getMonth() && this.year == test.getYear();
    }
    
    public ProjectDate(org.joda.time.DateTime date){
        this.year = date.getYear();
        this.month = date.getMonthOfYear();
        this.day = date.getDayOfMonth();
    }
    
    public ProjectDate(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
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
    
    public DateTime getJodaTime(ProjectDate date){
        org.joda.time.DateTime dateAndTime = new DateTime(date.year, date.month, date.day, 12, 0, 0, 0);        
        return dateAndTime;
    }
    
    public String toString(){
        return year+""+month+""+day;
    }
}
