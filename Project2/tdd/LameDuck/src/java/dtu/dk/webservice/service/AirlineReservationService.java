/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.webservice.service;

import dtu.dk.webservice.model.Flight;
import dtu.dk.webservice.model.flightInformation;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author dxong
 */
@WebService(serviceName = "AirlineReservationService")
public class AirlineReservationService {

    List<flightInformation> flightInformationContainer = new ArrayList<>();
    
    flightInformation flightInfo = new flightInformation();
    Flight flight = new Flight();
    
    @WebMethod(operationName = "setTestFlightInformations")
    public void setFlightInformationsTest(@WebParam(name = "flightInfo") flightInformation flightInfo){
        flightInformationContainer.add(flightInfo);
    }
    
    @WebMethod(operationName = "clearFlightInformations")
    public void clearFlightInformations(){
        flightInformationContainer.clear();
    }
    
    /**
     * Web service operation
     * @param startDestination
     * @param endDestination
     * @param startDate
     * @return 
     */
    @WebMethod(operationName = "getFlights")
    public List<flightInformation> getFlights(@WebParam(name = "startDestination") String startDestination, @WebParam(name = "endDestination") String endDestination,
            @WebParam(name = "startDate") String startDate) {
        List<flightInformation> flightInformationsfound = new ArrayList<>();
        
        for (flightInformation flightInformation : flightInformationContainer) {
            if (flightInformation.getFlight().getStartAirport().equals(startDestination) &&
                    flightInformation.getFlight().getEndAirport().equals(endDestination) &&
                    flightInformation.getFlight().getDateAndTimefForLiftOff().equals(startDate)) {
                flightInformationsfound.add(flightInformation);
            }
        }
        return flightInformationsfound;
    }
    
}
