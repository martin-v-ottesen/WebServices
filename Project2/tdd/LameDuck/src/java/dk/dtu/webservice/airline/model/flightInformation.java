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
public class flightInformation {

    int bookingNumber;
    int price;
    String nameOfAirlineReservationService; 
    Flight flight;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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

    public String getNameOfAirlineReservationService() {
        return nameOfAirlineReservationService;
    }

    public void setNameOfAirlineReservationService(String nameOfAirlineReservationService) {
        this.nameOfAirlineReservationService = nameOfAirlineReservationService;
    }
}
