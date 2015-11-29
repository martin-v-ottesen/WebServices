package dk.dtu.ws;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class AirlineReservationServiceTest {
    
    @Before
    

    private static boolean createAirline(java.lang.String arg0) {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.createAirline(arg0);
    }

    private static boolean setFightData(java.lang.String arg0, dk.dtu.ws.Flightinformation arg1) throws FileNotFoundException_Exception {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.setFightData(arg0, arg1);
    }
    
    private static boolean clear() {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.clear();
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

    private static boolean cancelFlight(int arg0, int arg1, dk.dtu.imm.fastmoney.types.CreditCardInfoType arg2) throws CreditCardFaultMessage {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.cancelFlight(arg0, arg1, arg2);
    }
}
