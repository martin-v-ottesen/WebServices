package dk.dtu.ws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author Martin
 */
@javax.jws.WebService
public class AirlineReservation {
    File outputFile;
    
    public AirlineReservation(){
        outputFile = new File("FlightData");
    }

    public void setFightData(int bookingNumber, String airlineReservationService, String startDestination, String EndDestination, Date departureTime, Date ArrivalTime, double bookingPrice) throws FileNotFoundException {
        Flightinformation info = new Flightinformation(bookingNumber, airlineReservationService, startDestination, EndDestination, departureTime, ArrivalTime, bookingPrice);
        //File inputFile = new File("FlightData");
        PrintWriter output = new PrintWriter(outputFile);
        output.println(info);
        output.close();

    }

    public String getFlights(String Start, String Destination, Date date) {

        return "";
    }

    public String bookFlight() {
        return "";
    }

    public String cancelFlight() {
        return "";
    }

}
