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
    GregorianCalendar gregory = new GregorianCalendar();

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
        reset("Reset");
    }

    @Test
    public void addAppointment() throws ParseException, DatatypeConfigurationException {
        String dateStr = "2011.02.01";
        Date date = format.parse(dateStr);
        gregory.setTime(date);

        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
        addAppointment(calendar, "TestJunk");
    }
    
    @Test
    public void getNoAppointment() throws ParseException, DatatypeConfigurationException {
        String dateStr = "2011.02.01";
        Date date = format.parse(dateStr);

        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(date);

        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
        String result = getAppointment(calendar);
        assertEquals("No Appointment", result);
    }

    @Test
    public void firstAddThenGetAppointment() throws ParseException, DatatypeConfigurationException {
        String dateStr = "2011.02.01";
        Date date = format.parse(dateStr);
        gregory.setTime(date);

        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
        addAppointment(calendar, "TestJunk");

        String result = getAppointment(calendar);
        assertEquals("TestJunk", result);

    }

    private static void addAppointment(javax.xml.datatype.XMLGregorianCalendar part1, java.lang.String part2) {
        org.netbeans.j2ee.wsdl.exercises3.java.calandar.CalandarService service = new org.netbeans.j2ee.wsdl.exercises3.java.calandar.CalandarService();
        org.netbeans.j2ee.wsdl.exercises3.java.calandar.CalendarServicePortType port = service.getCalendarPort();
        port.addAppointment(part1, part2);
    }

    private static String getAppointment(javax.xml.datatype.XMLGregorianCalendar part1) {
        org.netbeans.j2ee.wsdl.exercises3.java.calandar.CalandarService service = new org.netbeans.j2ee.wsdl.exercises3.java.calandar.CalandarService();
        org.netbeans.j2ee.wsdl.exercises3.java.calandar.CalendarServicePortType port = service.getCalendarPort();
        return port.getAppointment(part1);
    }

    private static void reset(java.lang.String part1) {
        org.netbeans.j2ee.wsdl.exercises3.java.calandar.CalandarService service = new org.netbeans.j2ee.wsdl.exercises3.java.calandar.CalandarService();
        org.netbeans.j2ee.wsdl.exercises3.java.calandar.CalendarServicePortType port = service.getCalendarPort();
        port.reset(part1);
    }
}
