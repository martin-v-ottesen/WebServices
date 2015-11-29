
package dk.dtu.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "FileNotFoundException", targetNamespace = "http://ws.dtu.dk/")
public class FileNotFoundException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private FileNotFoundException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public FileNotFoundException_Exception(String message, FileNotFoundException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public FileNotFoundException_Exception(String message, FileNotFoundException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: dk.dtu.ws.FileNotFoundException
     */
    public FileNotFoundException getFaultInfo() {
        return faultInfo;
    }

}
