/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.cook;

import javax.xml.ws.WebFault;

/**
 *
 * @author Wojtek
 */
@WebFault(name="eggSmellsBadFault", targetNamespace="http://cook.ws/")
public class EggSmellsBadFault extends Exception {

    /**
     * Java type that goes as soapenv:Fault detail element.
     *
     */
    private String faultInfo;

    public EggSmellsBadFault(String message, String faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public EggSmellsBadFault(String message, String faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public String getFaultInfo() {
        return faultInfo;
    }
}
