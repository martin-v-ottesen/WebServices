/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.webservice;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


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
    public String getFlight(@PathParam("startDestination") String startDestination,
			@PathParam("endDestination") String endDestination, 
			@PathParam("startDate") String startDate){
//        List<FlightInformation> flights = getFlights(startDestination, endDestination, startDate);
 
//        JsonArray flightsList = new JsonArray();
//        flights.stream().map((FlightInformation flightInormation) -> {
//            JsonObject flightInfo = new JsonObject();
//            flightInfo.addProperty("BookingNumber", flightInormation.getBookingNumber());
//            flightInfo.addProperty("AirlineReservationServic", flightInormation.getNameOfAirlineReservationService());
//            JsonObject flight = new JsonObject();
//            flight.addProperty("CarrierOperationTheFlight", flightInormation.getFlight().getCarrierOperationTheFlight());
//            flightInfo.add("flight", flight);
//            return flightInfo;
//        }).forEach((flightInfo) -> {  
//            flightsList.add(flightInfo);
//        });
        
//        return flightsList.toString();
        return startDestination + " " + endDestination + " " + startDate;
    }

}
