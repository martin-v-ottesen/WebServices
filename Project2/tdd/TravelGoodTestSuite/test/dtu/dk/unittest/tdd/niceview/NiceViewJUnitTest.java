/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.niceview;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.webservice.hotel.service.CreditCardFaultMessage;
import dk.dtu.webservice.hotel.service.Exception_Exception;
import dk.dtu.webservice.hotel.service.Hotel;
import dk.dtu.webservice.hotel.service.HotelInformation;

import java.util.ArrayList;
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
public class NiceViewJUnitTest {
    
    public NiceViewJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //Hotel 1: HotelOfBangkok
        setTestHotelInformations(HotelData.testHotelInformation1());
        
        //Hotel 2: BLUE
        setTestHotelInformations(HotelData.testHotelInformation2());
        
        //Hotel 3: RADISON
        setTestHotelInformations(HotelData.testHotelInformation3());
    }
    
    @After
    public void tearDown() {
        clearHotelInformations();
    }

     @Test
     public void getHotels_GetSingleHotel_ReturnExpectedHotelInformation() {        
         //Arrange
         List<HotelInformation> Result;
         HotelInformation expected = HotelData.testHotelInformation1();
         
         //Act
         Result = getHotels(expected.getHotel().getCity(),
                 expected.getHotel().getCheckInDate(),
                 expected.getHotel().getCheckOutDate());
         //Assert
         assertEquals(expected.getBookingNumber(), Result.get(0).getBookingNumber());
         assertEquals(expected.getPrice(), Result.get(0).getPrice());
         assertEquals(expected.getHotel().getHotelName(), Result.get(0).getHotel().getHotelName());
     }
     @Test
     public void getHotels_GetMultipleHotels_ReturnTwoHotels() {
        //Arrange
        List<HotelInformation> Result;
        Hotel expectedHotel1;
        Hotel expectedHotel2;

        expectedHotel1 = new Hotel();
        expectedHotel2 = new Hotel();

        expectedHotel1.setCity("Bangkok");
        expectedHotel1.setHotelName("HotelOfBangkok");
        expectedHotel1.setCheckInDate("2016-04-20");
        expectedHotel1.setCheckOutDate("2016-04-27");
        expectedHotel1.setAddress("BangkokMainStreet 2");
        
        int expectedBookingNumber1 = 12345;
        int expectedPrice1 = 5000;
        
        expectedHotel2.setCity("Bangkok");
        expectedHotel2.setHotelName("BLUE");
        expectedHotel2.setCheckInDate("2016-04-20");
        expectedHotel2.setCheckOutDate("2016-04-27");
        expectedHotel2.setAddress("BangkokMainStreet 100");
        
        int expectedBookingNumber2 = 567;
        int expectedPrice2 = 10000;
        
        //Act
        Result = getHotels("Bangkok", "2016-04-20", "2016-04-27");
        
        //Assert
        assertEquals(2, Result.size());
        assertEquals(expectedBookingNumber1, Result.get(0).getBookingNumber());
        assertEquals(expectedPrice1, Result.get(0).getPrice());
        assertEquals(expectedHotel1.getHotelName(), Result.get(0).getHotel().getHotelName());
        assertEquals(expectedBookingNumber2, Result.get(1).getBookingNumber());
        assertEquals(expectedPrice2, Result.get(1).getPrice());
        assertEquals(expectedHotel2.getHotelName(), Result.get(1).getHotel().getHotelName());
     }
     
     @Test
     public void bookHotel_BookSingleHotel_ReturnTrue() throws CreditCardFaultMessage, Exception_Exception{
         //Arrange
         HotelInformation expectedHotelToBeBooked = new HotelInformation();
         List<HotelInformation> expectedHotelInformationList = new ArrayList<>();
         
         boolean Result;
         boolean expectedResult = true;
         String expectedCity = "Bangkok";
         String expectedCheckInDate = "2016-04-20";
         String expectedCheckOutDate = "2016-04-27";
         
         CreditCardInfoType creditcardInfo = new CreditCardInfoType();
         CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        
         creditcardInfo.setName("Thor-Jensen Claus");
         creditcardInfo.setNumber("50408825");
        
         expirationDate.setYear(9);
         expirationDate.setMonth(5);
         creditcardInfo.setExpirationDate(expirationDate);
         
         //Act
         expectedHotelToBeBooked = getHotels(expectedCity, expectedCheckInDate, expectedCheckOutDate).get(0);
         
         Result = bookHotel(expectedHotelToBeBooked.getBookingNumber(), expectedHotelToBeBooked.isIsCreditCardGuaranteeRequired(), creditcardInfo);        
         //Assert
         assertEquals(expectedResult, Result);
     }
     
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    
    @Test
    public void bookHotel_BookingSingleHotelWithBookingNumberThatDoesNotExist_ThrowsAnExceptionWithExpectedErrorMessage() throws CreditCardFaultMessage, Exception_Exception{
         //Arrange
         HotelInformation expectedHotelToBeBooked = new HotelInformation();
         List<HotelInformation> expectedHotelInformationList = new ArrayList<>();
         
         int WrongBookingNumber = 69;
         boolean Result;
         boolean expectedResult = true;
         
         String expectedCity = "Bangkok";
         String expectedCheckInDate = "2016-04-20";
         String expectedCheckOutDate = "2016-04-27";
         
         CreditCardInfoType creditcardInfo = new CreditCardInfoType();
         CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();
        
         creditcardInfo.setName("Thor-Jensen Claus");
         creditcardInfo.setNumber("50408825");
        
         expirationDate.setYear(9);
         expirationDate.setMonth(5);
         creditcardInfo.setExpirationDate(expirationDate);
         
         //Act
         expectedHotelToBeBooked = getHotels(expectedCity, expectedCheckInDate, expectedCheckOutDate).get(0);
         expectedEx.expectMessage("Booking Number was not found: " + String.valueOf(WrongBookingNumber)); 
         Result = bookHotel(WrongBookingNumber, expectedHotelToBeBooked.isIsCreditCardGuaranteeRequired(), creditcardInfo);                 
         //Assert
         //Exception is Thrown and caught
    }
    
    @Test
    public void cancelHotel_CancelAnExistingBookedHotelWithCorrectBookingNumber_ReturnVoid() throws CreditCardFaultMessage, Exception_Exception, InterruptedException{
        
        //Arrange
        List<HotelInformation> expectedHotelInformationList = new ArrayList<>();

        boolean Result = false;
        boolean expectedResult = true;
        int expectedBookingNumberToBeCancelled = 876;
        boolean expectedisCreditCardGuaranteeRequired = false;
        
        CreditCardInfoType creditcardInfo = new CreditCardInfoType();
        CreditCardInfoType.ExpirationDate expirationDate = new CreditCardInfoType.ExpirationDate();

        creditcardInfo.setName("Thor-Jensen Claus");
        creditcardInfo.setNumber("50408825");

        expirationDate.setYear(9);
        expirationDate.setMonth(5);
        creditcardInfo.setExpirationDate(expirationDate);

        //Act
        bookHotel(expectedBookingNumberToBeCancelled, expectedisCreditCardGuaranteeRequired , creditcardInfo);

        cancelHotel(expectedBookingNumberToBeCancelled);
        
        int checkingValue = getBookedHotelInformations().size();
        int counter = 0;
        for (HotelInformation bookedHotelInfo : getBookedHotelInformations()) {
            if (bookedHotelInfo.getBookingNumber() != expectedBookingNumberToBeCancelled ){
                ++counter;
            }               
        }
        
        if(checkingValue == counter)
            Result = true;
        //Assert
        assertEquals(expectedResult, Result);         
    }
    
    @Test
    public void cancelHotel_CancelAnExistingBookedHotelWithAnInCorrectBookingNumber_ExpectionIsThrownWithExpectedMessage() throws CreditCardFaultMessage, Exception_Exception{
         //Arrange
         HotelInformation expectedHotelToBeBooked = new HotelInformation();
         List<HotelInformation> expectedHotelInformationList = new ArrayList<>();
         
         int WrongBookingNumber = -1;
         //Act
         expectedEx.expectMessage("Cancelling hotel can not be issued. Booking number: " + WrongBookingNumber); 
         cancelHotel(WrongBookingNumber);
         //Assert
         //Exception is Thrown and caught
    }

    private static boolean bookHotel(int bookingNumber, boolean isCreditCardGuaranteeRequired, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage, Exception_Exception {
        dk.dtu.webservice.hotel.service.HotelReservationService_Service service = new dk.dtu.webservice.hotel.service.HotelReservationService_Service();
        dk.dtu.webservice.hotel.service.HotelReservationService port = service.getHotelReservationServicePort();
        return port.bookHotel(bookingNumber, isCreditCardGuaranteeRequired, creditCardInfo);
    }

    private static void cancelHotel(int bookingNumber) throws Exception_Exception {
        dk.dtu.webservice.hotel.service.HotelReservationService_Service service = new dk.dtu.webservice.hotel.service.HotelReservationService_Service();
        dk.dtu.webservice.hotel.service.HotelReservationService port = service.getHotelReservationServicePort();
        port.cancelHotel(bookingNumber);
    }

    private static void clearHotelInformations() {
        dk.dtu.webservice.hotel.service.HotelReservationService_Service service = new dk.dtu.webservice.hotel.service.HotelReservationService_Service();
        dk.dtu.webservice.hotel.service.HotelReservationService port = service.getHotelReservationServicePort();
        port.clearHotelInformations();
    }

    private static java.util.List<dk.dtu.webservice.hotel.service.HotelInformation> getBookedHotelInformations() {
        dk.dtu.webservice.hotel.service.HotelReservationService_Service service = new dk.dtu.webservice.hotel.service.HotelReservationService_Service();
        dk.dtu.webservice.hotel.service.HotelReservationService port = service.getHotelReservationServicePort();
        return port.getBookedHotelInformations();
    }

    private static java.util.List<dk.dtu.webservice.hotel.service.HotelInformation> getHotels(java.lang.String cityName, java.lang.String arrivalDate, java.lang.String departureDate) {
        dk.dtu.webservice.hotel.service.HotelReservationService_Service service = new dk.dtu.webservice.hotel.service.HotelReservationService_Service();
        dk.dtu.webservice.hotel.service.HotelReservationService port = service.getHotelReservationServicePort();
        return port.getHotels(cityName, arrivalDate, departureDate);
    }

    private static void setTestHotelInformations(dk.dtu.webservice.hotel.service.HotelInformation hotelInfo) {
        dk.dtu.webservice.hotel.service.HotelReservationService_Service service = new dk.dtu.webservice.hotel.service.HotelReservationService_Service();
        dk.dtu.webservice.hotel.service.HotelReservationService port = service.getHotelReservationServicePort();
        port.setTestHotelInformations(hotelInfo);
    }


}
