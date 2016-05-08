/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

import javax.jws.WebService;
import org.netbeans.j2ee.wsdl.lameduck2.java.lameduck.BookFlightFault;
import org.netbeans.j2ee.wsdl.lameduck2.java.lameduck.CancelFlightError;

/**
 *
 * @author Martin
 */
@WebService(serviceName = "LameDuckService", portName = "LameDuckPortTypeBindingPort", endpointInterface = "org.netbeans.j2ee.wsdl.lameduck2.java.lameduck.LameDuckPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/LameDuck2/java/LameDuck", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/LameDuck.wsdl")
public class NewWebServiceFromWSDL {

    public void reset(boolean doReset) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean cancelFlight(org.netbeans.j2ee.wsdl.lameduck2.java.lameduck.CancelFlightType cancelFlights) throws CancelFlightError {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean bookFlight(org.netbeans.j2ee.wsdl.lameduck2.java.lameduck.BookFlightRequestType booking) throws BookFlightFault {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public org.netbeans.j2ee.wsdl.lameduck2.java.lameduck.FlightInformations getFlights(org.netbeans.j2ee.wsdl.lameduck2.java.lameduck.GetFlightRequestType route) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
