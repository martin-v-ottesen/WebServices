/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.imm.dtu.dk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
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
public class CalendarTest {

    static final DateFormat format = new SimpleDateFormat("yyyy.MM.dd");

    public CalendarTest() {
    }

    @BeforeClass
    public static void setUpClass() throws ParseException {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAppointment() throws ParseException, DatatypeConfigurationException {
        String dateStr = "2011.02.01";
        Date date = format.parse(dateStr);

        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(date);

        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
        try {
            String result = getAppointment(calendar);
        } catch (Exception e) {
            assertEquals("Client received SOAP Fault from server: Not implemented yet. Please see the server log to find more detail regarding exact cause of the failure.", e.getMessage());
        }
    }

    @Test
    public void addAppointment() throws ParseException, DatatypeConfigurationException {
        String dateStr = "2011.02.01";
        Date date = format.parse(dateStr);

        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(date);

        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
        try {
            addAppointment(calendar, "TestJunk");
        } catch (Exception e) {
            assertEquals("Client received SOAP Fault from server: Not implemented yet. Please see the server log to find more detail regarding exact cause of the failure.", e.getMessage());
        }
    }

    private static void addAppointment(javax.xml.datatype.XMLGregorianCalendar part1, java.lang.String part2) {
        org.netbeans.j2ee.wsdl.exercises3.java.calendar.CalendarService service = new org.netbeans.j2ee.wsdl.exercises3.java.calendar.CalendarService();
        org.netbeans.j2ee.wsdl.exercises3.java.calendar.CalendarServicePortType port = service.getCalendarPort();
        port.addAppointment(part1, part2);
    }

    private static String getAppointment(javax.xml.datatype.XMLGregorianCalendar part1) {
        org.netbeans.j2ee.wsdl.exercises3.java.calendar.CalendarService service = new org.netbeans.j2ee.wsdl.exercises3.java.calendar.CalendarService();
        org.netbeans.j2ee.wsdl.exercises3.java.calendar.CalendarServicePortType port = service.getCalendarPort();
        return port.getAppointment(part1);
    }

}
