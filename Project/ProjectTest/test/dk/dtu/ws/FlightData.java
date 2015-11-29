/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

public class FlightData {
    static final FlightInformation Flight1(){
        ProjectDate departureTime = new ProjectDate();
        departureTime.setYear(2015);
        departureTime.setMonth(11);
        departureTime.setDay(29);
        ProjectDate arrivalTime = new ProjectDate();
        arrivalTime.setYear(2015);
        arrivalTime.setMonth(11);
        arrivalTime.setDay(30);
        
        FlightInformation flight1 = new FlightInformation();
        flight1.setBookingNumber(1001);
        flight1.setStartDestination("Copenhagen");
        flight1.setEndDestination("Bruxelles");
        flight1.setDepartureTime(departureTime);
        flight1.setBookingPrice(1500);
        
        return flight1;
    }
    
    static final FlightInformation Flight4(){
        ProjectDate departureTime = new ProjectDate();
        departureTime.setYear(2015);
        departureTime.setMonth(11);
        departureTime.setDay(29);
        ProjectDate arrivalTime = new ProjectDate();
        arrivalTime.setYear(2015);
        arrivalTime.setMonth(11);
        arrivalTime.setDay(30);
        
        FlightInformation flight1 = new FlightInformation();
        flight1.setBookingNumber(1004);
        flight1.setStartDestination("Copenhagen");
        flight1.setEndDestination("Bruxelles");
        flight1.setDepartureTime(departureTime);
        flight1.setBookingPrice(700);
        
        return flight1;
    }
    
    static final FlightInformation Flight2(){
        ProjectDate departureTime = new ProjectDate();
        departureTime.setYear(2015);
        departureTime.setMonth(12);
        departureTime.setDay(5);
        ProjectDate arrivalTime = new ProjectDate();
        arrivalTime.setYear(2015);
        arrivalTime.setMonth(12);
        arrivalTime.setDay(6);
        
        FlightInformation flight1 = new FlightInformation();
        flight1.setBookingNumber(1002);
        flight1.setStartDestination("Bruxelles");
        flight1.setEndDestination("Copenhagen");
        flight1.setDepartureTime(departureTime);
        flight1.setBookingPrice(1200);
        
        return flight1;
    }
    
    static final FlightInformation Flight3(){
        ProjectDate departureTime = new ProjectDate();
        departureTime.setYear(2015);
        departureTime.setMonth(12);
        departureTime.setDay(8);
        ProjectDate arrivalTime = new ProjectDate();
        arrivalTime.setYear(2015);
        arrivalTime.setMonth(12);
        arrivalTime.setDay(9);
        
        FlightInformation flight1 = new FlightInformation();
        flight1.setBookingNumber(1003);
        flight1.setStartDestination("Copenhagen");
        flight1.setEndDestination("London");
        flight1.setDepartureTime(departureTime);
        flight1.setBookingPrice(3500);
        
        return flight1;
    }
}
