package dk.dtu.ws;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.ws.WebServiceRef;
import org.joda.time.*;

/**
 *
 * @author Martin
 */
@javax.jws.WebService
public class AirlineReservation {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;

    private ArrayList<Airline> airlines;
    private ArrayList<FlighListObject> bookingObjects;
    private ArrayList<Bookings> bookingList;

    public boolean createAirline(String airlineName) {
        Airline airline = new Airline(airlineName.toLowerCase());
        airlines.add(airline);
        return true;
    }

    public boolean setFightData(int bookingNumber, String airlineName, String airlineReservationService, String startDestination, String endDestination, DateTime departureTime, DateTime arrivalTime, int bookingPrice) throws FileNotFoundException {
        Flightinformation info = new Flightinformation(bookingNumber, startDestination, endDestination, departureTime, arrivalTime, bookingPrice);
        for (Airline airline : airlines) {
            if (airline.name.equals(airlineName.toLowerCase())) {
                airline.flights.add(info);
                return true;
            }
        }
        Airline airline = new Airline(airlineName.toLowerCase());
        airline.flights.add(info);
        airlines.add(airline);
        return true;
    }

    public ArrayList<FlighListObject> getFlights(String Start, String Destination, DateTime date) {
        ArrayList<FlighListObject> eligibleFlights = null;
        for (Airline airline : airlines) {
            ArrayList<Flightinformation> flights = airline.getFlights(Start, Destination, date);
            for(Flightinformation flight : flights){
                FlighListObject booking = new FlighListObject(Start, flight);
                bookingObjects.add(booking);
            }
        }
        return eligibleFlights;
    }

    public boolean bookFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage {
        if(validateCreditCard(1, creditCardInfo, 10000)){
            for(FlighListObject bookingObject : bookingObjects){
                if (bookingObject.getFlight().getBookingNumber() == bookingNumber){
                    for (Bookings booking : bookingList){
                        if (booking.getCreditCardInfo().equals(creditCardInfo)){
                            booking.addFlight(bookingObject);
                            return chargeCreditCard(1, creditCardInfo,  bookingObject.getFlight().getBookingPrice(), BankAccount.validAccount());
                        }
                    }
                    Bookings newCostumer = new Bookings(creditCardInfo, bookingObjects);
                    return chargeCreditCard(1, creditCardInfo,  bookingObject.getFlight().getBookingPrice(), BankAccount.validAccount());                 
                }
            }
        }        
        return false;
    }

    public boolean cancelFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage {
        if(validateCreditCard(1, creditCardInfo, 0)){
            for(FlighListObject bookingObject : bookingObjects){
                refundCreditCard(1, creditCardInfo,  bookingObject.getFlight().getBookingPrice()/2, BankAccount.validAccount()); 
                if (bookingObject.getFlight().getBookingNumber() == bookingNumber){
                    for (Bookings booking : bookingList){
                        if (booking.getCreditCardInfo().equals(creditCardInfo)){
                            booking.removeFlight(bookingObject);
                            return true;
                        }
                    }
                    return true;                
                }
            }
        }        
        return false;
    }

    private boolean validateCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.validateCreditCard(group, creditCardInfo, amount);
    }

    private boolean chargeCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.chargeCreditCard(group, creditCardInfo, amount, account);
    }

    private boolean refundCreditCard(int group, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo, int amount, dk.dtu.imm.fastmoney.types.AccountType account) throws CreditCardFaultMessage {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        dk.dtu.imm.fastmoney.BankPortType port = service.getBankPort();
        return port.refundCreditCard(group, creditCardInfo, amount, account);
    }

}
