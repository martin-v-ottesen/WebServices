/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.integrationtest.travelgood.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dk.dtu.webservice.airline.service.CreditCardFaultMessage;
import dk.dtu.webservice.airline.service.Exception_Exception;
import dk.dtu.webservice.airline.service.FlightInformation;
import dk.dtu.webservice.hotel.service.HotelInformation;
import dtu.dk.webservice.model.ItineraryContainer;
import dtu.dk.webservice.model.ItineraryContainer.FlightObject;
import dtu.dk.webservice.model.ItineraryContainer.HotelObject;
import dtu.dk.webservice.model.ItineraryContainer.ItineraryState;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class IntegrationTestTravelGoodREST {
    
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/TravleGoodREST/webresources/tg");
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    
    public IntegrationTestTravelGoodREST() {
    }
    
    @Before
    public void setUp() {
        //Flight 1: Cobenhagen -> Bangkok / SAS
        setTestFlightInformations(FlightData.testFlightInformation1());
        //Flight 2: Cobenhagen -> Bangkok / Thai Airways
        setTestFlightInformations(FlightData.testFlightInformation2());
        //Flight 3: Bangkok -> Sidney / Austrian Airlines
        setTestFlightInformations(FlightData.testFlightInformation3());
        //Flight 4: Bangkok -> Sidney / Thai Airways
        setTestFlightInformations(FlightData.testFlightInformation4());
        //Flight 5: Sidney -> Cobenhagen / SAS
        setTestFlightInformations(FlightData.testFlightInformation5());

        //Hotel 1: Bangkok / HotelOfBangkok
        setTestHotelInformations(HotelData.testHotelInformation1());
        //Hotel 2: Bangkok / BLUE
        setTestHotelInformations(HotelData.testHotelInformation2());
        //Hotel 3: Bangkok / RADISON
        setTestHotelInformations(HotelData.testHotelInformation3());
        
        //Hotel 4: Sidney / SidneyOperahouseHotel
        setTestHotelInformations(HotelData.testHotelInformation4());
        //Hotel 5: Sidney / TrumphTower
        setTestHotelInformations(HotelData.testHotelInformation5());
        //Hotel 6: Sidney / RADISON
        setTestHotelInformations(HotelData.testHotelInformation6());
    }
    
    @After
    public void tearDown() {
        clearFlightInformations();
        clearHotelInformations();
    }
    
    /** (planning and booking) Plan a trip by first planning a fight (i.e. getting a list of fights and then
     *  adding a fight to the itinerary), then by planning a hotel, another fight, a third fight, and finally
     *  a hotel. Ask for the itinerary and check that it is correct using JUnit's assert statements { i.e.
     *  assertEquals, assertTrue, . . . { in particular, that the booking status for each item is unconfirmed.
     *  Book the itinerary and ask again for the itinerary. Check that each booking status is now confirmed
     */
    @Test
    public void testP1(){
        /*********************
        * Session Initiation *
        *********************/
        
        Response result = target.path("/itinerary/new")
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        final String sessionId = result.readEntity(String.class);
        
        /***************************
        * Get 1st. List of Flights * 
        ***************************/

        result = target.path("flight/"
                + FlightData.testFlightInformation1().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation1().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation1().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        List<FlightInformation> flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
                }.getType());
        
        assertEquals(2, flights.size());

        assertEquals(FlightData.testFlightInformation1().getBookingNumber(),
                flights.get(0).getBookingNumber());
        assertEquals(FlightData.testFlightInformation2().getBookingNumber(),
                flights.get(1).getBookingNumber());
        
        /****************************
        * Add Flight 1 to Itinerary * 
        ****************************/

        result = target.path("itinerary/" + sessionId + "/flight")
                .request().put(Entity.entity(flights.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /**************************
        * Get 1st. List of Hotels * 
        **************************/

        result = target.path("hotel/"
                + HotelData.testHotel1().getCity() + "/"
                + HotelData.testHotel1().getCheckInDate() + "/"
                + HotelData.testHotel1().getCheckOutDate())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        List<HotelInformation> hotels = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<HotelInformation>>() {
                }.getType());
        assertEquals(2, hotels.size());
        assertEquals(HotelData.testHotelInformation1().getBookingNumber(),
                hotels.get(0).getBookingNumber());
        assertEquals(HotelData.testHotelInformation2().getBookingNumber(),
                hotels.get(1).getBookingNumber());
        
        /***************************
        * Add Hotel 1 to Itinerary * 
        ***************************/

        result = target.path("itinerary/" + sessionId + "/hotel").request().put(Entity.entity(hotels.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /***************************
        * Get 2nd. List of Flights * 
        ***************************/

        result = target.path("flight/"
                + FlightData.testFlightInformation3().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation3().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation3().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
                }.getType());
        
        assertEquals(2, flights.size());

        assertEquals(FlightData.testFlightInformation3().getBookingNumber(),
                flights.get(0).getBookingNumber());
        assertEquals(FlightData.testFlightInformation4().getBookingNumber(),
                flights.get(1).getBookingNumber());
        
        /****************************
        * Add Flight 2 to Itinerary * 
        ****************************/

        result = target.path("itinerary/" + sessionId + "/flight")
                .request().put(Entity.entity(flights.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /***************************
        * Get 3rd. List of Flights * 
        ***************************/

        result = target.path("flight/"
                + FlightData.testFlightInformation5().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation5().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation5().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
                }.getType());
        
        assertEquals(1, flights.size());

        assertEquals(FlightData.testFlightInformation5().getBookingNumber(),
                flights.get(0).getBookingNumber());
        
        /****************************
        * Add Flight 3 to Itinerary * 
        ****************************/

        result = target.path("itinerary/" + sessionId + "/flight")
                .request().put(Entity.entity(flights.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /**************************
        * Get 2nd. List of Hotels * 
        **************************/

        result = target.path("hotel/"
                + HotelData.testHotel5().getCity() + "/"
                + HotelData.testHotel5().getCheckInDate() + "/"
                + HotelData.testHotel5().getCheckOutDate())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        hotels = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<HotelInformation>>() {
                }.getType());
        assertEquals(2, hotels.size());
        assertEquals(HotelData.testHotelInformation5().getBookingNumber(),
                hotels.get(0).getBookingNumber());
        assertEquals(HotelData.testHotelInformation6().getBookingNumber(),
                hotels.get(1).getBookingNumber());
        
        /***************************
        * Add Hotel 2 to Itinerary * 
        ***************************/

        result = target.path("itinerary/" + sessionId + "/hotel").request().put(Entity.entity(hotels.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        
        /**********************************************
        * Test if All Parts Of Booking are Unonfirmed * 
        **********************************************/
        
        result = target.path("itinerary/" + sessionId).request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        ItineraryContainer contence = gson.fromJson(result.readEntity(String.class),
                new TypeToken<ItineraryContainer>() {
                }.getType());
        assertEquals(ItineraryState.UNCONFIRMED, contence.getItineraryState());
        for (FlightObject flight : contence.getFlights()){
            assertEquals(ItineraryState.UNCONFIRMED, flight.getState());
        }
        for (HotelObject hotel : contence.getHotels()){
            assertEquals(ItineraryState.UNCONFIRMED, hotel.getState());
        }        
        
        /*****************
        * Book Itinerary * 
        *****************/        

        result = target.path("/itinerary/" + sessionId).request().post(Entity.entity(gson.toJson(BankData.validCard()), MediaType.WILDCARD_TYPE));
        if (result.getStatus() != 202) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(202, result.getStatus());

        /*********************************************
        * Test if All Parts Of Booking are Confirmed * 
        *********************************************/
        
        result = target.path("itinerary/" + sessionId).request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        contence = gson.fromJson(result.readEntity(String.class),
                new TypeToken<ItineraryContainer>() {
                }.getType());
        assertEquals(ItineraryState.CONFIRMED, contence.getItineraryState());
        for (FlightObject flight : contence.getFlights()){
            assertEquals(ItineraryState.CONFIRMED, flight.getState());
        }
        for (HotelObject hotel : contence.getHotels()){
            assertEquals(ItineraryState.CONFIRMED, hotel.getState());
        }
    }
    
    /** (cancel planning) Plan a trip by first getting a list of fights and then adding a fight to the itinerary.
     *  Then cancel planning.
     */
    @Test
    public void testP2(){
        /*********************
        * Session Initiation *
        *********************/
        
        Response result = target.path("/itinerary/new")
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        final String sessionId = result.readEntity(String.class);
        
        /**********************
        * Get List of Flights * 
        **********************/

        result = target.path("flight/"
                + FlightData.testFlightInformation1().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation1().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation1().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        List<FlightInformation> flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
                }.getType());
        
        assertEquals(2, flights.size());

        assertEquals(FlightData.testFlightInformation1().getBookingNumber(),
                flights.get(0).getBookingNumber());
        assertEquals(FlightData.testFlightInformation2().getBookingNumber(),
                flights.get(1).getBookingNumber());
        
        /**************************
        * Add Flight to Itinerary * 
        **************************/

        result = target.path("itinerary/" + sessionId + "/flight")
                .request().put(Entity.entity(flights.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /*****************
        * Cancel Planing *
        *****************/
        
        result = target.path("/itinerary/" + sessionId).request().delete();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /*************************************
        * Test That Planing has been cancled *
        *************************************/
        
        result = target.path("itinerary/" + sessionId).request().get();
        if (result.getStatus() != 409) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(409, result.getStatus());
        assertEquals("Unknown ID", result.readEntity(String.class));
    }
    
    /** (booking fails) Plan an itinerary with three bookings (mixed fights amd hotels). Get the itinerary
     *  and make sure that the booking status is unconfirmed for each entry. Then book the itinerary.
     *  During booking, the second booking should fail. Get the itinerary and check that the result of the
     *  bookTrip operation records a failure and that the returned itinerary has cancelled as the booking
     *  status of the rst booking and unconfirmed for the status of the second and third booking.
     */
    @Test
    public void testB(){
        /*********************
        * Session Initiation *
        *********************/
        
        Response result = target.path("/itinerary/new")
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        final String sessionId = result.readEntity(String.class);
        
        /***************************
        * Get 1st. List of Flights * 
        ***************************/

        result = target.path("flight/"
                + FlightData.testFlightInformation1().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation1().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation1().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        List<FlightInformation> flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
                }.getType());
        
        assertEquals(2, flights.size());

        assertEquals(FlightData.testFlightInformation1().getBookingNumber(),
                flights.get(0).getBookingNumber());
        assertEquals(FlightData.testFlightInformation2().getBookingNumber(),
                flights.get(1).getBookingNumber());
        
        /****************************
        * Add Flight 1 to Itinerary * 
        ****************************/

        result = target.path("itinerary/" + sessionId + "/flight")
                .request().put(Entity.entity(flights.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /*********************
        * Get List of Hotels * 
        *********************/

        result = target.path("hotel/"
                + HotelData.testHotel1().getCity() + "/"
                + HotelData.testHotel1().getCheckInDate() + "/"
                + HotelData.testHotel1().getCheckOutDate())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        List<HotelInformation> hotels = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<HotelInformation>>() {
                }.getType());
        assertEquals(2, hotels.size());
        assertEquals(HotelData.testHotelInformation1().getBookingNumber(),
                hotels.get(0).getBookingNumber());
        assertEquals(HotelData.testHotelInformation2().getBookingNumber(),
                hotels.get(1).getBookingNumber());
        
        /*************************
        * Add Hotel to Itinerary * 
        *************************/

        result = target.path("itinerary/" + sessionId + "/hotel").request().put(Entity.entity(hotels.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /***************************
        * Get 2nd. List of Flights * 
        ***************************/

        result = target.path("flight/"
                + FlightData.testFlightInformation3().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation3().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation3().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
                }.getType());
        
        assertEquals(2, flights.size());

        assertEquals(FlightData.testFlightInformation3().getBookingNumber(),
                flights.get(0).getBookingNumber());
        assertEquals(FlightData.testFlightInformation4().getBookingNumber(),
                flights.get(1).getBookingNumber());
        
        /****************************
        * Add Flight 2 to Itinerary * 
        ****************************/

        result = target.path("itinerary/" + sessionId + "/flight")
                .request().put(Entity.entity(flights.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /*******************************
        * Make sure 2nd booking failes *
        *******************************/
        
        clearFlightInformations();
        
        //Flight 1: Cobenhagen -> Bangkok / SAS
        setTestFlightInformations(FlightData.testFlightInformation1());
        //Flight 2: Cobenhagen -> Bangkok / Thai Airways
        setTestFlightInformations(FlightData.testFlightInformation2());
        //Flight 3: Bangkok -> Sidney / Austrian Airlines
        //setTestFlightInformations(FlightData.testFlightInformation3());
        //Flight 4: Bangkok -> Sidney / Thai Airways
        setTestFlightInformations(FlightData.testFlightInformation4());
        //Flight 5: Sidney -> Cobenhagen / SAS
        setTestFlightInformations(FlightData.testFlightInformation5());

        
        /*****************
        * Book Itinerary * 
        *****************/        

        result = target.path("/itinerary/" + sessionId).request().post(Entity.entity(gson.toJson(BankData.validCard()), MediaType.WILDCARD_TYPE));
        if (result.getStatus() != 404) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(404, result.getStatus());
        assertEquals("dtu.dk.webservice.TravelGoodResourcedk.dtu.webservice.airline.service.Exception_Exception: Booking Number was not found: 1357924680", result.readEntity(String.class));

        /*********************************************
        * Test if All Parts Of Booking are Canceled * 
        *********************************************/
        
        result = target.path("itinerary/" + sessionId).request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        ItineraryContainer contence = gson.fromJson(result.readEntity(String.class),
                new TypeToken<ItineraryContainer>() {
                }.getType());
        assertEquals(ItineraryState.CANCELED, contence.getItineraryState());
        for (FlightObject flight : contence.getFlights()){
            assertEquals(ItineraryState.CANCELED, flight.getState());
        }
        for (HotelObject hotel : contence.getHotels()){
            assertEquals(ItineraryState.CANCELED, hotel.getState());
        }        
    }
    
    /** (cancel booking) Create an itinerary with three bookings (mixed fights and hotels) and book it.
     *  Get the itinerary and make sure that the booking status is confirmed for each entry. Cancel the
     *  trip and check that now the booking status is cancelled for all bookings of the itinerary.
     */
    @Test
    public void testC1(){
        /*********************
        * Session Initiation *
        *********************/
        
        Response result = target.path("/itinerary/new")
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        final String sessionId = result.readEntity(String.class);
        
        /***************************
        * Get 1st. List of Flights * 
        ***************************/

        result = target.path("flight/"
                + FlightData.testFlightInformation1().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation1().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation1().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        List<FlightInformation> flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
                }.getType());
        
        assertEquals(2, flights.size());

        assertEquals(FlightData.testFlightInformation1().getBookingNumber(),
                flights.get(0).getBookingNumber());
        assertEquals(FlightData.testFlightInformation2().getBookingNumber(),
                flights.get(1).getBookingNumber());
        
        /****************************
        * Add Flight 1 to Itinerary * 
        ****************************/

        result = target.path("itinerary/" + sessionId + "/flight")
                .request().put(Entity.entity(flights.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /*********************
        * Get List of Hotels * 
        *********************/

        result = target.path("hotel/"
                + HotelData.testHotel1().getCity() + "/"
                + HotelData.testHotel1().getCheckInDate() + "/"
                + HotelData.testHotel1().getCheckOutDate())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        List<HotelInformation> hotels = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<HotelInformation>>() {
                }.getType());
        assertEquals(2, hotels.size());
        assertEquals(HotelData.testHotelInformation1().getBookingNumber(),
                hotels.get(0).getBookingNumber());
        assertEquals(HotelData.testHotelInformation2().getBookingNumber(),
                hotels.get(1).getBookingNumber());
        
        /*************************
        * Add Hotel to Itinerary * 
        *************************/

        result = target.path("itinerary/" + sessionId + "/hotel").request().put(Entity.entity(hotels.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /***************************
        * Get 2nd. List of Flights * 
        ***************************/

        result = target.path("flight/"
                + FlightData.testFlightInformation3().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation3().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation3().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
                }.getType());
        
        assertEquals(2, flights.size());

        assertEquals(FlightData.testFlightInformation3().getBookingNumber(),
                flights.get(0).getBookingNumber());
        assertEquals(FlightData.testFlightInformation4().getBookingNumber(),
                flights.get(1).getBookingNumber());
        
        /****************************
        * Add Flight 2 to Itinerary * 
        ****************************/

        result = target.path("itinerary/" + sessionId + "/flight")
                .request().put(Entity.entity(flights.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
          
        /*****************
        * Book Itinerary * 
        *****************/        

        result = target.path("/itinerary/" + sessionId).request().post(Entity.entity(gson.toJson(BankData.validCard()), MediaType.WILDCARD_TYPE));
        if (result.getStatus() != 202) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(202, result.getStatus());

        /*********************************************
        * Test if All Parts Of Booking are Confirmed * 
        *********************************************/
        
        result = target.path("itinerary/" + sessionId).request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        ItineraryContainer contence = gson.fromJson(result.readEntity(String.class),
                new TypeToken<ItineraryContainer>() {
                }.getType());
        assertEquals(ItineraryState.CONFIRMED, contence.getItineraryState());
        for (FlightObject flight : contence.getFlights()){
            assertEquals(ItineraryState.CONFIRMED, flight.getState());
        }
        for (HotelObject hotel : contence.getHotels()){
            assertEquals(ItineraryState.CONFIRMED, hotel.getState());
        }
        
        /**************************
        * Cancel Booked Itinerary * 
        **************************/
        
        result = target.path("/itinerary/" + sessionId).request().delete();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /*********************************************
        * Test if All Parts Of Booking are Canceled * 
        *********************************************/

        result = target.path("itinerary/" + sessionId).request().get();
        assertEquals(200, result.getStatus());
        contence = gson.fromJson(result.readEntity(String.class),
                new TypeToken<ItineraryContainer>() {
        }.getType());        
        assertEquals(ItineraryState.CANCELED,contence.getItineraryState());
        for (FlightObject flight : contence.getFlights()){
            assertEquals(ItineraryState.CANCELED, flight.getState());
        }
        for (HotelObject hotel : contence.getHotels()){
            assertEquals(ItineraryState.CANCELED, hotel.getState());
        }
        
    }
    
    /**
     *
     */
    @Test
    public void testC2(){
        /*********************
        * Session Initiation *
        *********************/
        
        Response result = target.path("/itinerary/new")
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        final String sessionId = result.readEntity(String.class);
        
        /***************************
        * Get 1st. List of Flights * 
        ***************************/

        result = target.path("flight/"
                + FlightData.testFlightInformation1().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation1().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation1().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        List<FlightInformation> flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
                }.getType());
        
        assertEquals(2, flights.size());

        assertEquals(FlightData.testFlightInformation1().getBookingNumber(),
                flights.get(0).getBookingNumber());
        assertEquals(FlightData.testFlightInformation2().getBookingNumber(),
                flights.get(1).getBookingNumber());
        
        /****************************
        * Add Flight 1 to Itinerary * 
        ****************************/

        result = target.path("itinerary/" + sessionId + "/flight")
                .request().put(Entity.entity(flights.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /*********************
        * Get List of Hotels * 
        *********************/

        result = target.path("hotel/"
                + HotelData.testHotel1().getCity() + "/"
                + HotelData.testHotel1().getCheckInDate() + "/"
                + HotelData.testHotel1().getCheckOutDate())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        List<HotelInformation> hotels = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<HotelInformation>>() {
                }.getType());
        assertEquals(2, hotels.size());
        assertEquals(HotelData.testHotelInformation1().getBookingNumber(),
                hotels.get(0).getBookingNumber());
        assertEquals(HotelData.testHotelInformation2().getBookingNumber(),
                hotels.get(1).getBookingNumber());
        
        /*************************
        * Add Hotel to Itinerary * 
        *************************/

        result = target.path("itinerary/" + sessionId + "/hotel").request().put(Entity.entity(hotels.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        /***************************
        * Get 2nd. List of Flights * 
        ***************************/

        result = target.path("flight/"
                + FlightData.testFlightInformation3().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation3().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation3().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
                }.getType());
        
        assertEquals(2, flights.size());

        assertEquals(FlightData.testFlightInformation3().getBookingNumber(),
                flights.get(0).getBookingNumber());
        assertEquals(FlightData.testFlightInformation4().getBookingNumber(),
                flights.get(1).getBookingNumber());
        
        /****************************
        * Add Flight 2 to Itinerary * 
        ****************************/

        result = target.path("itinerary/" + sessionId + "/flight")
                .request().put(Entity.entity(flights.get(0).getBookingNumber() + "", MediaType.TEXT_PLAIN_TYPE));
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
          
        /*****************
        * Book Itinerary * 
        *****************/        

        result = target.path("/itinerary/" + sessionId).request().post(Entity.entity(gson.toJson(BankData.validCard()), MediaType.WILDCARD_TYPE));
        if (result.getStatus() != 202) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(202, result.getStatus());

        /*********************************************
        * Test if All Parts Of Booking are Confirmed * 
        *********************************************/
        
        result = target.path("itinerary/" + sessionId).request().get();
        if (result.getStatus() != 200) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        ItineraryContainer contence = gson.fromJson(result.readEntity(String.class),
                new TypeToken<ItineraryContainer>() {
                }.getType());
        assertEquals(ItineraryState.CONFIRMED, contence.getItineraryState());
        for (FlightObject flight : contence.getFlights()){
            assertEquals(ItineraryState.CONFIRMED, flight.getState());
        }
        for (HotelObject hotel : contence.getHotels()){
            assertEquals(ItineraryState.CONFIRMED, hotel.getState());
        }
        
        /*******************************
        * Make sure 2nd booking failes *
        *******************************/
        
        clearFlightInformations();
        
        //Flight 1: Cobenhagen -> Bangkok / SAS
        setTestFlightInformations(FlightData.testFlightInformation1());
        //Flight 2: Cobenhagen -> Bangkok / Thai Airways
        setTestFlightInformations(FlightData.testFlightInformation2());
        //Flight 3: Bangkok -> Sidney / Austrian Airlines
        //setTestFlightInformations(FlightData.testFlightInformation3());
        //Flight 4: Bangkok -> Sidney / Thai Airways
        setTestFlightInformations(FlightData.testFlightInformation4());
        //Flight 5: Sidney -> Cobenhagen / SAS
        setTestFlightInformations(FlightData.testFlightInformation5());
        try {
            bookFlight(FlightData.testFlightInformation1().getBookingNumber(), BankData.validCard());
        } catch (CreditCardFaultMessage ex) {
            Logger.getLogger(IntegrationTestTravelGoodREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception_Exception ex) {
            Logger.getLogger(IntegrationTestTravelGoodREST.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        /**************************
        * Cancel Booked Itinerary * 
        **************************/
        
        result = target.path("/itinerary/" + sessionId).request().delete();
        if (result.getStatus() != 404) {
            System.err.println(result.getStatus() + " - " + result.readEntity(String.class));
        }
        assertEquals(404, result.getStatus());

        result = target.path("itinerary/" + sessionId).request().get();
        assertEquals(200, result.getStatus());
        //System.err.println(result.readEntity(String.class));
        contence = gson.fromJson(result.readEntity(String.class),
                new TypeToken<ItineraryContainer>() {
        }.getType());        
        //assertEquals(ItineraryState.CANCELED,contence.getItineraryState());
        assertEquals(ItineraryState.CANCELED, contence.getFlights().get(0).getState());
        assertEquals(ItineraryState.CONFIRMED, contence.getFlights().get(1).getState());
        assertEquals(ItineraryState.CANCELED, contence.getHotels().get(0).getState());                    
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

    private static boolean bookFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage, Exception_Exception {
        dk.dtu.webservice.airline.service.AirlineReservationService_Service service = new dk.dtu.webservice.airline.service.AirlineReservationService_Service();
        dk.dtu.webservice.airline.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        return port.bookFlight(bookingNumber, creditCardInfo);
    }
}
