/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dk.dtu.webservice.airline.service.CreditCardInfoType;
import dk.dtu.webservice.airline.service.ExpirationDate;
import dk.dtu.webservice.airline.service.GetFlightsResponse;
import dk.dtu.webservice.hotel.service.GetHotelsResponse;
import dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.ItineraryContainerType;
import java.util.Random;
import java.util.UUID;
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
public class TravelGoodBpelJUnitTest {
    //globalTestUUID
    private String globalTestUUID = "99999";
    
    public TravelGoodBpelJUnitTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * CreateItinerary (COMPLETE and WORKS)
     */
    @Test
    public void createItinerary_InputUUIDAsTheCorrelationSet_ReturnItineraryContainerWithUUIDAndStatusUnconfirmed() {
        //Arrange

        ItineraryContainerType resultItineraryContainer;
        
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(10000);
        String itinerarySomething = Integer.toString(random);
        
        String testUUID = UUID.randomUUID().toString();
        //System.out.println(testUUID);
        System.out.println(itinerarySomething);
        //Act
        resultItineraryContainer = createItinerary(itinerarySomething);
        String resultItineraryID = resultItineraryContainer.getItineraryID();
        System.out.println(resultItineraryID);
        //Assert
        assertNotSame(itinerarySomething, resultItineraryID);

    }
    /**
     * GetFlights into ItineraryContainer (COMPLETE and WORKS)
     */
    @Test
    public void getFlights_getSingleFlightInformationFromSpecificItineraryID_ReturnSingleFlightInformationWithCorrectBookingNUmber() {
        //Arrange
        ItineraryContainerType resultItineraryContainer;
        
        GetFlightsResponse resultsFromGetFlights;
        int expectedBookingNumber = 365247;
        int expectedResultGetFlightListSize = 1;
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(10000);
        String itineraryID = Integer.toString(random);
        
        //Act
        resultItineraryContainer = createItinerary(itineraryID);
        resultsFromGetFlights = getFlights(itineraryID, "Copenhagen", "Tokyo", "2017-02-01");
        //Assert
        assertEquals(expectedResultGetFlightListSize, resultsFromGetFlights.getReturn().size());
        assertEquals(expectedBookingNumber, resultsFromGetFlights.getReturn().get(0).getBookingNumber());
    }
    /**
     * AddFlight into ItineraryContainer (COMPLETE and WORKS)
     */
    @Test
    public void AddFlight_getSingleFlightAndAddflightToItineraryContainer_ReturnItineraryContainerWithCorrectInformations() {
        //Arrange
        ItineraryContainerType resultItineraryContainer;
        ItineraryContainerType resultsFromAddFlight;        
        GetFlightsResponse resultsFromGetFlights;
        
        int expectedBookingNumber = 365247;
        int resultBookingNumber;
        int resultPrice;
        int expectedResultGetFlightListSize = 1;
        String expectedItineraryState = "unconfirmed";
        
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(10000);
        String itineraryID = Integer.toString(random);      
        
        //Act
        resultItineraryContainer = createItinerary(itineraryID);
        resultsFromGetFlights = getFlights(itineraryID, "Copenhagen", "Tokyo", "2017-02-01");
        resultBookingNumber = resultsFromGetFlights.getReturn().get(0).getBookingNumber();
        resultPrice = resultsFromGetFlights.getReturn().get(0).getPrice();
        resultsFromAddFlight = addFlight(itineraryID, resultBookingNumber, resultPrice);
        //Assert
        assertEquals(expectedResultGetFlightListSize, resultsFromGetFlights.getReturn().size());
        assertEquals(expectedBookingNumber, resultsFromGetFlights.getReturn().get(0).getBookingNumber());
        
        System.out.println(resultItineraryContainer.getItineraryID());
        
        assertEquals(resultItineraryContainer.getItineraryID(), resultsFromAddFlight.getItineraryID());
        assertEquals(expectedItineraryState, resultsFromAddFlight.getItineraryState());
        assertEquals(expectedResultGetFlightListSize, resultsFromAddFlight.getFlightReservationContainer().size());
        assertEquals(expectedBookingNumber, resultsFromAddFlight.getFlightReservationContainer().get(0).getBookingNumber());
    }

