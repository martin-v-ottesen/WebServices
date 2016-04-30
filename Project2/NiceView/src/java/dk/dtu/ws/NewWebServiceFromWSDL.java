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
@WebService(serviceName = "niceViewWSDLService", portName = "NiceViewPortalBindingPort", endpointInterface = "org.netbeans.j2ee.wsdl.niceview.java.niceviewwsdl.NiceViewPortal", targetNamespace = "http://j2ee.netbeans.org/wsdl/NiceView/java/niceViewWSDL", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/niceViewWSDL.wsdl")
public class NewWebServiceFromWSDL {

    public org.netbeans.j2ee.wsdl.niceview.java.niceviewwsdl.HotelVacancies getHotels(org.netbeans.j2ee.wsdl.niceview.java.niceviewwsdl.HotelRequestType part1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
