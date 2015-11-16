/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class HotelReservationServiceTest {

    private static String getHotels() {
        dk.dtu.ws.HotelReservationService service = new dk.dtu.ws.HotelReservationService();
        dk.dtu.ws.HotelReservation port = service.getHotelReservationPort();
        return port.getHotels();
    }

    private static String bookHotel() {
        dk.dtu.ws.HotelReservationService service = new dk.dtu.ws.HotelReservationService();
        dk.dtu.ws.HotelReservation port = service.getHotelReservationPort();
        return port.bookHotel();
    }

    private static String cancelHotel() {
        dk.dtu.ws.HotelReservationService service = new dk.dtu.ws.HotelReservationService();
        dk.dtu.ws.HotelReservation port = service.getHotelReservationPort();
        return port.cancelHotel();
    }  
}
