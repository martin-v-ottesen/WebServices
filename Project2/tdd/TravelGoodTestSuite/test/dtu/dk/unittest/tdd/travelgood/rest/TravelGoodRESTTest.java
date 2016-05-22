/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.travelgood.rest;

import dk.dtu.webservice.airline.service.Flight;
import dk.dtu.webservice.airline.service.FlightInformation;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

/**
 *
 * @author Martin
 */
public class TravelGoodRESTTest {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/TravleGoodREST/webresources/tg");

    public TravelGoodRESTTest() {
    }
    @Test
    public void hello() {
        String result = target.request().get(String.class);
        assertEquals(result,"DTU");
    }
    
        @Before
    public void setUp() {
        FlightInformation expectedflightInfo = new FlightInformation();
        Flight flight = new Flight();
        
        flight.setStartAirport("Copenhagen");
        flight.setEndAirport("Thailand");
        flight.setCarrierOperationTheFlight("SAS");
        flight.setDateAndTimefForLiftOff("2016-04-16");
        flight.setDateAndTimefForLanding("2016-04-20");
        
        expectedflightInfo.setFlight(flight);
        
        expectedflightInfo.setNameOfAirlineReservationService("LameDuck");
        expectedflightInfo.setBookingNumber(1234567890);
        expectedflightInfo.setPrice(999);

        setTestFlightInformations(expectedflightInfo);
    }
    
    @After
    public void tearDown() {
        clearFlightInformations();
    }
    
    @Test
    public void EccoTest(){
        String startDestination = "Copenhagen";
        String endDestination = "Thailand";
        String startDate = "2016-04-16";
        String result = target.path("flight/" + FlightData.startDestination + "/"+FlightData.endDestination+"/"+FlightData.startDate)
               .request().get(String.class);
        System.out.println(result);
    }

    private static void setTestFlightInformations(dk.dtu.webservice.airline.service.FlightInformation flightInfo) {
        dk.dtu.webservice.airline.service.AirlineReservationService_Service service = new dk.dtu.webservice.airline.service.AirlineReservationService_Service();
        dk.dtu.webservice.airline.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        port.setTestFlightInformations(flightInfo);
    }

    private static void clearFlightInformations() {
        dk.dtu.webservice.airline.service.AirlineReservationService_Service service = new dk.dtu.webservice.airline.service.AirlineReservationService_Service();
        dk.dtu.webservice.airline.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        port.clearFlightInformations();
    }
}
