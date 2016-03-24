/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.test.dtu.dk.ws;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * @author dxong
 */
public class CalendarServiceJUnitTest {
    
    public CalendarServiceJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void addAppointment_InputDateAndAppointment_NoException() throws ParseException{
        //Arrange
        String expectedAppointment = "Tis og Lort";
        final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse("24/03/2016"));
        Date expectedDate = calendar.getTime();

        XMLGregorianCalendar expectedXMLDate = toXMLGregorianCalendar(expectedDate);
        Exception expectedException = null; 
        try {
            //Act
            addAppointment(expectedAppointment, expectedXMLDate);
        } catch (Exception e) {
            //Assert
            System.out.println("Adding Appointment failed");
            expectedException = e;
        }
        assertEquals(null, expectedException);
        //Outputting date
        System.out.println("addAppointment_InputDateAndAppointment_NoException");
        System.out.println("expectedDate: " + expectedDate.toString());
        System.out.println("expectedXMLDate" + expectedXMLDate.toString());
    }
    
    @Test
    public void getAppointment_InputDate_ReturnExpectedAppointment() throws ParseException{
        //Arrange
        String expectedAppointment = "Tis og Lort";
        final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse("24/03/2016"));
        Date insertDate = calendar.getTime();
        XMLGregorianCalendar insertXMLDate = toXMLGregorianCalendar(insertDate);
        //Act
        String resultAppointment = getAppointment(insertXMLDate);
        //Assert
        assertEquals(expectedAppointment, resultAppointment);
        //Outputting date
        System.out.println("getAppointment_InputDate_ReturnExpectedAppointment");
        System.out.println("insertDate: " + insertDate.toString());
        System.out.println("insertXMLDate:  " + insertXMLDate.toString());
        System.out.println("expectedAppointment:    " + expectedAppointment);
        System.out.println("resultAppointment:  " + resultAppointment);
    }

    @Test
    public void addAppointment_InputTwoDifferentAppointmentsWithDifferentDates_WhatHappens() throws ParseException{
        //Arrange
        String insertAppointmentFIRST = "Tis og Lort";
        String insertAppointmentSECOND = "HOLD NU";
        final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse("24/03/2016"));
        Date expectedDate = calendar.getTime();

        XMLGregorianCalendar expectedXMLDate = toXMLGregorianCalendar(expectedDate);
        Exception expectedException = null; 
        try {
            //Act
            addAppointment(insertAppointmentFIRST, expectedXMLDate);
            addAppointment(insertAppointmentSECOND, expectedXMLDate);
        } catch (Exception e) {
            //Assert
            System.out.println("Adding Appointment failed");
            expectedException = e;
        }
        //Assert
        /*Enten returneres der null hvis der ikke forefindes en Value for den angivne
          Key. I dette tilfælde overskrives den første, derfor forventes
          insertAppointmentSECOND = "HOLD NU";
        */
        assertNotSame(insertAppointmentFIRST, getAppointment(expectedXMLDate));
        //Nedenstående Assert vil fejle som forventet.
        //assertEquals(insertAppointmentFIRST, getAppointment(expectedXMLDate));
        
    }
    
    private static void addAppointment(java.lang.String appointment, javax.xml.datatype.XMLGregorianCalendar date) {
        org.netbeans.j2ee.wsdl.calendarwebservice.java.calendarservicewsdl.CalendarServiceService service = new org.netbeans.j2ee.wsdl.calendarwebservice.java.calendarservicewsdl.CalendarServiceService();
        org.netbeans.j2ee.wsdl.calendarwebservice.java.calendarservicewsdl.CalendarServicePortType port = service.getCalendarServicePort();
        port.addAppointment(appointment, date);
    }

    private static String getAppointment(javax.xml.datatype.XMLGregorianCalendar date) {
        org.netbeans.j2ee.wsdl.calendarwebservice.java.calendarservicewsdl.CalendarServiceService service = new org.netbeans.j2ee.wsdl.calendarwebservice.java.calendarservicewsdl.CalendarServiceService();
        org.netbeans.j2ee.wsdl.calendarwebservice.java.calendarservicewsdl.CalendarServicePortType port = service.getCalendarServicePort();
        return port.getAppointment(date);
    }
    
    /*
     * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date){
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            System.out.println("Converting date to XMLGregorianCalendar Failed: " + ex.getMessage());
        }
        return xmlCalendar;
    }
  
    /*
     * Converts XMLGregorianCalendar to java.util.Date in Java
     */
    public static Date toDate(XMLGregorianCalendar calendar){
        if(calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }
}
