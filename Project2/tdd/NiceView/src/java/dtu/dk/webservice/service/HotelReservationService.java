/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.webservice.service;

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
    
    @WebMethod(operationName = "setTestHotelInformations")
    public void setTestHotelInformations(@WebParam(name = "hotelInfo") hotelInformation hotelInfo){
        hotelInformationContainer.add(hotelInfo);
    }
    
    @WebMethod(operationName = "clearHotelInformations")
    public void clearHotelInformations(){
        hotelInformationContainer.clear();
        bookedHotelInformationContainer.clear();
    }

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
    
    
}
