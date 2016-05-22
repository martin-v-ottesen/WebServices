/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.travelgood.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import dk.dtu.webservice.airline.service.*;
import dk.dtu.webservice.hotel.service.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
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
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

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
    public void getFlightTest() throws IOException {
        String result = target.path("flight/"
                + FlightData.testFlightInformation().getFlight().getStartAirport() + "/"
                + FlightData.testFlightInformation().getFlight().getEndAirport() + "/"
                + FlightData.testFlightInformation().getFlight().getDateAndTimefForLiftOff())
                .request().get(String.class);
        List<FlightInformation> flights = gson.fromJson(result,
                new TypeToken<List<FlightInformation>>() {
        }.getType());
        assertEquals(1, flights.size());
        assertEquals(FlightData.testFlightInformation().getBookingNumber(),
                flights.get(0).getBookingNumber());
    }

    @Test
    public void getHotelTest() {
        String result = target.path("hotel/"
                + HotelData.testHotel1().getCity() + "/"
                + HotelData.testHotel1().getCheckInDate() + "/"
                + HotelData.testHotel1().getCheckOutDate())
                .request().get(String.class);
        List<HotelInformation> hotelss = gson.fromJson(result,
                new TypeToken<List<HotelInformation>>() {}.getType());
        assertEquals(2, hotelss.size());
        assertEquals(HotelData.testHotelInformation1().getBookingNumber(),
                hotelss.get(0).getBookingNumber());
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