    @Test
    public void BookItinerary_getSingleFlightAddFlightToItineraryContainerAndBookFlight_NoExpectionIsCaught() {
        //Arrange
        ItineraryContainerType resultItineraryContainer;
        ItineraryContainerType resultsFromAddFlight;        
        GetFlightsResponse resultsFromGetFlights;
        String resultsFromBookItinerary;
        
        int expectedBookingNumber = 365247;
        int resultBookingNumber;
        int resultPrice;
        int expectedResultGetFlightListSize = 1;
        String expectedItineraryStateWhenPlanning = "unconfirmed";
        String expectedItineraryStateWhenBooked = "confirmed";
        
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(10000);
        String itineraryID = Integer.toString(random);      
        
        CreditCardInfoType testCard = new CreditCardInfoType();
        testCard.setName("Thor-Jensen Claus");
        testCard.setNumber("50408825");

        ExpirationDate expirationDate = new ExpirationDate();
        expirationDate.setYear(9);
        expirationDate.setMonth(5);
        testCard.setExpirationDate(expirationDate);
        
        //Act
        resultItineraryContainer = createItinerary(itineraryID);
        resultsFromGetFlights = getFlights(itineraryID, "Copenhagen", "Tokyo", "2017-02-01");
        resultBookingNumber = resultsFromGetFlights.getReturn().get(0).getBookingNumber();
        System.out.println(resultBookingNumber);
        resultPrice = resultsFromGetFlights.getReturn().get(0).getPrice();
        resultsFromAddFlight = addFlight(itineraryID, resultBookingNumber, resultPrice);
        System.out.println(resultsFromAddFlight.getFlightReservationContainer().get(0).getPrice());
        resultsFromBookItinerary = bookItinerary(itineraryID, testCard);
        
        //Assert
        assertEquals(expectedResultGetFlightListSize, resultsFromGetFlights.getReturn().size());
        assertEquals(expectedBookingNumber, resultsFromGetFlights.getReturn().get(0).getBookingNumber());
        
        System.out.println(resultItineraryContainer.getItineraryID());
        
        assertEquals(resultItineraryContainer.getItineraryID(), resultsFromAddFlight.getItineraryID());
        assertEquals(expectedItineraryStateWhenPlanning, resultsFromAddFlight.getItineraryState());
        assertEquals(expectedResultGetFlightListSize, resultsFromAddFlight.getFlightReservationContainer().size());
        assertEquals(expectedBookingNumber, resultsFromAddFlight.getFlightReservationContainer().get(0).getBookingNumber());
        
        assertEquals(expectedItineraryStateWhenBooked, resultsFromBookItinerary);
    }
    
    /*
    * P1 (planning and booking) Plan a trip by first planning a flight (i.e. getting a list of flights 
    * and then adding a flight to the itinerary), then by planning a hotel, another flight, a third flight, 
    * and finally a hotel. Ask for the itinerary and check that it is correct using JUnit’s assert statements –
    * i.e. assertEquals,assertTrue,... –inparticular,thatthebookingstatusforeachitemisunconfirmed.
    * Book the itinerary and ask again for the itinerary. Check that each booking status is now confirmed.
    */
    
