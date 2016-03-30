/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.imm.dtu.dk;

import javax.jws.WebService;

/**
 *
 * @author Martin
 */
@WebService(serviceName = "calendarService", portName = "calendarPort", endpointInterface = "org.netbeans.j2ee.wsdl.exercises3.java.calendar.CalendarServicePortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/Exercises3/java/calendar", wsdlLocation = "WEB-INF/wsdl/CalendarService/calendar.wsdl")
public class CalendarService {

    public void addAppointment(javax.xml.datatype.XMLGregorianCalendar part1, java.lang.String part2) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public java.lang.String getAppointment(javax.xml.datatype.XMLGregorianCalendar part1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
