package dk.dtu.ws;

import java.util.Date;

/**
 *
 * @author Martin
 */
public class Flightinformation {
    private int bookingNumber;
    private String airlineReservationService;
    private String startDestination;
    private String EndDestination;
    private Date departureTime;
    private Date ArrivalTime;
    private double bookingPrice;

    public Flightinformation(int bookingNumber, String airlineReservationService, String startDestination, String EndDestination, Date departureTime, Date ArrivalTime, double bookingPrice) {
        this.bookingNumber = bookingNumber;
        this.airlineReservationService = airlineReservationService;
        this.startDestination = startDestination;
        this.EndDestination = EndDestination;
        this.departureTime = departureTime;
        this.ArrivalTime = ArrivalTime;
        this.bookingPrice = bookingPrice;
    }

    public String getAirlineReservationService() {
        return airlineReservationService;
    }
    
    public void setAirlineReservationService(String airlineReservationService) {
        this.airlineReservationService = airlineReservationService;
    }

    public Date getArrivalTime() {
        return ArrivalTime;
    }
    
    public void setArrivalTime(Date ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }
    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public double getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(double bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getEndDestination() {
        return EndDestination;
    }

    public void setEndDestination(String EndDestination) {
        this.EndDestination = EndDestination;
    }

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
