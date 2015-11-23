/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class Bookings {
    private dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo;
    private ArrayList<FlighListObject> flights;    

    public Bookings(CreditCardInfoType creditCardInfo, ArrayList<FlighListObject> flights) {
        this.creditCardInfo = creditCardInfo;
        this.flights = flights;
    }

    public ArrayList<FlighListObject> getFlights() {
        return flights;
    }
    
    public boolean addFlight(FlighListObject flight){
        flights.add(flight);
        return true;
    }
    
    public boolean removeFlight(FlighListObject flight){
        flights.remove(flight);
        return true;
    } 

    public CreditCardInfoType getCreditCardInfo() {
        return creditCardInfo;
    }
    
    
}