    @Test
    public void P1() {
        //Arrange
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(10000);
        String ItineraryID = Integer.toString(random);      
        
        CreditCardInfoType testCard = new CreditCardInfoType();
        testCard.setName("Thor-Jensen Claus");
        testCard.setNumber("50408825");

        ExpirationDate expirationDate = new ExpirationDate();
        expirationDate.setYear(9);
        expirationDate.setMonth(5);
        testCard.setExpirationDate(expirationDate);
        
        //Flight
        String FlightOneCityFrom = "Copenhagen";
        String FlightOneCityTo = "Sydney";  
        String startDateOne = "2017-02-01";
        
        String FlightTwoCityFrom = "Sydney";
        String FlightTwoCityTo = "Thailand";
        String startDateTwo = "2017-02-10";
        
        String FlightThreeCityFrom = "Thailand";
        String FlightThreeCityTo = "Tokyo";
        String startDateThree = "2017-02-10";
        
        //Hotel
        String HotelOneCity = "Sydney";
        String HotelOneDateStart = "2017-02-02";
        String HotelOneDateEnd = "2017-02-10";       
        String HotelTwoCity = "Tokyo";
        String HotelTwoDateStart = "2017-02-11";
        String HotelTwoDateEnd = "2017-02-20";

        //Act
        /*creating Itinerary*/
        createItinerary(ItineraryID);
        /*Planning first flight from Copenhagen to Sydney*/
        
        GetFlightsResponse resultOneFlights = getFlights(ItineraryID, FlightOneCityFrom, FlightOneCityTo, startDateOne);
        int bookingNumberOneFlight = resultOneFlights.getReturn().get(0).getBookingNumber();
        int priceOneFlight = resultOneFlights.getReturn().get(0).getPrice();
        ItineraryContainerType resultOneFlight = addFlight(ItineraryID, bookingNumberOneFlight, priceOneFlight);
        
        assertEquals(1, resultOneFlight.getFlightReservationContainer().size());
        
        /*Planning first hotel in Sydney*/
        
        GetHotelsResponse resultOneHotel = getHotels(ItineraryID, HotelOneDateStart, HotelOneDateEnd, HotelOneCity);
        int bookingNumberOneHotel = resultOneHotel.getReturn().get(0).getBookingNumber();
        int priceOneHotel = resultOneFlights.getReturn().get(0).getPrice();        
        ItineraryContainerType resultHotelOne = addHotel(ItineraryID, bookingNumberOneHotel, priceOneHotel);
        
        assertEquals(1, resultHotelOne.getHotelReservationContainer().size());
        /*Planning second flight from Sydney to Thailand and From Thailand to Tokyo*/
        
        GetFlightsResponse resultTwoFlights = getFlights(ItineraryID, FlightTwoCityFrom, FlightTwoCityTo, startDateTwo);
        int bookingNumberTwoFlight = resultTwoFlights.getReturn().get(0).getBookingNumber();
        int priceTwoFlight = resultTwoFlights.getReturn().get(0).getPrice();
        ItineraryContainerType resultTwoFlight = addFlight(ItineraryID, bookingNumberTwoFlight, priceTwoFlight);
        
        assertEquals(2, resultTwoFlight.getFlightReservationContainer().size());
        
        GetFlightsResponse resultThreeFlights = getFlights(ItineraryID, FlightThreeCityFrom, FlightThreeCityTo, startDateThree);
        int bookingNumberThreeFlight = resultThreeFlights.getReturn().get(0).getBookingNumber();
        int priceThreeFlight = resultThreeFlights.getReturn().get(0).getPrice();
        ItineraryContainerType resultThreeFlight = addFlight(ItineraryID, bookingNumberThreeFlight, priceThreeFlight);
        
        assertEquals(3, resultThreeFlight.getFlightReservationContainer().size());
        
        /*Planning second hotel in Tokyo*/
        GetHotelsResponse resultTwoHotel = getHotels(ItineraryID, HotelTwoDateStart, HotelTwoDateEnd, HotelTwoCity);
        int bookingNumberTwoHotel = resultTwoHotel.getReturn().get(0).getBookingNumber();
        int priceTwoHotel = resultTwoFlights.getReturn().get(0).getPrice();        
        ItineraryContainerType resultHotelTwo = addHotel(ItineraryID, bookingNumberTwoHotel, priceTwoHotel);
        
        assertEquals(2, resultHotelTwo.getHotelReservationContainer().size());
        
        /*Book Itinerary*/
        String bookResult = bookItinerary(ItineraryID, testCard);
        //Assert
        assertEquals("confirmed", bookResult);
    }
    
    private static ItineraryContainerType createItinerary(java.lang.String itineraryID) {
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService service = new dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService();
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLPortType port = service.getTravelGoodWSDLPortTypeBindingPort();
        return port.createItinerary(itineraryID);
    }

    private static boolean cancelPlanningItinerary(java.lang.String itineraryID) {
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService service = new dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService();
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLPortType port = service.getTravelGoodWSDLPortTypeBindingPort();
        return port.cancelPlanningItinerary(itineraryID);
    }

    private static String bookItinerary(java.lang.String itineraryID, dk.dtu.webservice.airline.service.CreditCardInfoType creditCardInfo) {
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService service = new dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService();
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLPortType port = service.getTravelGoodWSDLPortTypeBindingPort();
        return port.bookItinerary(itineraryID, creditCardInfo);
    }

    private static GetFlightsResponse getFlights(java.lang.String itineraryID, java.lang.String startDestination, java.lang.String endDestination, java.lang.String startDate) {
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService service = new dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService();
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLPortType port = service.getTravelGoodWSDLPortTypeBindingPort();
        return port.getFlights(itineraryID, startDestination, endDestination, startDate);
    }

    private static GetHotelsResponse getHotels(java.lang.String itineraryID, java.lang.String arrivalDate, java.lang.String departureDate, java.lang.String cityName) {
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService service = new dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService();
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLPortType port = service.getTravelGoodWSDLPortTypeBindingPort();
        return port.getHotels(itineraryID, arrivalDate, departureDate, cityName);
    }

    private static ItineraryContainerType getItinerary(java.lang.String itineraryID) {
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService service = new dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService();
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLPortType port = service.getTravelGoodWSDLPortTypeBindingPort();
        return port.getItinerary(itineraryID);
    }

    private static ItineraryContainerType addFlight(java.lang.String itineraryID, int bookingNumber, int price) {
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService service = new dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService();
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLPortType port = service.getTravelGoodWSDLPortTypeBindingPort();
        return port.addFlight(itineraryID, bookingNumber, price);
    }

    private static ItineraryContainerType addHotel(java.lang.String itineraryID, int bookingNumber, int price) {
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService service = new dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLService();
        dk.dtu.webservice.travelagency.bpel.service.wsdl.travelgood2bpelmodule.src.travelgoodwsdl.TravelGoodWSDLPortType port = service.getTravelGoodWSDLPortTypeBindingPort();
        return port.addHotel(itineraryID, bookingNumber, price);
    }


    
}
