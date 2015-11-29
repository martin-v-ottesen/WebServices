/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.model;

import java.util.ArrayList;
import org.joda.time.DateTime;
import org.jvnet.staxex.util.FinalArrayList;

/**
 *
 * @author Martin
 */
public class Airline {

    private String name;
    private ArrayList<Flightinformation> flights;
    
    public Airline(String name) {
        this.name = name;
        this.flights = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<Flightinformation> getFlights() {
        return flights;
    }

    public ArrayList<Flightinformation> getFlights(String Start, String Destination, DateTime date) {
        ArrayList<Flightinformation> eligibleFlights = new ArrayList<>();
        for (Flightinformation fligth : flights) {
            if (fligth.getStartDestination().toLowerCase().equals(Start.toLowerCase()) 
                    && fligth.getEndDestination().toLowerCase().equals(Destination.toLowerCase())
                    && fligth.getDepartureTime().dayOfYear().equals(date.dayOfYear())) {
                eligibleFlights.add(fligth);
            }
        }
        return eligibleFlights;
    }
    
    public Flightinformation getFlight(int index) {
        return flights.get(index);
    }

    public boolean addFlight(Flightinformation flight) {
        return flights.add(flight);
    }

    public boolean removeFlight(Flightinformation flight) {
        return flights.remove(flight);
    }
    
}
