/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

/**
 *
 * @author Martin
 */
public class FlighListObject {
    private String airline;
    private Flightinformation flight;

    public FlighListObject(String airline, Flightinformation flight) {
        this.airline = airline;
        this.flight = flight;
    }

    public String getAirline() {
        return airline;
    }

    public Flightinformation getFlight() {
        return flight;
    }
}
