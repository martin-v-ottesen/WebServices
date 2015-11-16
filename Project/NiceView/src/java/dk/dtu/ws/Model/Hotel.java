/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.Model;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author dxong
 */
public class Hotel {                                                        
    private String name;
    private String city;
    private String address;
    private double dayPrice;
    private Date vacantStart;
    private Date vacantEnd;

    public Hotel(String name, String address, int bookingNumber, double dayPrice, String city, Date vacantStart, Date vacantEnd) {
        this.name = name;
        this.city = city;
        this.address = address;

        this.dayPrice = dayPrice;
        this.vacantStart = vacantStart;
        this.vacantEnd = vacantEnd;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public double getDayPrice() {
        return dayPrice;
    }

    public Date getVacantStart() {
        return vacantStart;
    }

    public Date getVacantEnd() {
        return vacantEnd;
    }
    
    public double priceOfStay(Date departure, Date arrival){
        
        Date d1 = arrival;
	Date d2 = departure;
        int days = 0;
	try {
		DateTime dt1 = new DateTime(d1);
		DateTime dt2 = new DateTime(d2);
                
                days = Days.daysBetween(dt1, dt2).getDays();


	 } catch (Exception e) {
		e.printStackTrace();
	 }
        return getDayPrice() * (double) days;
    }
}
