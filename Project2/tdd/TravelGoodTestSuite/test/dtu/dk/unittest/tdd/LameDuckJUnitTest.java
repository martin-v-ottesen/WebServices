/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd;

import dtu.dk.webservice.service.Flight;
import dtu.dk.webservice.service.FlightInformation;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dxong
 */
public class LameDuckJUnitTest {
    
    public LameDuckJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
     public void getFlights_ReturnString_ExpectedStringValueReturned() {
         //Arrange
        List<FlightInformation> Result;
        //Act
        Result = getFlights("Copenhagen", "Thailand", "2016-04-16");

        //Assert
        assertEquals(1, Result.size());
        assertEquals(1234567890, Result.get(0).getBookingNumber());
        assertEquals("SAS", Result.get(0).getFlight().getCarrierOperationTheFlight());
     }

    private static void clearFlightInformations() {
        dtu.dk.webservice.service.AirlineReservationService_Service service = new dtu.dk.webservice.service.AirlineReservationService_Service();
        dtu.dk.webservice.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        port.clearFlightInformations();
    }

    private static void setTestFlightInformations(dtu.dk.webservice.service.FlightInformation flightInfo) {
        dtu.dk.webservice.service.AirlineReservationService_Service service = new dtu.dk.webservice.service.AirlineReservationService_Service();
        dtu.dk.webservice.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        port.setTestFlightInformations(flightInfo);
    }

    private static java.util.List<dtu.dk.webservice.service.FlightInformation> getFlights(java.lang.String startDestination, java.lang.String endDestination, java.lang.String startDate) {
        dtu.dk.webservice.service.AirlineReservationService_Service service = new dtu.dk.webservice.service.AirlineReservationService_Service();
        dtu.dk.webservice.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        return port.getFlights(startDestination, endDestination, startDate);
    }

        
}
