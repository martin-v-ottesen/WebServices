/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.ws;

import java.util.HashMap;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Wojtek
 */
@WebService(serviceName = "calendarService", portName = "calendarServicePort", endpointInterface = "dk.dtu.imm.ws.calendar.CalendarServicePortType", targetNamespace = "http://ws.imm.dtu.dk/calendar", wsdlLocation = "WEB-INF/wsdl/Calendar/calendar.wsdl")
public class Calendar {

    HashMap appointments;

    public Calendar() {
        this.appointments = new HashMap();
    }

    public void addApointment(XMLGregorianCalendar part1, java.lang.String part2) {
        appointments.put(part1, part2);
    }

    public java.lang.String getAppointment(XMLGregorianCalendar part1) {
        return appointments.get(part1).toString();
    }
}
