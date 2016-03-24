/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws.Model;

import java.util.ArrayList;
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
    private boolean needCreditcardGuarantee;
    private ArrayList<Room> rooms;

    

    public Hotel(String name, String address, int bookingNumber, double dayPrice, String city, boolean needCreditcardGuarantee) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.needCreditcardGuarantee = needCreditcardGuarantee;
    }
    
    public boolean equals(Hotel hotel){
        return this.city.equals(hotel.getCity()) && this.name.equals(hotel.getName());
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

    public ArrayList<Room> getRooms() {
        return rooms;
    }
    
    public ArrayList<RoomListObject> getVacendRooms(ProjectDate arrival, ProjectDate departure){
        ArrayList<RoomListObject> result = null;
        ArrayList<ProjectDate> dates = null;
        ProjectDate tmp = arrival;
        do{
            dates.add(tmp);
        } while (!tmp.equals(departure));                
        for (Room room : rooms){            
            if (!room.has(dates)){
                result.add(new RoomListObject(this, room, arrival, dates.size()));
            }              
        }
        return result;        
    }
  
//    public double priceOfStay(Date departure, Date arrival){
//        
//        Date d1 = arrival;
//	Date d2 = departure;
//        int days = 0;
//	try {
//		DateTime dt1 = new DateTime(d1);
//		DateTime dt2 = new DateTime(d2);
//                
//                days = Days.daysBetween(dt1, dt2).getDays();
//
//
//	 } catch (Exception e) {
//		e.printStackTrace();
//	 }
//        return getDayPrice() * (double) days;
//    }
}
