/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarservice.ws;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author dxong
 */
@WebService(serviceName = "calendarServiceService", portName = "calendarServicePort", endpointInterface = "org.netbeans.j2ee.wsdl.calendarwebservice.java.calendarservicewsdl.CalendarServicePortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/CalendarWebservice/java/calendarServiceWSDL", wsdlLocation = "WEB-INF/wsdl/calendarServiceFromWSDL/calendarServiceWSDLWrapper.wsdl")
public class calendarServiceFromWSDL {
    HashMap<javax.xml.datatype.XMLGregorianCalendar, String> appointmentMap = new HashMap<>();
    
    public void addAppointment(java.lang.String appointment, javax.xml.datatype.XMLGregorianCalendar date) {
        appointmentMap.put(date, appointment);
    }

    public java.lang.String getAppointment(javax.xml.datatype.XMLGregorianCalendar date) {
        return appointmentMap.getOrDefault(date, "LORT");
    }
    
}
