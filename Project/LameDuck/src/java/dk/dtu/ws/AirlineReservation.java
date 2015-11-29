package dk.dtu.ws;

import dk.dtu.ws.model.Airline;
import dk.dtu.ws.model.Bookings;
import dk.dtu.ws.model.FlightListObject;
import dk.dtu.ws.model.FlightInformation;
import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.ws.model.ProjectDate;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.ws.WebServiceRef;
import org.joda.time.*;

/**
 *
 * @author Martin
 */
@javax.jws.WebService
public class AirlineReservation implements Serializable {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;

    private ArrayList<Airline> airlines = new ArrayList<Airline>();
    private ArrayList<FlightListObject> bookingObjects = new ArrayList<>();
    private ArrayList<Bookings> bookingList = new ArrayList<>();

    public boolean createAirline(String airlineName) {
        Airline airline = new Airline(airlineName.toLowerCase());
        airlines.add(airline);
        return true;
    }

    //For test purposes only
    public boolean setFightData(String airlineName, FlightInformation info) {
        for (Airline airline : airlines) {
            if (airline.getName().equals(airlineName.toLowerCase())) {
                airline.getFlights().add(info);
                return true;
            }
        }
        Airline airline = new Airline(airlineName.toLowerCase());
        airline.getFlights().add(info);
        airlines.add(airline);
        return true;
    }
    
    //For testpurposes ONLY!
    public boolean clear(){
        try{
            this.airlines.clear();
            this.bookingList.clear();
            this.bookingObjects.clear();
        } catch(Exception e){
            return false;
        }
        return true;        
    }

    public ArrayList<FlightListObject> getFlights(String Start, String Destination, ProjectDate date) {
        ArrayList<FlightListObject> eligibleFlights = null;
        for (Airline airline : airlines) {
            //ProjectDate thisDate = new ProjectDate(date);
            ArrayList<FlightInformation> flights = airline.getFlights(Start, Destination, date);
            for(FlightInformation flight : flights){
                FlightListObject booking = new FlightListObject(Start, flight);
                bookingObjects.add(booking);
            }
        }
        return eligibleFlights;
    }

    public boolean bookFlight(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage {
        if(validateCreditCard(1, creditCardInfo, 10000)){
            for(FlightListObject bookingObject : bookingObjects){
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

    public boolean cancelFlight(int bookingNumber, int price, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage {
        refundCreditCard(1, creditCardInfo,  price/2, BankAccount.validAccount());
        
        for(FlightListObject bookingObject : bookingObjects){
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
        return true;
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
