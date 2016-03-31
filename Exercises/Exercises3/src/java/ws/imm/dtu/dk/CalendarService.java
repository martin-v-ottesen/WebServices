/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.imm.dtu.dk;

import com.sun.faces.util.CollectionsUtils;
import java.util.HashMap;
import javax.jws.WebService;

/**
 *
 * @author Martin
 */
@WebService(serviceName = "CalandarService", portName = "calendarPort", endpointInterface = "org.netbeans.j2ee.wsdl.exercises3.java.calandar.CalendarServicePortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/Exercises3/java/Calandar", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/Calandar.wsdl")
public class CalendarService {

    HashMap<javax.xml.datatype.XMLGregorianCalendar, String> appointmentMap = new HashMap<>();

    public void addAppointment(javax.xml.datatype.XMLGregorianCalendar date, java.lang.String appointment) {
        appointmentMap.put(date, appointment);
    }

    public java.lang.String getAppointment(javax.xml.datatype.XMLGregorianCalendar date) {
        return appointmentMap.getOrDefault(date, "No Appointment");
    }

    public void reset(java.lang.String part1) {
        if (part1.equals("Reset")) {
            appointmentMap.clear();
        } else {
            throw new UnsupportedOperationException("Not implemented yet.");
        }
    }

}
