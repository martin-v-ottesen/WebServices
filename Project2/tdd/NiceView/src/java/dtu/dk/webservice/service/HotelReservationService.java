/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.webservice.service;

import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dtu.dk.webservice.model.hotelInformation;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author dxong
 */
@WebService(serviceName = "HotelReservationService")
public class HotelReservationService {
    
    List<hotelInformation> hotelInformationContainer = new ArrayList<>();
    List<hotelInformation> bookedHotelInformationContainer = new ArrayList<>();
    
    Bank bankService = new Bank();
    
    @WebMethod(operationName = "setTestHotelInformations")
    public void setTestHotelInformations(@WebParam(name = "hotelInfo") hotelInformation hotelInfo){
        hotelInformationContainer.add(hotelInfo);
    }
    
    @WebMethod(operationName = "clearHotelInformations")
    public void clearHotelInformations(){
        hotelInformationContainer.clear();
        bookedHotelInformationContainer.clear();
    }

    @WebMethod(operationName = "getBookedHotelInformations")
    public List<hotelInformation> getBookedHotelInformations(){
        return bookedHotelInformationContainer;
    }

//    @WebMethod(operationName = "addBookedHotelInformations")
//    public void addBookedHotelInformations(@WebParam(name = "hotelInfo") hotelInformation hotelInfo){
//        bookedHotelInformationContainer.add(hotelInfo);
//    }

    /**
     * Web service operation
     * @param city
     * @param arrivalDate
     * @param departureDate
     * @return 
     */
    @WebMethod(operationName = "getHotels")
    public List<hotelInformation> getHotels(@WebParam(name = "cityName") String city, @WebParam(name = "arrivalDate") String arrivalDate,
            @WebParam(name = "departureDate") String departureDate) {
        List<hotelInformation> foundHotelInformationContainer = new ArrayList<>();
        
        for (hotelInformation hotelInfo : hotelInformationContainer) {
            if (hotelInfo.getHotel().getCity().equals(city) && hotelInfo.getHotel().getCheckInDate().equals(arrivalDate)
                    && hotelInfo.getHotel().getCheckOutDate().equals(departureDate)){
                foundHotelInformationContainer.add(hotelInfo);                
            }                
        }
        return foundHotelInformationContainer;
    }
    
    /**
     * Web service operation
     * @param bookingNumber
     * @param isCreditCardGuaranteeRequired
     * @param creditCardInfo
     * @return 
     * @throws dk.dtu.imm.fastmoney.CreditCardFaultMessage 
     */
    @WebMethod(operationName = "bookHotel")
    public boolean bookHotel(@WebParam(name = "bookingNumber") int bookingNumber,
            @WebParam(name = "isCreditCardGuaranteeRequired") boolean isCreditCardGuaranteeRequired,
            @WebParam(name = "creditCardInfo") CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage, Exception{
        
        System.out.println("booking number: " + bookingNumber);
        System.out.println("isCreditCardGuaranteeRequired : " + isCreditCardGuaranteeRequired);
        System.out.println("creditcard name: " + creditCardInfo.getName());
        System.out.println("creditcard number: " + creditCardInfo.getNumber());
        System.out.println("hotelInformationContainer size: " + hotelInformationContainer.size());
        boolean isHotelbooked;
        
        for (hotelInformation hotelInfo : hotelInformationContainer) {
            if (bookingNumber == hotelInfo.getBookingNumber()) {
                if (isCreditCardGuaranteeRequired){                   
                    bankService.validateCreditCard(creditCardInfo, hotelInfo.getPrice());
                }
                isHotelbooked = bankService.chargeCreditCard(creditCardInfo.getName(), creditCardInfo.getNumber(),
                        creditCardInfo.getExpirationDate().getYear(),
                        creditCardInfo.getExpirationDate().getMonth(), hotelInfo.getPrice());
                
                if (isHotelbooked){
                    bookedHotelInformationContainer.add(hotelInfo);
                    return isHotelbooked;
                }              
            }       
        }
        throw new Exception("Booking Number was not found: " + String.valueOf(bookingNumber));
    }

    /**
     * Web service operation
     * @param bookingNumber
     * @throws java.lang.Exception
     */
    @WebMethod(operationName = "cancelHotel")
    public void cancelHotel(@WebParam(name = "bookingNumber") int bookingNumber) throws Exception {
        for (hotelInformation hotelInfo : bookedHotelInformationContainer) {
                if (hotelInfo.getBookingNumber() == bookingNumber){
                    bookedHotelInformationContainer.remove(hotelInfo);
                    return;
                }
            }
        throw new Exception("Cancelling hotel can not be issued. Booking number: " + bookingNumber);
    }
}
