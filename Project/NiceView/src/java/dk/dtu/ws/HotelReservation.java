/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.ws.Model.HotelBookingInformation;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Martin
 */
@javax.jws.WebService
public class HotelReservation {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/fastmoney.imm.dtu.dk_8080/BankService.wsdl")
    private BankService service;
    
    ArrayList<HotelBookingInformation> hotelList = new ArrayList<HotelBookingInformation>();
    
    public HotelReservation(ArrayList<HotelBookingInformation> hotelList){
        this.hotelList = hotelList;
    }
    
    public ArrayList<HotelBookingInformation> getHotels(String city,  Date arrival,  Date departure){
        ArrayList<HotelBookingInformation> vacantHotel = new ArrayList<HotelBookingInformation>();
        
        for(HotelBookingInformation hotelBookingInformation : hotelList){
            if(hotelBookingInformation.getHotel().getCity().equals(city)) {
                if(hotelBookingInformation.getHotel().getVacantStart().equals(arrival) || 
                        hotelBookingInformation.getHotel().getVacantStart().after(arrival) && 
                        hotelBookingInformation.getHotel().getVacantEnd().before(departure) ||
                                hotelBookingInformation.getHotel().getVacantEnd().equals(departure)){
                    //Get Price for the whole stay
                    hotelBookingInformation.setPriceOfStay(hotelBookingInformation.getHotel().priceOfStay(departure, arrival));
                    vacantHotel.add(hotelBookingInformation);
                
                }
            }
        }
        return vacantHotel;
    }
    public String bookHotel(int bookingNumber , dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo){
        //if()
        return "";
    }
    public String cancelHotel(int bookingNumber , dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo){
        return "";
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
