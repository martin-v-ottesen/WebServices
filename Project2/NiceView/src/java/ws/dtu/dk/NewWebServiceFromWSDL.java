/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.dk;

import javax.jws.WebService;

/**
 *
 * @author Martin
 */
@WebService(serviceName = "niceViewWSDLService", portName = "NiceViewPortalBindingPort", endpointInterface = "dk.dtu.ws.niceview.NiceViewPortal", targetNamespace = "http://ws.dtu.dk/NiceView", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/niceViewWSDL.wsdl")
public class NewWebServiceFromWSDL {

    public dk.dtu.ws.niceview.HotelVacancies getHotels(dk.dtu.ws.niceview.HotelRequestType part1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
