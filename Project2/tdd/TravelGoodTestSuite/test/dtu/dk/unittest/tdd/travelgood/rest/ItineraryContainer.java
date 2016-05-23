/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.travelgood.rest;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.ArrayList;
import java.util.List;
import dk.dtu.webservice.airline.service.FlightInformation;
import dk.dtu.webservice.hotel.service.HotelInformation;
import java.sql.Date;
import java.util.GregorianCalendar;
import sun.util.BuddhistCalendar;
import sun.util.calendar.Gregorian;

/**
 *
 * @author Martin
 */
public class ItineraryContainer {

    private List<HotelObject> hotels;
    private List<FlightObject> flights;
    private ItineraryState itineraryState;
    private CreditCardInfoType creditCard;
    private int price;
    private Date startDate = Date.valueOf("2001-01-01");
    
    public class HotelObject {
        private HotelInformation hotel;
        private ItineraryState state;    

        public HotelObject(HotelInformation hotel) {
            this.hotel = hotel;
            this.state = ItineraryState.UNCONFIRMED;
        }
        
        public void setState(ItineraryState state){
            this.state = state;
        }
        public ItineraryState getState(){
            return state;
        }
        
        public HotelInformation getHotel(){
            return hotel;
        }
    }
    
    public class FlightObject {
        private FlightInformation flight;
        private ItineraryState state;    

        public FlightObject(FlightInformation flight) {
            this.flight = flight;
            this.state = ItineraryState.UNCONFIRMED;
        }
        
        public void setState(ItineraryState state){
            this.state = state;
        }
        
        public ItineraryState getState(){
            return state;
        }
        
        public FlightInformation getFlight(){
            return flight;
        }
    }
    
    
    public enum ItineraryState {

        UNCONFIRMED, CONFIRMED, CANCELED;
    }

    public ItineraryContainer() {
        hotels = new ArrayList<>();
        flights = new ArrayList<>();
        itineraryState = ItineraryState.UNCONFIRMED;
    }

    public CreditCardInfoType getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardInfoType creditCard) {
        this.creditCard = creditCard;
    }
    
    public int getPrice() {
        return price;
    }

    private void setPrice() {
        price = 0;
        hotels.stream().forEach((hotel) -> {
            price = price + hotel.getHotel().getPrice();
        });
        flights.stream().forEach((flight) -> {
            price = price + flight.getFlight().getPrice();
        });
    }

    public ItineraryState getItineraryState() {
        return itineraryState;
    }

    public void setItineraryState(ItineraryState itineraryState) {
        this.itineraryState = itineraryState;
    }

    public List<FlightObject> getFlights() {
        return flights;
    }

    public List<HotelObject> getHotels() {
        return hotels;
    }

    public void addFlight(FlightInformation flight) {
        flights.add(new FlightObject(flight));
        Date date = Date.valueOf(flight.getFlight().getDateAndTimefForLiftOff());  
        if(date.before(startDate)){
            startDate = date;
        }
        setPrice();
    }

    public void addHotel(HotelInformation hotel) {
        hotels.add(new HotelObject(hotel));
        Date date = Date.valueOf(hotel.getHotel().getCheckInDate());  
        if(date.before(startDate)){
            startDate = date;
        }
        setPrice();
    }

    public void removeFlight(FlightInformation flight) {
        for (FlightObject obj : flights){
            if(obj.flight.getBookingNumber() == flight.getBookingNumber()){
                flights.remove(obj);
                break;
            }                
        }        
        setPrice();
    }

    public void removeHotel(HotelInformation hotel) {
        for(HotelObject obj : hotels){
            if(obj.hotel.getBookingNumber() == hotel.getBookingNumber()){
                hotels.remove(obj);
                break;
            }
        }        
        setPrice();
    }
}
