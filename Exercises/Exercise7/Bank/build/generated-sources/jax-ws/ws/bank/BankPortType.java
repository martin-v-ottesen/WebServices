
package ws.bank;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import orderdata.CreditcardType;
import orderdata.CustomerType;
import orderdata.ObjectFactory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebService(name = "bankPortType", targetNamespace = "http://bank.ws")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BankPortType {


    /**
     * 
     * @param amount
     * @param creditcard
     * @param customer
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "result", partName = "result")
    public String debitCreditCard(
        @WebParam(name = "customer", partName = "customer")
        CustomerType customer,
        @WebParam(name = "creditcard", partName = "creditcard")
        CreditcardType creditcard,
        @WebParam(name = "amount", partName = "amount")
        double amount);

    /**
     * 
     * @param amount
     * @param creditcard
     * @param customer
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "result", partName = "result")
    public String refundCreditCard(
        @WebParam(name = "customer", partName = "customer")
        CustomerType customer,
        @WebParam(name = "creditcard", partName = "creditcard")
        String creditcard,
        @WebParam(name = "amount", partName = "amount")
        double amount);

}
