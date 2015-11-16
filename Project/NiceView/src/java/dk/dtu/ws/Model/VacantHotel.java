/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.Model;

import java.util.ArrayList;

/**
 *
 * @author jacobmulvad
 */
public class VacantHotel {
    private ArrayList<HotelBookingInformation> hotelList;
    private String nameOfHotelBookingService;
    
    public VacantHotel(String nameOfHotelBookingService, ArrayList<HotelBookingInformation> hotelList) {
        this.nameOfHotelBookingService = nameOfHotelBookingService;
        this.hotelList = hotelList;
    }
    
    public void setNameOfHotelBookingService(String name) {
        this.nameOfHotelBookingService = name;
    }
    
    public void setHotelList(ArrayList<HotelBookingInformation> hotelList) {
        this.hotelList = hotelList;
    }
    
    public String getNameOfHotelBookingService() {
        return nameOfHotelBookingService;
    }
    
    public ArrayList<HotelBookingInformation> getHotelList() {
        return hotelList;
    }
}
