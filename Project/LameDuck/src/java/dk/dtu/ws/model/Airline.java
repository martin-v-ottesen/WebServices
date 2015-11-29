/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.model;

import java.util.ArrayList;
import org.joda.time.DateTime;

/**
 *
 * @author Martin
 */
public class Airline {

    private String name;
    private ArrayList<FlightInformation> flights;
    
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
    
    public ArrayList<FlightInformation> getFlights() {
        return flights;
    }

    public ArrayList<FlightInformation> getFlights(String Start, String Destination, Date date) {
        ArrayList<FlightInformation> eligibleFlights = new ArrayList<>();
        DateTime JDate = date.getJodaTime(date);
        for (FlightInformation fligth : flights) {
            if (fligth.getStartDestination().toLowerCase().equals(Start.toLowerCase()) 
                    && fligth.getEndDestination().toLowerCase().equals(Destination.toLowerCase())
                    && fligth.getDepartureTime().dayOfYear().equals(JDate.dayOfYear())) {
                eligibleFlights.add(fligth);
            }
        }
        return eligibleFlights;
    }
    
    public FlightInformation getFlight(int index) {
        return flights.get(index);
    }

    public boolean addFlight(FlightInformation flight) {
        return flights.add(flight);
    }

    public boolean removeFlight(FlightInformation flight) {
        return flights.remove(flight);
    }
    
}
