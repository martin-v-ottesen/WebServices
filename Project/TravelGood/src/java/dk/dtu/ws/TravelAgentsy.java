package dk.dtu.ws;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Martin
 */

@Path("TravelGood")
public class TravelAgentsy {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return ("DTU");
    }
    
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void put(String input) {
        
    }
    
    @Path("")
    @POST
    public void post(){
        
    }

    private static java.util.List<dk.dtu.ws.FlighListObject> getFlights(java.lang.String arg0, java.lang.String arg1, dk.dtu.ws.DateTime arg2) {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.getFlights(arg0, arg1, arg2);
    }

    private static boolean bookFlight(int arg0, dk.dtu.imm.fastmoney.types.CreditCardInfoType arg1) throws CreditCardFaultMessage {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.bookFlight(arg0, arg1);
    }

    private static boolean cancelFlight(int arg0, dk.dtu.imm.fastmoney.types.CreditCardInfoType arg1) throws CreditCardFaultMessage {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.cancelFlight(arg0, arg1);
    }
       
}
