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
public class HotelBookingInformation {
    
    private double priceOfStay;
    private Hotel hotel;
    private int bookingNumber;
    private boolean creditcardGuarantee;
    
    public HotelBookingInformation(double priceOfStay, Hotel hotel, int bookingNumber) {
        this.priceOfStay = priceOfStay;
        this.hotel = hotel;
        this.bookingNumber = bookingNumber;
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

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
    
    
    
}
