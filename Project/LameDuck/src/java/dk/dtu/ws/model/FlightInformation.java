package dk.dtu.ws.model;

import org.joda.time.DateTime;

/**
 *
 * @author Martin
 */
public class FlightInformation {
    private int bookingNumber;
    private String startDestination;
    private String endDestination;
    private ProjectDate departureTime;
    private ProjectDate arrivalTime;
    //private String carrier;
    private int bookingPrice;

    public FlightInformation(int bookingNumber, String startDestination, String endDestination, ProjectDate departureTime, ProjectDate arrivalTime, /*String carrier,*/ int bookingPrice) {
        this.bookingNumber = bookingNumber;
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        //this.carrier = carrier;
        this.bookingPrice = bookingPrice;
    }

    public ProjectDate getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(ProjectDate ArrivalTime) {
        this.arrivalTime = ArrivalTime;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }
    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public int getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(int bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public ProjectDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ProjectDate departureTime) {
        this.departureTime = departureTime;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(String EndDestination) {
        this.endDestination = EndDestination;
    }

//    public String getCarrier() {
//        return carrier;
//    }
//
//    public void setCarrier(String carrier) {
//        this.carrier = carrier;
//    }
    
    public String getStartDestination() {
        return startDestination;
    }

    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }   
}
