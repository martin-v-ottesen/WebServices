/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.travelgood.rest;

import dk.dtu.webservice.airline.service.Flight;
import dk.dtu.webservice.airline.service.FlightInformation;
import dk.dtu.webservice.hotel.service.Exception_Exception;
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
        assertEquals(result, "DTU");
    }

    @Before
    public void setUp() {
        setTestFlightInformations(FlightData.testFlightInformation());
        
        //Hotel 1: HotelOfBangkok
        setTestHotelInformations(HotelData.testHotelInformation1());
        
        //Hotel 2: BLUE
        setTestHotelInformations(HotelData.testHotelInformation2());
        
        //Hotel 3: RADISON
        setTestHotelInformations(HotelData.testHotelInformation3());
    }

    @After
    public void tearDown() {
        clearFlightInformations();
        clearHotelInformations();
    }

    @Test
    public void getFlightTest() {
        String result = target.path("flight/" 
                + FlightData.testFlightInformation().getFlight().getStartAirport() + "/" 
                + FlightData.testFlightInformation().getFlight().getEndAirport() + "/" 
                + FlightData.testFlightInformation().getFlight().getDateAndTimefForLiftOff())
                .request().get(String.class);
        System.out.println(result);
    }
    
    @Test
    public void getHotelTest() {
        String result = target.path("hotel/" 
                + HotelData.testHotel1().getCity() + "/" 
                + HotelData.testHotel1().getCheckInDate() + "/" 
                + HotelData.testHotel1().getCheckOutDate())
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

    private static void setTestHotelInformations(dk.dtu.webservice.hotel.service.HotelInformation hotelInfo) {
        dk.dtu.webservice.hotel.service.HotelReservationService_Service service = new dk.dtu.webservice.hotel.service.HotelReservationService_Service();
        dk.dtu.webservice.hotel.service.HotelReservationService port = service.getHotelReservationServicePort();
        port.setTestHotelInformations(hotelInfo);
    }

    private static void clearHotelInformations() {
        dk.dtu.webservice.hotel.service.HotelReservationService_Service service = new dk.dtu.webservice.hotel.service.HotelReservationService_Service();
        dk.dtu.webservice.hotel.service.HotelReservationService port = service.getHotelReservationServicePort();
        port.clearHotelInformations();
    }


}
