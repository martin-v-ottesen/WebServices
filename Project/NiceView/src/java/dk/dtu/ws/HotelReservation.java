package dk.dtu.ws;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.ws.Model.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Martin
 */
@javax.jws.WebService
public class HotelReservation {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;
    
    private ArrayList<Hotel> hotels;
    private ArrayList<Bookings> bookingObjects;

    ArrayList<RoomListObject> hotelList = new ArrayList<RoomListObject>();

    public HotelReservation(ArrayList<RoomListObject> hotelList) {
        this.hotelList = hotelList;
    }
    
    public boolean createAirline(Hotel hotel) {        
        hotels.add(hotel);
        return true;
    }

    public boolean setRoomData(Hotel thisHotel, Room info) throws FileNotFoundException {
        for (Hotel hotel : hotels) {
            if (hotel.equals(thisHotel)) {
                hotel.getRooms().add(info);
                return true;
            }
        }
        Hotel hotel = thisHotel;
        hotel.getRooms().add(info);
        hotels.add(hotel);
        return true;
    }
    
    //For testpurposes ONLY!
    public boolean clear(){
        try{
            this.hotels.clear();
            this.bookingObjects.clear();
        } catch(Exception e){
            return false;
        }
        return true;        
    }

    public ArrayList<RoomListObject> getHotels(String city, ProjectDate arrival, ProjectDate departure) {
        ArrayList<RoomListObject> vacantHotelRooms = new ArrayList<>();        
        hotels.stream().forEach((hotel) -> {
            vacantHotelRooms.addAll(hotel.getVacendRooms(arrival, departure));
        });
        return vacantHotelRooms;
    }

    public boolean bookHotel(String bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage {
        if(validateCreditCard(1, creditCardInfo, 10000)){
            return true;
            
            
//            for(FlightListObject bookingObject : bookingObjects){
//                if (bookingObject.getFlight().getBookingNumber() == bookingNumber){
//                    for (Bookings booking : bookingList){
//                        if (booking.getCreditCardInfo().equals(creditCardInfo)){
//                            booking.addFlight(bookingObject);
//                            return chargeCreditCard(1, creditCardInfo,  bookingObject.getFlight().getBookingPrice(), BankAccount.validAccount());
//                        }
//                    }
//                    Bookings newCostumer = new Bookings(creditCardInfo, bookingObjects);
//                    return chargeCreditCard(1, creditCardInfo,  bookingObject.getFlight().getBookingPrice(), BankAccount.validAccount());                 
//                }
//            }
        }        
        return false;
    }

    public boolean cancelHotel(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage {
        if(validateCreditCard(1, creditCardInfo, 0)){
            return true;
//            for(FlighListObject bookingObject : bookingObjects){
//                if (bookingObject.getFlight().getBookingNumber() == bookingNumber){
//                    for (Bookings booking : bookingList){
//                        if (booking.getCreditCardInfo().equals(creditCardInfo)){
//                            booking.removeFlight(bookingObject);
//                            refundCreditCard(1, creditCardInfo,  bookingObject.getFlight().getBookingPrice()/2, BankAccount.validAccount()); 
//                            return true;
//                        }
//                    }
//                    return true;                
//                }
//            }
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
