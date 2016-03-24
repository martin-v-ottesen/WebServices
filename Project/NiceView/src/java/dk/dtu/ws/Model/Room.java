/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.Model;

import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class Room {
    private int nr;
    private int dayPrice;
    private ArrayList<ProjectDate> booked;    

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getNr() {
        return nr;
    }

    public void setDayPrice(int dayPrice) {
        this.dayPrice = dayPrice;
    }

    public int getDayPrice() {
        return dayPrice;
    }

    public ArrayList<ProjectDate> getBooked() {
        return booked;
    }

    public void setBooked(ArrayList<ProjectDate> booked) {
        this.booked = booked;
    }
    
    public boolean has(ArrayList<ProjectDate> dates){
        return dates.stream().noneMatch((date) -> (booked.contains(date)));
    }
    
    
}
