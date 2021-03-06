/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.webservice.airline.service;

import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dtu.dk.webservice.model.Flight;
import dtu.dk.webservice.model.flightInformation;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Kasper
 */
@WebService(serviceName = "AirlineReservationService")
public class AirlineReservationService {

    List<flightInformation> flightInformationContainer = new ArrayList<>();
    List<flightInformation> bookedflightInformationContainer = new ArrayList<>();
    Bank bankService = new Bank();
    
    flightInformation flightInfo = new flightInformation();
    Flight flight = new Flight();

    public AirlineReservationService() {
        
        flight.setStartAirport("Copenhagen");
        flight.setEndAirport("Tokyo");
        flight.setCarrierOperationTheFlight("ANA");
        flight.setDateAndTimefForLiftOff("2017-02-01");
        flight.setDateAndTimefForLanding("I Will never come back");
        
        flightInfo.setFlight(flight);
        
        flightInfo.setNameOfAirlineReservationService("LameDuck");
        flightInfo.setBookingNumber(365247);
        flightInfo.setPrice(35813);
    
        flightInformationContainer.add(flightInfo);
        
        //Flight 1
        flight = new Flight();
        flightInfo = new flightInformation();
        flight.setStartAirport("Copenhagen");
        flight.setEndAirport("Sydney");
        flight.setCarrierOperationTheFlight("SAS");
        flight.setDateAndTimefForLiftOff("2017-02-01");
        flight.setDateAndTimefForLanding("2017-02-02");

        flightInfo.setFlight(flight);

        flightInfo.setNameOfAirlineReservationService("LameDuck");
        flightInfo.setBookingNumber(11111);
        flightInfo.setPrice(2500);

        flightInformationContainer.add(flightInfo);

        //Flight 2
        flight = new Flight();
        flightInfo = new flightInformation();
        flight.setStartAirport("Sydney");
        flight.setEndAirport("Thailand");
        flight.setCarrierOperationTheFlight("Thai");
        flight.setDateAndTimefForLiftOff("2017-02-10");
        flight.setDateAndTimefForLanding("2017-02-10");

        flightInfo.setFlight(flight);

        flightInfo.setNameOfAirlineReservationService("LameDuck");
        flightInfo.setBookingNumber(22222);
        flightInfo.setPrice(1500);

        flightInformationContainer.add(flightInfo);

        //Flight 3
        flight = new Flight();
        flightInfo = new flightInformation();
        flight.setStartAirport("Thailand");
        flight.setEndAirport("Tokyo");
        flight.setCarrierOperationTheFlight("ANA");
        flight.setDateAndTimefForLiftOff("2017-02-10");
        flight.setDateAndTimefForLanding("2017-02-11");

        flightInfo.setFlight(flight);

        flightInfo.setNameOfAirlineReservationService("LameDuck");
        flightInfo.setBookingNumber(33333);
        flightInfo.setPrice(1200);

        flightInformationContainer.add(flightInfo);
    }
    
    
     /**
     * Web service operation
     * @author jacob
     */

    @WebMethod(operationName = "setTestFlightInformations")
    public void setFlightInformationsTest(@WebParam(name = "flightInfo") flightInformation flightInfo){
        flightInformationContainer.add(flightInfo);
    }
    
    /**
     * Web service operation
     * @author jacob
     */
    
    @WebMethod(operationName = "clearFlightInformations")
    public void clearFlightInformations(){
        flightInformationContainer.clear();
        bookedflightInformationContainer.clear();
    }
    
    /**
     * Web service operation
     * @author jacob
     * @param startDestination
     * @param endDestination
     * @param startDate
     * @return 
     */
    @WebMethod(operationName = "getFlights")
    public List<flightInformation> getFlights(@WebParam(name = "startDestination") String startDestination, @WebParam(name = "endDestination") String endDestination,
            @WebParam(name = "startDate") String startDate) {
        List<flightInformation> flightInformationsfound = new ArrayList<>();
        
        flightInformationContainer.stream().filter((flightInformation) -> (flightInformation.getFlight().getStartAirport().equals(startDestination) &&
                flightInformation.getFlight().getEndAirport().equals(endDestination) &&
                flightInformation.getFlight().getDateAndTimefForLiftOff().equals(startDate))).forEach((flightInformation) -> {
                    flightInformationsfound.add(flightInformation);
                });
        return flightInformationsfound;
    }
    
    /**
     * Web service operation
     * @author Kasper
     * @param bookingNumber
     * @param creditCardInfo
     * @return 
     * @throws dk.dtu.imm.fastmoney.CreditCardFaultMessage 
     */
    @WebMethod(operationName = "bookFlight")
    public boolean bookFlight(@WebParam(name = "bookingNumber") int bookingNumber, 
            @WebParam(name = "creditCardInfo") CreditCardInfoType creditCardInfo) throws CreditCardFaultMessage, Exception{
        System.out.println("booking number: " + bookingNumber);
        System.out.println("creditcard name: " + creditCardInfo.getName());
        System.out.println("creditcard number: " + creditCardInfo.getNumber());
        
        for (flightInformation flightInfo : flightInformationContainer) {
            if (flightInfo.getBookingNumber() == bookingNumber) {
                boolean isFlightBooked;
                isFlightBooked = bankService.chargeCreditCard(creditCardInfo.getName(), creditCardInfo.getNumber(),
                        creditCardInfo.getExpirationDate().getYear(),
                        creditCardInfo.getExpirationDate().getMonth(), flightInfo.getPrice());
                
                bookedflightInformationContainer.add(flightInfo);
                return isFlightBooked;
            }
        }
        throw new Exception("Booking Number was not found: " + String.valueOf(bookingNumber));
    }
    
    /**
     * Web service operation
     * @author Kasper
     * @param bookingNumber
     * @param creditCardInfo
     * @param price
     * @return 
     * @throws dk.dtu.imm.fastmoney.CreditCardFaultMessage 
    */
    @WebMethod(operationName = "cancelFlight")
    public boolean cancelFlight(@WebParam(name = "bookingNumber") int bookingNumber,
            @WebParam(name = "creditCardInfo") CreditCardInfoType creditCardInfo,
            @WebParam(name = "price") int price) throws CreditCardFaultMessage, Exception{
        
        for (flightInformation flightInfo : bookedflightInformationContainer) {
            if (flightInfo.getBookingNumber() == bookingNumber) {
                boolean isFlightBooked;
                int refundPrice = flightInfo.getPrice() / 2;
                isFlightBooked = bankService.refundCreditCard(creditCardInfo, refundPrice);
                
                bookedflightInformationContainer.remove(flightInfo);
                return isFlightBooked;
            }
        }
        throw new Exception("Booking Number was not found: " + String.valueOf(bookingNumber));
    }
}
