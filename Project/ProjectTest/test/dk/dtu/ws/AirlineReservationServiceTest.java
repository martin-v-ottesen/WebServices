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
    

    private static String getFlights() {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.getFlights();
    }

    private static String bookFlight() {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.bookFlight();
    }

    private static String cancelFlight() {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.cancelFlight();
    }
}
