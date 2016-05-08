/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.webservice.model;

/**
 *
 * @author dxong
 */
public class hotelInformation {
    int bookingNumber;
    int price;
    String nameOfHotelReservationService;
    Hotel hotel;
    boolean isCreditCardGuaranteeRequired;

    public boolean isIsCreditCardGuaranteeRequired() {
        return isCreditCardGuaranteeRequired;
    }

    public void setIsCreditCardGuaranteeRequired(boolean isCreditCardGuaranteeRequired) {
        this.isCreditCardGuaranteeRequired = isCreditCardGuaranteeRequired;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNameOfHotelReservationService() {
        return nameOfHotelReservationService;
    }

    public void setNameOfHotelReservationService(String nameOfHotelReservationService) {
        this.nameOfHotelReservationService = nameOfHotelReservationService;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
}
