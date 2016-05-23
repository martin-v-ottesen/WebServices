/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.lameduck;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.webservice.airline.service.CreditCardFaultMessage;
import dk.dtu.webservice.airline.service.Exception_Exception;
import dk.dtu.webservice.airline.service.Flight;
import dk.dtu.webservice.airline.service.FlightInformation;
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
        setTestFlightInformations(FlightData.testFlightInformation());
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
       Result = getFlights(FlightData.startDestination,
                FlightData.endDestination, 
                FlightData.startDate);

       //Assert
       assertEquals(1, Result.size());
       assertEquals(1234567890, Result.get(0).getBookingNumber());
       assertEquals("SAS", Result.get(0).getFlight().getCarrierOperationTheFlight());
    }
    
//    @Test
//    public void getFlights_GetMultipleFlightInformation_ReturnsTwoFlightInformations() {
//        throw new NotImplementedException();
//        //Arrange
//        //Act
//        //Assert
//    }

    @Test
    public void bookFlight_BookingSingleFlight_ReturnsTrue() throws CreditCardFaultMessage, Exception_Exception{
        //Arrange
        boolean Result;
        List<FlightInformation> expectedFlightInformations;   
        CreditCardInfoType creditcardInfo = BankData.validCard();
        
        //Act
        expectedFlightInformations = getFlights(FlightData.startDestination,
                FlightData.endDestination, 
                FlightData.startDate);
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
        CreditCardInfoType creditcardInfo = BankData.validCard();
        
        //Act
        expectedFlightInformations = getFlights(FlightData.startDestination,
                FlightData.endDestination, 
                FlightData.startDate);
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
        CreditCardInfoType creditcardInfo = BankData.validCard();

        //Act
        expectedFlightInformations = getFlights(FlightData.startDestination,
                FlightData.endDestination, 
                FlightData.startDate);
        FlightInformation expectedFlightInfo = expectedFlightInformations.get(0);
        bookedFlightResult = bookFlight(expectedFlightInfo.getBookingNumber(), creditcardInfo);
        cancelFlightResult = cancelFlight(expectedFlightInfo.getBookingNumber(), creditcardInfo,
                expectedFlightInfo.getPrice());
        
        //Assert
        assertEquals(true, cancelFlightResult);
    }

    private static boolean bookFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws dk.dtu.webservice.airline.service.CreditCardFaultMessage, dk.dtu.webservice.airline.service.Exception_Exception {
        dk.dtu.webservice.airline.service.AirlineReservationService_Service service = new dk.dtu.webservice.airline.service.AirlineReservationService_Service();
        dk.dtu.webservice.airline.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        return port.bookFlight(bookingNumber, creditCardInfo);
    }

    private static boolean cancelFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int price) throws dk.dtu.webservice.airline.service.CreditCardFaultMessage, dk.dtu.webservice.airline.service.Exception_Exception {
        dk.dtu.webservice.airline.service.AirlineReservationService_Service service = new dk.dtu.webservice.airline.service.AirlineReservationService_Service();
        dk.dtu.webservice.airline.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        return port.cancelFlight(bookingNumber, creditCardInfo, price);
    }

    private static void clearFlightInformations() {
        dk.dtu.webservice.airline.service.AirlineReservationService_Service service = new dk.dtu.webservice.airline.service.AirlineReservationService_Service();
        dk.dtu.webservice.airline.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        port.clearFlightInformations();
    }

    private static java.util.List<dk.dtu.webservice.airline.service.FlightInformation> getFlights(java.lang.String startDestination, java.lang.String endDestination, java.lang.String startDate) {
        dk.dtu.webservice.airline.service.AirlineReservationService_Service service = new dk.dtu.webservice.airline.service.AirlineReservationService_Service();
        dk.dtu.webservice.airline.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        return port.getFlights(startDestination, endDestination, startDate);
    }

    private static void setTestFlightInformations(dk.dtu.webservice.airline.service.FlightInformation flightInfo) {
        dk.dtu.webservice.airline.service.AirlineReservationService_Service service = new dk.dtu.webservice.airline.service.AirlineReservationService_Service();
        dk.dtu.webservice.airline.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        port.setTestFlightInformations(flightInfo);
    }
}
