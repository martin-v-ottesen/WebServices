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

    private static java.util.List<dk.dtu.ws.HotelBookingInformation> getHotels(java.lang.String arg0, javax.xml.datatype.XMLGregorianCalendar arg1, javax.xml.datatype.XMLGregorianCalendar arg2) {
        dk.dtu.ws.HotelReservationService service = new dk.dtu.ws.HotelReservationService();
        dk.dtu.ws.HotelReservation port = service.getHotelReservationPort();
        return port.getHotels(arg0, arg1, arg2);
    }

    private static String bookHotel(int arg0, dk.dtu.imm.fastmoney.types.CreditCardInfoType arg1) {
        dk.dtu.ws.HotelReservationService service = new dk.dtu.ws.HotelReservationService();
        dk.dtu.ws.HotelReservation port = service.getHotelReservationPort();
        return port.bookHotel(arg0, arg1);
    }

    private static String cancelHotel(int arg0, dk.dtu.imm.fastmoney.types.CreditCardInfoType arg1) {
        dk.dtu.ws.HotelReservationService service = new dk.dtu.ws.HotelReservationService();
        dk.dtu.ws.HotelReservation port = service.getHotelReservationPort();
        return port.cancelHotel(arg0, arg1);
    }

    
}
