/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.model;

/**
 *
 * @author Martin
 */
public class FlightListObject {
    private String airline;
    private FlightInformation flight;
    private String reservationService = "LameDuck";

    public FlightListObject(String airline, FlightInformation flight) {
        this.airline = airline;
        this.flight = flight;
    }

    public String getAirline() {
        return airline;
    }

    public FlightInformation getFlight() {
        return flight;
    }
    
    public String getReservationService(){
        return this.reservationService;
    }
}
