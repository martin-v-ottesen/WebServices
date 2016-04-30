/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ws;

import dk.dtu.lameduck.lameduckwsdl.CancelFlightExceptionMessage;
import dk.dtu.lameduck.lameduckwsdl.LameDuckWSDLExceptionBookFlights;
import javax.jws.WebService;

/**
 *
 * @author dxong
 */
@WebService(serviceName = "lameDuckWSDLService", portName = "lameDuckWSDLPortTypeBindingPort", endpointInterface = "dk.dtu.lameduck.lameduckwsdl.LameDuckWSDLPortType", targetNamespace = "http://dk/dtu/LameDuck/lameDuckWSDL", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/lameDuckWSDL.wsdl")
public class NewWebServiceFromWSDL {

    public dk.dtu.lameduck.lameduckwsdl.FlightInformations getFlights(dk.dtu.lameduck.lameduckwsdl.RequestFlightType part1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public dk.dtu.lameduck.lameduckwsdl.BookingFlightStatusType bookFlights(dk.dtu.lameduck.lameduckwsdl.RequestBookFlightType requestBookFlights) throws LameDuckWSDLExceptionBookFlights {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public dk.dtu.lameduck.lameduckwsdl.BookingFlightStatusType cancelFlights(dk.dtu.lameduck.lameduckwsdl.RequestCancelFlightsType part1) throws CancelFlightExceptionMessage {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void reset(dk.dtu.lameduck.lameduckwsdl.BookingFlightStatusType part1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
