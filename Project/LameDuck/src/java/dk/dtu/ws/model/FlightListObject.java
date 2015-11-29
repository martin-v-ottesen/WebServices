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
    
    //Attempt by JGM
    public void setAirLine(String airlineName){
        if(!airlineName.isEmpty()){
            this.airline = airlineName;
        }
    }

    public FlightInformation getFlight() {
        return flight;
    }
    
    //Attempt by JGM
    public void setFlight(FlightInformation flight){
        if(flight != null){
            this.flight = flight;
        }
    }
    
    public void setAirline(String name){
        if(!name.isEmpty()){
            this.airline = name;
        }
        else this.airline = "defaultName";
    }
    
    public String getReservationService(){
        return this.reservationService;
    }
}
