/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dk.dtu.webservice.airline.service.FlightInformation;
import dk.dtu.webservice.hotel.service.HotelInformation;


/**
 * REST Web Service
 *
 * @author Martin
 */
@Path("/tg")
public class TravelGoodResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public TravelGoodResource() {
    }

    /**
     * Retrieves representation of an instance of dtu.dk.webservice.TravelGoodResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "DTU";
    }
    
    @GET
    @Path("/flight/{startDestination}/{endDestination}/{startDate}")
    public String getFlightsToJSON(@PathParam("startDestination") String startDestination,
			@PathParam("endDestination") String endDestination, 
			@PathParam("startDate") String startDate){
        List<FlightInformation> flights = getFlights(startDestination, endDestination, startDate);        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();                
        return gson.toJson(flights);
    }
    
    @GET
    @Path("/hotel/{cityName}/{arrivalDate}/{departureDate}")
    public String getHotelsToJSON(@PathParam("cityName") String city,
			@PathParam("arrivalDate") String arrivalDate, 
			@PathParam("departureDate") String departureDate){
        List<HotelInformation> hotels = getHotels(city, arrivalDate, departureDate);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();                
        return gson.toJson(hotels);
    }

    private static java.util.List<dk.dtu.webservice.airline.service.FlightInformation> getFlights(java.lang.String startDestination, java.lang.String endDestination, java.lang.String startDate) {
        dk.dtu.webservice.airline.service.AirlineReservationService_Service service = new dk.dtu.webservice.airline.service.AirlineReservationService_Service();
        dk.dtu.webservice.airline.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        return port.getFlights(startDestination, endDestination, startDate);
    }    

    private static java.util.List<dk.dtu.webservice.hotel.service.HotelInformation> getHotels(java.lang.String cityName, java.lang.String arrivalDate, java.lang.String departureDate) {
        dk.dtu.webservice.hotel.service.HotelReservationService_Service service = new dk.dtu.webservice.hotel.service.HotelReservationService_Service();
        dk.dtu.webservice.hotel.service.HotelReservationService port = service.getHotelReservationServicePort();
        return port.getHotels(cityName, arrivalDate, departureDate);
    }
}
