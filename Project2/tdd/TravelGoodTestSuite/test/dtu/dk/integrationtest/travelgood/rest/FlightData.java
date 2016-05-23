/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.integrationtest.travelgood.rest;

import dtu.dk.unittest.tdd.travelgood.rest.*;
import dtu.dk.unittest.tdd.lameduck.*;
import dk.dtu.webservice.airline.service.Flight;
import dk.dtu.webservice.airline.service.FlightInformation;

/**
 *
 * @author Martin
 */
public class FlightData {
    
    /* Plane 1*/
    static FlightInformation testFlightInformation1() {
        FlightInformation expectedflightInfo = new FlightInformation();
        expectedflightInfo.setFlight(testFlight1());
        expectedflightInfo.setNameOfAirlineReservationService("LameDuck");
        expectedflightInfo.setBookingNumber(1234567890);
        expectedflightInfo.setPrice(999);

        return expectedflightInfo;
    }

    static Flight testFlight1() {
        Flight flight = new Flight();

        flight.setStartAirport("Copenhagen");
        flight.setEndAirport("Bangkok");
        flight.setCarrierOperationTheFlight("SAS");
        flight.setDateAndTimefForLiftOff("2016-08-19");
        flight.setDateAndTimefForLanding("2016-08-20");

        return flight;
    }
    
    /* Plane 2*/
    static FlightInformation testFlightInformation2() {
        FlightInformation expectedflightInfo = new FlightInformation();
        expectedflightInfo.setFlight(testFlight2());
        expectedflightInfo.setNameOfAirlineReservationService("LameDuck");
        expectedflightInfo.setBookingNumber(1248163264);
        expectedflightInfo.setPrice(888);

        return expectedflightInfo;
    }

    static Flight testFlight2() {
        Flight flight = new Flight();

        flight.setStartAirport("Copenhagen");
        flight.setEndAirport("Bangkok");
        flight.setCarrierOperationTheFlight("Thai Airways");
        flight.setDateAndTimefForLiftOff("2016-08-19");
        flight.setDateAndTimefForLanding("2016-08-20");

        return flight;
    }

    /* Plane 3*/
    static FlightInformation testFlightInformation3() {
        FlightInformation expectedflightInfo = new FlightInformation();
        expectedflightInfo.setFlight(testFlight3());
        expectedflightInfo.setNameOfAirlineReservationService("LameDuck");
        expectedflightInfo.setBookingNumber(1357924680);
        expectedflightInfo.setPrice(99);

        return expectedflightInfo;
    }

    static Flight testFlight3() {
        Flight flight = new Flight();

        flight.setStartAirport("Bangkok");
        flight.setEndAirport("Sidney");
        flight.setCarrierOperationTheFlight("Austrian Airlines");
        flight.setDateAndTimefForLiftOff("2016-08-27");
        flight.setDateAndTimefForLanding("2016-08-27");

        return flight;
    }
    
    /* Plane 4*/
    static FlightInformation testFlightInformation4() {
        FlightInformation expectedflightInfo = new FlightInformation();
        expectedflightInfo.setFlight(testFlight4());
        expectedflightInfo.setNameOfAirlineReservationService("LameDuck");
        expectedflightInfo.setBookingNumber(1392771211);
        expectedflightInfo.setPrice(88);

        return expectedflightInfo;
    }

    static Flight testFlight4() {
        Flight flight = new Flight();

        flight.setStartAirport("Bangkok");
        flight.setEndAirport("Sidney");
        flight.setCarrierOperationTheFlight("Thai Airways");
        flight.setDateAndTimefForLiftOff("2016-08-27");
        flight.setDateAndTimefForLanding("2016-08-27");

        return flight;
    }
    
    /* Plane 5 */    
    static FlightInformation testFlightInformation5() {
        FlightInformation expectedflightInfo = new FlightInformation();
        expectedflightInfo.setFlight(testFlight5());
        expectedflightInfo.setNameOfAirlineReservationService("LameDuck");
        expectedflightInfo.setBookingNumber(1416642560);
        expectedflightInfo.setPrice(989);

        return expectedflightInfo;
    }

    static Flight testFlight5() {
        Flight flight = new Flight();

        flight.setStartAirport("Sidney");
        flight.setEndAirport("Copenhagen");
        flight.setCarrierOperationTheFlight("SAS");
        flight.setDateAndTimefForLiftOff("2016-08-30");
        flight.setDateAndTimefForLanding("2016-08-31");

        return flight;
    }
    

}
