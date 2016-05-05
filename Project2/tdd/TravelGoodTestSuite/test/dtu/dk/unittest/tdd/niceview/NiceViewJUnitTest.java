/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.niceview;

import dtu.dk.webservice.service.Hotel;
import dtu.dk.webservice.service.HotelInformation;
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
        HotelInformation expectedHotelInfo = new HotelInformation();
        Hotel hotel = new Hotel();
        
        hotel.setCity("Bangkok");
        hotel.setHotelName("HotelOfBangkok");
        hotel.setCheckInDate("2016-04-20");
        hotel.setCheckOutDate("2016-04-27");
        hotel.setAddress("BangkokMainStreet 2");
        
        expectedHotelInfo.setNameOfHotelReservationService("NiceView");
        expectedHotelInfo.setHotel(hotel);
        expectedHotelInfo.setBookingNumber(12345);
        expectedHotelInfo.setPrice(5000);
        
        setTestHotelInformations(expectedHotelInfo);
    }
    
    @After
    public void tearDown() {
        clearHotelInformations();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void getHotels_GetSingleHotel_ReturnExpectedHotelInformation() {        
         //Arrange
         List<HotelInformation> Result;
         Hotel expectedHotel;
         
         expectedHotel = new Hotel();
        
         expectedHotel.setCity("Bangkok");
         expectedHotel.setHotelName("HotelOfBangkok");
         expectedHotel.setCheckInDate("2016-04-20");
         expectedHotel.setCheckOutDate("2016-04-27");
         expectedHotel.setAddress("BangkokMainStreet 2");
         
         int expectedBookingNumber = 12345;
         int expectedPrice = 5000;
         //Act
         Result = getHotels(expectedHotel.getCity(), expectedHotel.getCheckInDate(), expectedHotel.getCheckOutDate());
         //Assert
         assertEquals(expectedBookingNumber, Result.get(0).getBookingNumber());
         assertEquals(expectedPrice, Result.get(0).getPrice());
         assertEquals(expectedHotel.getHotelName(), Result.get(0).getHotel().getHotelName());
     }

    private static java.util.List<dtu.dk.webservice.service.HotelInformation> getHotels(java.lang.String cityName, java.lang.String arrivalDate, java.lang.String departureDate) {
        dtu.dk.webservice.service.HotelReservationService_Service service = new dtu.dk.webservice.service.HotelReservationService_Service();
        dtu.dk.webservice.service.HotelReservationService port = service.getHotelReservationServicePort();
        return port.getHotels(cityName, arrivalDate, departureDate);
    }

    private static void setTestHotelInformations(dtu.dk.webservice.service.HotelInformation hotelInfo) {
        dtu.dk.webservice.service.HotelReservationService_Service service = new dtu.dk.webservice.service.HotelReservationService_Service();
        dtu.dk.webservice.service.HotelReservationService port = service.getHotelReservationServicePort();
        port.setTestHotelInformations(hotelInfo);
    }

    private static void clearHotelInformations() {
        dtu.dk.webservice.service.HotelReservationService_Service service = new dtu.dk.webservice.service.HotelReservationService_Service();
        dtu.dk.webservice.service.HotelReservationService port = service.getHotelReservationServicePort();
        port.clearHotelInformations();
    }

}
