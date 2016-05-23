/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.webservice.model;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.ArrayList;
import java.util.List;
import dk.dtu.webservice.airline.service.FlightInformation;
import dk.dtu.webservice.hotel.service.HotelInformation;

/**
 *
 * @author Martin
 */
public class ItineraryContainer {

    private List<HotelInformation> hotels;
    private List<FlightInformation> flights;
    private ItineraryState state;
    private CreditCardInfoType creditCard;
    private int price;

    public enum ItineraryState {

        UNCONFERMED, CONFIRMED, CANCELED;
    }

    public ItineraryContainer() {
        hotels = new ArrayList<>();
        flights = new ArrayList<>();
        state = ItineraryState.UNCONFERMED;
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
            price = price + hotel.getPrice();
        });
        flights.stream().forEach((flight) -> {
            price = price + flight.getPrice();
        });
    }

    public ItineraryState getState() {
        return state;
    }

    public void setState(ItineraryState state) {
        this.state = state;
    }

    public List<FlightInformation> getFlights() {
        return flights;
    }

    public List<HotelInformation> getHotels() {
        return hotels;
    }

    public void addFlight(FlightInformation flight) {
        flights.add(flight);
        setPrice();
    }

    public void addHotel(HotelInformation hotel) {
        hotels.add(hotel);
        setPrice();
    }

    public void removeFlight(FlightInformation flight) {
        flights.remove(flight);
        setPrice();
    }

    public void removeHotel(HotelInformation hotel) {
        hotels.remove(hotel);
        setPrice();
    }

}
