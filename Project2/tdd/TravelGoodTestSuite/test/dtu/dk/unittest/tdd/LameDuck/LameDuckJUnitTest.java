/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.LameDuck;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dtu.dk.webservice.service.CreditCardFaultMessage;
import dtu.dk.webservice.service.Exception_Exception;
import dtu.dk.webservice.service.Flight;
import dtu.dk.webservice.service.FlightInformation;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

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
    public void getFlights_GetOneFlightInformation_ExpectedSingleFlightInformationReturned() {
       //Arrange
       List<FlightInformation> Result;
       //Act
       Result = getFlights("Copenhagen", "Thailand", "2016-04-16");

       //Assert
       assertEquals(1, Result.size());
       assertEquals(1234567890, Result.get(0).getBookingNumber());
       assertEquals("SAS", Result.get(0).getFlight().getCarrierOperationTheFlight());
    }

    @Test
    public void bookFlight_BookingSingleFlight_ReturnsTrue() throws CreditCardFaultMessage, Exception_Exception{
        //Arrange
        boolean Result;
        List<FlightInformation> expectedFlightInformations;   
        CreditCardInfoType creditcardInfo = new CreditCardInfoType();
        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        
        creditcardInfo.setName("Thor-Jensen Claus");
        creditcardInfo.setNumber("50408825");
        
        expirationDate.setYear(9);
        expirationDate.setMonth(5);
        creditcardInfo.setExpirationDate(expirationDate);
        //Act
        expectedFlightInformations = getFlights("Copenhagen", "Thailand", "2016-04-16");
        FlightInformation expectedFlightInfo = expectedFlightInformations.get(0);
        Result = bookFlight(expectedFlightInfo.getBookingNumber(), creditcardInfo);
        //Assert
        assertEquals(true, Result);
    }
    
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    
    @Test
    public void bookFlight_BookingSingleFlight_ThrowsAnExceptionWithExpectedErrorMessage() throws CreditCardFaultMessage, Exception_Exception{
        //Arrange    
        boolean Result;
        int WrongBookingNumber;
        List<FlightInformation> expectedFlightInformations;   
        CreditCardInfoType creditcardInfo = new CreditCardInfoType();
        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        
        creditcardInfo.setName("Thor-Jensen Claus");
        creditcardInfo.setNumber("50408825");
        
        expirationDate.setYear(9);
        expirationDate.setMonth(5);
        creditcardInfo.setExpirationDate(expirationDate);
        //Act
        expectedFlightInformations = getFlights("Copenhagen", "Thailand", "2016-04-16");
        FlightInformation expectedFlightInfo = expectedFlightInformations.get(0);
        
        WrongBookingNumber = 000000;
        expectedEx.expectMessage("Booking Number was not found: " + String.valueOf(WrongBookingNumber));
        Result = bookFlight(WrongBookingNumber, creditcardInfo);
        //Assert
        //Exception is Thrown and caught
    }
    
    @Test
    public void cancelFlight_cancelSingleFlight_ReturnTrue() throws CreditCardFaultMessage, Exception_Exception{        
         //Arrange
        boolean bookedFlightResult;
        boolean cancelFlightResult;
        List<FlightInformation> expectedFlightInformations;   
        CreditCardInfoType creditcardInfo = new CreditCardInfoType();
        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        
        creditcardInfo.setName("Thor-Jensen Claus");
        creditcardInfo.setNumber("50408825");
        
        expirationDate.setYear(9);
        expirationDate.setMonth(5);
        creditcardInfo.setExpirationDate(expirationDate);
        //Act
        expectedFlightInformations = getFlights("Copenhagen", "Thailand", "2016-04-16");
        FlightInformation expectedFlightInfo = expectedFlightInformations.get(0);
        bookedFlightResult = bookFlight(expectedFlightInfo.getBookingNumber(), creditcardInfo);
        cancelFlightResult = cancelFlight(expectedFlightInfo.getBookingNumber(), creditcardInfo,
                expectedFlightInfo.getPrice());
        
        //Assert
        assertEquals(true, cancelFlightResult);
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

    private static boolean bookFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage, Exception_Exception {
        dtu.dk.webservice.service.AirlineReservationService_Service service = new dtu.dk.webservice.service.AirlineReservationService_Service();
        dtu.dk.webservice.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        return port.bookFlight(bookingNumber, creditCardInfo);
    }

    private static boolean cancelFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int price) throws Exception_Exception, CreditCardFaultMessage {
        dtu.dk.webservice.service.AirlineReservationService_Service service = new dtu.dk.webservice.service.AirlineReservationService_Service();
        dtu.dk.webservice.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        return port.cancelFlight(bookingNumber, creditCardInfo, price);
    }
}
