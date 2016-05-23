/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.webservice.airline.service.Exception_Exception;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import dk.dtu.webservice.airline.service.FlightInformation;
import dk.dtu.webservice.hotel.service.HotelInformation;
import dtu.dk.webservice.model.ItineraryContainer;
import dtu.dk.webservice.model.ItineraryContainer.FlightObject;
import dtu.dk.webservice.model.ItineraryContainer.HotelObject;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * REST Web Service
 *
 * @author Martin
 */
@Path("/tg")
public class TravelGoodResource {

    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    @Context
    private ServletContext context;
//    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public TravelGoodResource() {

    }

    @GET
    @Path("/itinerary/new")
    public Response getSession() {
        UUID uuid = UUID.randomUUID();
        //ItineraryMap.put(uuid, new ItineraryContainer());
        context.setAttribute(uuid.toString(), new ItineraryContainer());
        return Response.status(Response.Status.OK).entity(uuid.toString()).build();
    }

    @GET
    @Path("/flight/{startDestination}/{endDestination}/{startDate}")
    public Response getFlightsToJSON(@PathParam("startDestination") String startDestination,
            @PathParam("endDestination") String endDestination,
            @PathParam("startDate") String startDate) {

        List<FlightInformation> flights = getFlights(startDestination, endDestination, startDate);
        flights.stream().forEach((flight) -> {
            context.setAttribute("f" + flight.getBookingNumber(), flight);
        });
        return Response.status(Response.Status.OK).entity(gson.toJson(flights)).build();
    }

    @GET
    @Path("/hotel/{cityName}/{arrivalDate}/{departureDate}")
    public Response getHotelsToJSON(@PathParam("cityName") String city,
            @PathParam("arrivalDate") String arrivalDate,
            @PathParam("departureDate") String departureDate) {
        List<HotelInformation> hotels = getHotels(city, arrivalDate, departureDate);
        hotels.stream().forEach((hotel) -> {
            context.setAttribute("h" + hotel.getBookingNumber(), hotel);
        });
        return Response.status(Response.Status.OK).entity(gson.toJson(hotels)).build();
    }

    @PUT
    @Path("/itinerary/{itineraryId}/flight")
    public Response addFlight(@PathParam("itineraryId") String itineraryId,
            String bookingNumber) {
        try {
            ItineraryContainer itenerary = (ItineraryContainer) context.getAttribute(itineraryId);
            if (itenerary.getItineraryState() == ItineraryContainer.ItineraryState.UNCONFIRMED) {
                try {
                    FlightInformation flight = (FlightInformation) context.getAttribute("f" + bookingNumber);
                    itenerary.addFlight(flight);
                    context.setAttribute(itineraryId, itenerary);
                    return Response.status(Response.Status.OK).build();
                } catch (Exception e) {
                    return Response.status(Response.Status.CONFLICT).entity("Unknown Booking Number").build();
                }
            } else {
                context.setAttribute(itineraryId, itenerary);
                return Response.status(Response.Status.CONFLICT).entity("Itinerary Already Locked").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity("Unknown ID").build();
        }
    }

    @PUT
    @Path("/itinerary/{itineraryId}/hotel")
    public Response addHotel(@PathParam("itineraryId") String itineraryId,
            String bookingNumber) {
        try {
            ItineraryContainer itenerary = (ItineraryContainer) context.getAttribute(itineraryId);
            if (itenerary.getItineraryState() == ItineraryContainer.ItineraryState.UNCONFIRMED) {
                try {
                    HotelInformation hotel = (HotelInformation) context.getAttribute("h" + bookingNumber);
                    itenerary.addHotel(hotel);
                    context.setAttribute(itineraryId, itenerary);
                    return Response.status(Response.Status.OK).build();
                } catch (Exception e) {
                    return Response.status(Response.Status.CONFLICT).entity("Unknown Booking Number").build();
                }
            } else {
                context.setAttribute(itineraryId, itenerary);
                return Response.status(Response.Status.CONFLICT).entity("Itinerary Already Locked").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity("Unknown ID").build();
        }
    }

    @GET
    @Path("/itinerary/{itineraryId}")
    public Response getItinerary(@PathParam("itineraryId") String itineraryId) {
        try {
            ItineraryContainer itenerary = (ItineraryContainer) context.getAttribute(itineraryId);
            return Response.status(Response.Status.OK)
                    .entity(gson.toJson(itenerary)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity("Unknown ID").build();
        }
    }

    @POST
    @Path("/itinerary/{itineraryId}")
    public Response bookItinerary(@PathParam("itineraryId") String itineraryId,
            String creditCardInfo) {
        CreditCardInfoType creditCard = gson.fromJson(creditCardInfo, CreditCardInfoType.class);
        System.err.println(itineraryId);

        ItineraryContainer itenerary;

        try {
            itenerary = (ItineraryContainer) context.getAttribute(itineraryId);
            System.err.println(itenerary.getItineraryState());
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity("Unknown ID").build();
        }
        try {
            if (validateCreditCard(1, creditCard, itenerary.getPrice())) {
                itenerary.setCreditCard(creditCard);
                try {
                    for (FlightObject flight : itenerary.getFlights()) {
                        bookFlight(flight.getFlight().getBookingNumber(), creditCard);
                        flight.setState(ItineraryContainer.ItineraryState.CONFIRMED);
                    }
                    for (HotelObject hotel : itenerary.getHotels()) {
                        bookHotel(hotel.getHotel().getBookingNumber(), hotel.getHotel().isIsCreditCardGuaranteeRequired(), creditCard);
                        hotel.setState(ItineraryContainer.ItineraryState.CONFIRMED);
                    }
                    itenerary.setItineraryState(ItineraryContainer.ItineraryState.CONFIRMED);
                } catch (Exception e) {
                    for (FlightObject flight : itenerary.getFlights()) {
                        if (flight.getState() == ItineraryContainer.ItineraryState.CONFIRMED) {
                            cancelFlight(flight.getFlight().getBookingNumber(), itenerary.getCreditCard(), flight.getFlight().getPrice());
                            flight.setState(ItineraryContainer.ItineraryState.CANCELED);
                        }
                    }
                    for (HotelObject hotel : itenerary.getHotels()) {
                        if (hotel.getState() == ItineraryContainer.ItineraryState.CONFIRMED) {
                            cancelHotel(hotel.getHotel().getBookingNumber());
                            hotel.setState(ItineraryContainer.ItineraryState.CANCELED);
                        }
                    }
                    itenerary.setItineraryState(ItineraryContainer.ItineraryState.CANCELED);
                    context.setAttribute(itineraryId, itenerary);
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
                context.setAttribute(itineraryId, itenerary);
                return Response.status(Response.Status.ACCEPTED).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Insufficient Funds").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

    @DELETE
    @Path("/itinerary/{itineraryId}")
    public Response cancelePlan(@PathParam("itineraryId") String itineraryId) {
        try {
            ItineraryContainer itenerary = (ItineraryContainer) context.getAttribute(itineraryId);
            System.out.println(itenerary.getItineraryState());
            switch (itenerary.getItineraryState()) {
                case UNCONFIRMED:
                    context.removeAttribute(itineraryId);
                    return Response.status(Response.Status.OK).build();
                case CONFIRMED:
                    Date today = new Date();
                    if (today.before(today)) {
                        for (FlightObject flight : itenerary.getFlights()) {
                            cancelFlight(flight.getFlight().getBookingNumber(), itenerary.getCreditCard(), flight.getFlight().getPrice());
                            flight.setState(ItineraryContainer.ItineraryState.CANCELED);
                        }
                        for (HotelObject hotel : itenerary.getHotels()) {
                            cancelHotel(hotel.getHotel().getBookingNumber());
                            hotel.setState(ItineraryContainer.ItineraryState.CANCELED);
                        }
                        itenerary.setItineraryState(ItineraryContainer.ItineraryState.CANCELED);
                        context.setAttribute(itineraryId, itenerary);
                        return Response.status(Response.Status.OK).build();
                    } else {
                        return Response.status(Response.Status.CONFLICT).entity("Trip Already Started").build();
                    }
                default:
                    return Response.status(Response.Status.CONFLICT).entity("Plan Already Canceled").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity("Unknown ID").build();
        }
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

    private static boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        dk.dtu.imm.fastmoney.BankService service = new dk.dtu.imm.fastmoney.BankService();
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }

    private static boolean bookFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws dk.dtu.webservice.airline.service.CreditCardFaultMessage, Exception_Exception {
        dk.dtu.webservice.airline.service.AirlineReservationService_Service service = new dk.dtu.webservice.airline.service.AirlineReservationService_Service();
        dk.dtu.webservice.airline.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        return port.bookFlight(bookingNumber, creditCardInfo);
    }

    private static boolean bookHotel(int bookingNumber, boolean isCreditCardGuaranteeRequired, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws dk.dtu.webservice.hotel.service.Exception_Exception, dk.dtu.webservice.hotel.service.CreditCardFaultMessage {
        dk.dtu.webservice.hotel.service.HotelReservationService_Service service = new dk.dtu.webservice.hotel.service.HotelReservationService_Service();
        dk.dtu.webservice.hotel.service.HotelReservationService port = service.getHotelReservationServicePort();
        return port.bookHotel(bookingNumber, isCreditCardGuaranteeRequired, creditCardInfo);
    }

    private static void cancelHotel(int bookingNumber) throws dk.dtu.webservice.hotel.service.Exception_Exception {
        dk.dtu.webservice.hotel.service.HotelReservationService_Service service = new dk.dtu.webservice.hotel.service.HotelReservationService_Service();
        dk.dtu.webservice.hotel.service.HotelReservationService port = service.getHotelReservationServicePort();
        port.cancelHotel(bookingNumber);
    }

    private static boolean cancelFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int price) throws Exception_Exception, dk.dtu.webservice.airline.service.CreditCardFaultMessage {
        dk.dtu.webservice.airline.service.AirlineReservationService_Service service = new dk.dtu.webservice.airline.service.AirlineReservationService_Service();
        dk.dtu.webservice.airline.service.AirlineReservationService port = service.getAirlineReservationServicePort();
        return port.cancelFlight(bookingNumber, creditCardInfo, price);
    }
}
