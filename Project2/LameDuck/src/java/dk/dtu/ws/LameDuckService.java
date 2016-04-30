/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

import javax.jws.WebService;

/**
 *
 * @author Martin
 */
@WebService(serviceName = "lameDuckWSDLService", portName = "lameDuckWSDLPortTypeBindingPort", endpointInterface = "org.netbeans.j2ee.wsdl.lameduck.lameduck.lameduckwsdl.LameDuckWSDLPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/LameDuck/LameDuck/lameDuckWSDL", wsdlLocation = "WEB-INF/wsdl/LameDuckService/lameDuckWSDL.wsdl")
public class LameDuckService {

    public org.netbeans.j2ee.wsdl.lameduck.lameduck.lameduckwsdl.FlightInformations getFlights(org.netbeans.j2ee.wsdl.lameduck.lameduck.lameduckwsdl.RequestFlightType part1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
