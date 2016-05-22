/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.travelgood.rest;

import dtu.dk.unittest.tdd.lameduck.*;
import dk.dtu.webservice.airline.service.Flight;
import dk.dtu.webservice.airline.service.FlightInformation;

/**
 *
 * @author Martin
 */
public class FlightData {
    
    static FlightInformation testFlightInformation(){
        FlightInformation expectedflightInfo = new FlightInformation();       
        expectedflightInfo.setFlight(testFlight());        
        expectedflightInfo.setNameOfAirlineReservationService("LameDuck");
        expectedflightInfo.setBookingNumber(1234567890);
        expectedflightInfo.setPrice(999);
        
        return expectedflightInfo;
    }
    
    static Flight testFlight(){
        Flight flight = new Flight();
        
        flight.setStartAirport("Copenhagen");
        flight.setEndAirport("Thailand");
        flight.setCarrierOperationTheFlight("SAS");
        flight.setDateAndTimefForLiftOff("2016-04-16");
        flight.setDateAndTimefForLanding("2016-04-20");
        
        return flight;
    }
        
    
}
