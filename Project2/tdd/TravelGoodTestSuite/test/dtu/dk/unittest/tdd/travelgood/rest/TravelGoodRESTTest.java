/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.travelgood.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dk.dtu.webservice.airline.service.*;
import dk.dtu.webservice.hotel.service.*;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Martin
 */
public class TravelGoodRESTTest {

    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/TravleGoodREST/webresources/tg");
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
//    static String id;

    public TravelGoodRESTTest() {
        
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
    public void newIteneraryTest(){
        Response result = target.path("/itinerary/new")
                .request().get();
        assertEquals(200, result.getStatus());
        assertEquals(36, result.readEntity(String.class).length());
    }

    @Test
    public void getFlightTest() throws IOException {
        Response result = target.path("flight/"
                + FlightData.testFlightInformation().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        assertEquals(200, result.getStatus());
        List<FlightInformation> flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
        }.getType());
        assertEquals(1, flights.size());
        assertEquals(FlightData.testFlightInformation().getBookingNumber(),
                flights.get(0).getBookingNumber());
    }
   
    @Test
    public void getHotelTest() {
        HotelInformation hotelInfo = null;
        
        Response result = target.path("hotel/"
                + HotelData.testHotel1().getCity() + "/"
                + HotelData.testHotel1().getCheckInDate() + "/"
                + HotelData.testHotel1().getCheckOutDate())
                .request().get();
        assertEquals(200, result.getStatus());
        List<HotelInformation> hotels = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<HotelInformation>>() {}.getType());
        assertEquals(2, hotels.size());
        assertEquals(HotelData.testHotelInformation1().getBookingNumber(),
                hotels.get(0).getBookingNumber());
    }
    
    @Test
    public void addFlightTest(){
        Response result = target.path("/itinerary/new")
                .request().get();
        assertEquals(200, result.getStatus());
        String id = result.readEntity(String.class);
        
        result = target.path("flight/"
                + FlightData.testFlightInformation().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        assertEquals(200, result.getStatus());
        List<FlightInformation> flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
        }.getType());
        
        assertEquals(FlightData.testFlightInformation().getBookingNumber(),
                flights.get(0).getBookingNumber());
        
        result = target.path("itinerary/" + id + "/flight").request().put(Entity.entity(flights.get(0).getBookingNumber()+"", MediaType.TEXT_PLAIN_TYPE));
        if(result.getStatus()== 409){
            System.out.println(result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());        
    }
    
    @Test
    public void addHotelTest(){
        Response result = target.path("/itinerary/new")
                .request().get();
        assertEquals(200, result.getStatus());
        String id = result.readEntity(String.class);
        
        result = target.path("hotel/"
                + HotelData.testHotel1().getCity() + "/"
                + HotelData.testHotel1().getCheckInDate() + "/"
                + HotelData.testHotel1().getCheckOutDate())
                .request().get();
        assertEquals(200, result.getStatus());
        List<HotelInformation> hotels = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<HotelInformation>>() {}.getType());
        assertEquals(HotelData.testHotelInformation1().getBookingNumber(),
                hotels.get(0).getBookingNumber());
        
        result = target.path("itinerary/" + id + "/hotel").request().put(Entity.entity(hotels.get(0).getBookingNumber()+"", MediaType.TEXT_PLAIN_TYPE));
        if(result.getStatus()== 409){
            System.out.println(result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());        
    }
    
    @Test
    public void getItineraryTest(){
        Response result = target.path("/itinerary/new")
                .request().get();
        assertEquals(200, result.getStatus());
        String id = result.readEntity(String.class);
        
        result = target.path("flight/"
                + FlightData.testFlightInformation().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation().getFlight().getDateAndTimefForLiftOff())
                .request().get();
        assertEquals(200, result.getStatus());
        List<FlightInformation> flights = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<FlightInformation>>() {
        }.getType());
        
        assertEquals(FlightData.testFlightInformation().getBookingNumber(),
                flights.get(0).getBookingNumber());
        
        result = target.path("itinerary/" + id + "/flight").request().put(Entity.entity(flights.get(0).getBookingNumber()+"", MediaType.TEXT_PLAIN_TYPE));
        if(result.getStatus()== 409){
            System.out.println(result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        result = target.path("hotel/"
                + HotelData.testHotel1().getCity() + "/"
                + HotelData.testHotel1().getCheckInDate() + "/"
                + HotelData.testHotel1().getCheckOutDate())
                .request().get();
        assertEquals(200, result.getStatus());
        List<HotelInformation> hotels = gson.fromJson(result.readEntity(String.class),
                new TypeToken<List<HotelInformation>>() {}.getType());
        assertEquals(HotelData.testHotelInformation1().getBookingNumber(),
                hotels.get(0).getBookingNumber());
        
        result = target.path("itinerary/" + id + "/hotel").request().put(Entity.entity(hotels.get(0).getBookingNumber()+"", MediaType.TEXT_PLAIN_TYPE));
        if(result.getStatus()== 409){
            System.out.println(result.readEntity(String.class));
        }
        assertEquals(200, result.getStatus());
        
        result = target.path("itinerary/" + id).request().get();
        assertEquals(200, result.getStatus());
        System.out.println(result.readEntity(String.class));
      
    }
    
    @Test
    public void postBookItineraryTest(){
        int id = 1;
        Response result = target.path("/itinerary/"+ id).request().post(Entity.entity(BankData.validCard(), MediaType.WILDCARD_TYPE));
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
