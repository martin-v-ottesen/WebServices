/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.unittest.tdd.travelgood.rest;

import dtu.dk.unittest.tdd.niceview.*;
import dk.dtu.webservice.hotel.service.Hotel;
import dk.dtu.webservice.hotel.service.HotelInformation;

/**
 *
 * @author Martin
 */
public class HotelData {

    public static HotelInformation testHotelInformation1() {
        HotelInformation expectedHotelInfo = new HotelInformation();
        Hotel hotel = testHotel1();

        expectedHotelInfo.setNameOfHotelReservationService("NiceView");
        expectedHotelInfo.setHotel(hotel);
        expectedHotelInfo.setBookingNumber(12345);
        expectedHotelInfo.setPrice(5000);
        expectedHotelInfo.setIsCreditCardGuaranteeRequired(true);

        return expectedHotelInfo;
    }

    public static HotelInformation testHotelInformation2() {

        HotelInformation hotelInfo = new HotelInformation();

        hotelInfo.setNameOfHotelReservationService("NiceView");
        hotelInfo.setHotel(testHotel2());
        hotelInfo.setBookingNumber(567);
        hotelInfo.setPrice(10000);
        hotelInfo.setIsCreditCardGuaranteeRequired(false);

        return hotelInfo;
    }

    public static HotelInformation testHotelInformation3() {
        HotelInformation hotelInfo = new HotelInformation();
        
        hotelInfo.setNameOfHotelReservationService("NiceView");
        hotelInfo.setHotel(testHotel3());
        hotelInfo.setBookingNumber(876);
        hotelInfo.setPrice(3000);
        hotelInfo.setIsCreditCardGuaranteeRequired(false);
        
        return hotelInfo;
    }

    public static Hotel testHotel1() {
        Hotel hotel = new Hotel();
        //Hotel 1: HotelOfBangkok
        hotel.setCity("Bangkok");
        hotel.setHotelName("HotelOfBangkok");
        hotel.setCheckInDate("2016-08-20");
        hotel.setCheckOutDate("2016-08-27");
        hotel.setAddress("BangkokMainStreet 2");

        return hotel;
    }

    public static Hotel testHotel2() {
        Hotel hotel = new Hotel();
        //Hotel 2: BLUE
        hotel.setCity("Bangkok");
        hotel.setHotelName("BLUE");
        hotel.setCheckInDate("2016-08-20");
        hotel.setCheckOutDate("2016-08-27");
        hotel.setAddress("BangkokMainStreet 100");

        return hotel;
    }
    
    public static Hotel testHotel3() {
        Hotel hotel = new Hotel();
        //Hotel 3: RADISON
        hotel.setCity("Bangkok");
        hotel.setHotelName("RADISON");
        hotel.setCheckInDate("2016-08-21");
        hotel.setCheckOutDate("2016-08-28");
        hotel.setAddress("BangkokMainStreet 3");

        return hotel;
    }

}
