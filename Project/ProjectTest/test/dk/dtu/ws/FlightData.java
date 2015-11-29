/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

import org.joda.time.DateTime;

public class FlightData {
    static final FlightInformation Flight1(){
        DateTime departureTime = new DateTime(2015, 11, 29, 22, 0, 0, 0);
        DateTime arrivalTime = new DateTime(2015, 11, 30, 12, 0, 0, 0);
        FlightInformation flight1 = new FlightInformation();
        flight1.setBookingNumber(1001);
        flight1.setStartDestination("Copenhagen");
        flight1.setEndDestination("Bruxelles");
        //flight1.setDepartureTime(departureTime);
        //flight1.setArrivalTime();
        flight1.setBookingPrice(1500);
        
        return flight1;
    }
    
    static final FlightInformation Flight2(){
        
        
        FlightInformation flight2 = new FlightInformation();
        flight2.setBookingNumber(1002);
        flight2.setStartDestination("Bruxelles");
        flight2.setEndDestination("Copenhagen");
        return flight2;
    }
    
    static final FlightInformation Flight3(){
        
        
        FlightInformation flight3 = new FlightInformation();
        flight3.setBookingNumber(1002);
        flight3.setStartDestination("Bruxelles");
        flight3.setEndDestination("Copenhagen");
        return flight3;
    }
}
