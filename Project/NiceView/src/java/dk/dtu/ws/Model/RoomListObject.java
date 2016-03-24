/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.Model;

import java.util.Date;

/**
 *
 * @author jacobmulvad
 */
public class RoomListObject {
    
    private double priceOfStay;
    private Hotel hotel;
    private String bookingNumber;
    private boolean creditcardGuarantee;
    
    public RoomListObject(Hotel hotel, Room room, ProjectDate arrival, int days) {
        this.priceOfStay = days * room.getDayPrice();
        this.hotel = hotel;
        this.bookingNumber = hotel.getName()+":"+hotel.getCity()+":"+room.getNr()+":"+arrival.toString()+":"+days;
    }
    
    public double getPriceOfStay() {
        return priceOfStay;
    }
    
    public void setPriceOfStay(double priceOfStay){
        this.priceOfStay = priceOfStay;
    }
    
    public Hotel getHotel() {
        return hotel;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
    
    
    
}
