/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.model;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class Bookings implements Serializable{
    private dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo;
    private ArrayList<FlightListObject> flights;    

    public Bookings(CreditCardInfoType creditCardInfo, ArrayList<FlightListObject> flights) {
        this.creditCardInfo = creditCardInfo;
        this.flights = flights;
    }

    public ArrayList<FlightListObject> getFlights() {
        return flights;
    }
    
    public boolean addFlight(FlightListObject flight){
        flights.add(flight);
        return true;
    }
    
    public boolean removeFlight(FlightListObject flight){
        flights.remove(flight);
        return true;
    } 

    public CreditCardInfoType getCreditCardInfo() {
        return creditCardInfo;
    }
    
    
}
