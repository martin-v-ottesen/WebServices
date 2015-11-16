/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.Model;

/**
 *
 * @author dxong
 */
public class Hotel {                                                        
    private String name;
    private String address;
    private int bookingNumber;
    private double price;

    public Hotel(String name, String address, int bookingNumber, double price) {
        this.name = name;
        this.address = address;
        this.bookingNumber = bookingNumber;
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
