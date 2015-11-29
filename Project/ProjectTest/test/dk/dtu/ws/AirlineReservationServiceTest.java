package dk.dtu.ws;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import dk.dtu.ws.FlightListObject;

/**
 *
 * @author Martin
 */
public class AirlineReservationServiceTest {
    //Airlines:
    private String airline1;
    private String airline2;
    private String airline3;
    
    @Before
    public void executeBeforeEach(){
        //Clean up
        clear();
        
        //Set up
        airline1 = "SAS";
        airline2 = "Thomas Cook";
        airline3 = "Ryan Air";
        createAirline(airline1);
        createAirline(airline2);
        createAirline(airline3);
        
        setFightData(airline1, FlightData.Flight1());
        setFightData(airline2, FlightData.Flight2());
        setFightData(airline3, FlightData.Flight3());
        setFightData(airline1, FlightData.Flight4());
    }
    
    //Tests for getFlights method
    @Test
    public void getFlights_Returns2Flights_Test(){
        //Arrange
        String departureCity = "Copenhagen";
        String destinationCity = "Bruxelles";
        ProjectDate departureDate = new ProjectDate();
        departureDate.setYear(2015);
        departureDate.setMonth(11);
        departureDate.setDay(29);
        
        ProjectDate arrivalDate = new ProjectDate();
        arrivalDate.setYear(2015);
        arrivalDate.setMonth(11);
        arrivalDate.setDay(30);
        
        String airlineName1 = "SAS";
        FlightInformation flightInfo1 = new FlightInformation();//FlightData.Flight1();
        flightInfo1.setBookingNumber(1001);
        flightInfo1.setBookingPrice(1500);
        flightInfo1.setStartDestination("Copenhagen");
        flightInfo1.setEndDestination("Bruxelles");
        flightInfo1.setDepartureTime(departureDate);
        flightInfo1.setArrivalTime(arrivalDate);
        
        FlightListObject flo1 = new FlightListObject();
        flo1.setFlight(flightInfo1);
        flo1.setAirline(airlineName1);
        
        String airlineName2 = "SAS";
        FlightInformation flightInfo2 = new FlightInformation();
        flightInfo1.setBookingNumber(1004);
        flightInfo1.setBookingPrice(700);
        flightInfo1.setStartDestination("Copenhagen");
        flightInfo1.setEndDestination("Bruxelles");
        flightInfo1.setDepartureTime(departureDate);
        flightInfo1.setArrivalTime(arrivalDate);
        FlightListObject flo2 = new FlightListObject();
        flo2.setFlight(flightInfo2);
        flo2.setAirline(airlineName2);
        
        List<FlightListObject> expected = new ArrayList<FlightListObject>();
        expected.add(flo1);
        expected.add(flo2);
        
        //Act
        List<FlightListObject> result = getFlights(departureCity, destinationCity, departureDate);
        
        //Assert
        assertEquals(expected, result);
    }
    
    //Tests for bookFlight method
    public void bookFlight__Test(){
        //Arrange
        
        //Act
        
        //Assert
        
    }
    
    //Tests for cancelFlight method
    public void cancelFlight__Test(){
        //Arrange
        
        //Act
        
        //Assert
        
    }
    
    

    private static boolean createAirline(java.lang.String arg0) {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.createAirline(arg0);
    }

    private static boolean setFightData(java.lang.String arg0, dk.dtu.ws.FlightInformation arg1) {
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

    private static java.util.List<dk.dtu.ws.FlightListObject> getFlights(java.lang.String arg0, java.lang.String arg1, dk.dtu.ws.ProjectDate arg2) {
        dk.dtu.ws.AirlineReservationService service = new dk.dtu.ws.AirlineReservationService();
        dk.dtu.ws.AirlineReservation port = service.getAirlineReservationPort();
        return port.getFlights(arg0, arg1, arg2);
    }
    
}
