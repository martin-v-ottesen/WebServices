/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.Model;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class Bookings {
    private dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo;
    private ArrayList<RoomListObject> roomBookings;    

    public Bookings(CreditCardInfoType creditCardInfo, ArrayList<RoomListObject> hotels) {
        this.creditCardInfo = creditCardInfo;
        this.roomBookings = hotels;
    }

    public ArrayList<RoomListObject> getFlights() {
        return roomBookings;
    }
    
    public boolean addFlight(RoomListObject room){
        roomBookings.add(room);
        return true;
    }
    
    public boolean removeFlight(RoomListObject flight){
        roomBookings.remove(flight);
        return true;
    } 

    public CreditCardInfoType getCreditCardInfo() {
        return creditCardInfo;
    }
    
    
}
