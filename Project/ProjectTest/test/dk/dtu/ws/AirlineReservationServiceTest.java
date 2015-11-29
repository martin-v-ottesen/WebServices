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
    private AirlineReservation airlineReservation;
    
    //Airlines:
    private String airline1;
    private String airline2;
    private String airline3;
    
    //FlightInformation (planes)
    private FlightInformation flI1;
    
    
    
    @Before
    public void executeBeforeEach() throws FileNotFoundException_Exception{
        //Clean up
        airlineReservation.clear();
        
        //Set up
        airline1 = "SAS";
        airline2 = "Thomas Cook";
        airline3 = "Ryan Air";
        createAirline(airline1);
        createAirline(airline2);
        createAirline(airline3);
        
        setFightData(airline1, flI1);
        
        //create 
    }
    
    
    //Tests for createAirline method
    public void createAirline__Test(){
        
    }
    
    
    //Tests for setFightData method
    public void setFightData__Test(){
        
    }
    
    //Tests for getFlights method
    public void getFlights__Test(){
        
    }
    
    
    //Tests for bookFlight method
    public void bookFlight__Test(){
        
    }
    
    
    //Tests for cancelFlight method
    public void cancelFlight__Test(){
        
    }
    
    

    private static boolean createAirline(java.lang.String arg0) {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.createAirline(arg0);
    }

    private static boolean setFightData(java.lang.String arg0, dk.dtu.ws.FlightInformation arg1) throws FileNotFoundException_Exception {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.setFightData(arg0, arg1);
    }
    
    private static boolean clear() {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.clear();
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

    private static java.util.List<dk.dtu.ws.FlightListObject> getFlights(java.lang.String arg0, java.lang.String arg1, dk.dtu.ws.DateTime arg2) {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.getFlights(arg0, arg1, arg2);
    }   
}
