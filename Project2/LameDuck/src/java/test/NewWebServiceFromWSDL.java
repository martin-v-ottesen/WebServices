/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.jws.WebService;

/**
 *
 * @author Martin
 */
@WebService(serviceName = "LameDuckService", portName = "LameDuckPort", endpointInterface = "org.netbeans.j2ee.wsdl.lameduck.java.lameduck.LameDuckPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/LameDuck/java/LAmeDuck", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/LameDuckWrapper.wsdl")
public class NewWebServiceFromWSDL {

    public void lameDuckOperation(boolean part1) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
