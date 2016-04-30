/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helloworld.ws;

import javax.jws.WebService;

/**
 *
 * @author Wojtek
 */
@WebService(serviceName = "helloworldService", portName = "helloworldPort", endpointInterface = "ws.helloworld.HelloworldPortType", targetNamespace = "http://helloworld.ws", wsdlLocation = "WEB-INF/wsdl/HelloWorld/helloworldWrapper.wsdl")
public class HelloWorld {

    public java.lang.String helloworld(java.lang.String part1) {
        return "Hello " + part1;
    }

}
