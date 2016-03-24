/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld.ws;

import javax.jws.WebService;

/**
 *
 * @author dxong
 */
@WebService(serviceName = "helloworldService", portName = "helloworldPort", endpointInterface = "ws.helloworld.HelloworldPortType", targetNamespace = "http://helloworld.ws", wsdlLocation = "WEB-INF/wsdl/HelloWorld/helloworld.wsdl")
public class HelloWorld {

    public java.lang.String helloworld(javax.xml.ws.Holder<java.lang.String> part1) {
        return "Hello " + part1;
    }
    
}
