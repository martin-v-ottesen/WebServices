/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

import dk.dtu.ws.Model.HotelBookingInformation;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Martin
 */
@javax.jws.WebService
public class HotelReservation {
    
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
    public String bookHotel(int bookingNumber , String creditCardNumber){
        //if()
        return "";
    }
    public String cancelHotel(){
        return "";
    }
    
}
