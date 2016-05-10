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
public class Flight {
    
    String StartAirport;
    String EndAirport;
    String DateAndTimefForLiftOff;
    String DateAndTimefForLanding;
    String CarrierOperationTheFlight;

    public String getStartAirport() {
        return StartAirport;
    }

    public void setStartAirport(String StartAirport) {
        this.StartAirport = StartAirport;
    }

    public String getEndAirport() {
        return EndAirport;
    }

    public void setEndAirport(String EndAirport) {
        this.EndAirport = EndAirport;
    }

    public String getDateAndTimefForLiftOff() {
        return DateAndTimefForLiftOff;
    }

    public void setDateAndTimefForLiftOff(String DateAndTimefForLiftOff) {
        this.DateAndTimefForLiftOff = DateAndTimefForLiftOff;
    }

    public String getDateAndTimefForLanding() {
        return DateAndTimefForLanding;
    }

    public void setDateAndTimefForLanding(String DateAndTimefForLanding) {
        this.DateAndTimefForLanding = DateAndTimefForLanding;
    }

    public String getCarrierOperationTheFlight() {
        return CarrierOperationTheFlight;
    }

    public void setCarrierOperationTheFlight(String CarrierOperationTheFlight) {
        this.CarrierOperationTheFlight = CarrierOperationTheFlight;
    }

}
