
package orderprocess;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import orderdata.CreditcardType;
import orderdata.CustomerType;
import orderdata.OrderType;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "orderPT", targetNamespace = "urn:orderProcess")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    orderdata.ObjectFactory.class,
    orderprocess.ObjectFactory.class
})
public interface OrderPT {


    /**
     * 
     * @param customer
     * @param order
     * @return
     *     returns orderprocess.ResultType
     * @throws ReceiveOrderFault
     */
    @WebMethod
    @WebResult(name = "result", partName = "result")
    public ResultType receiveOrder(
        @WebParam(name = "customer", partName = "customer")
        CustomerType customer,
        @WebParam(name = "order", partName = "order")
        OrderType order)
        throws ReceiveOrderFault
    ;

    /**
     * 
     * @param orderID
     * @param customerID
     * @param creditcard
     * @return
     *     returns orderprocess.ResultType
     */
    @WebMethod
    @WebResult(name = "result", partName = "result")
    public ResultType pay(
        @WebParam(name = "customerID", partName = "customerID")
        String customerID,
        @WebParam(name = "orderID", partName = "orderID")
        String orderID,
        @WebParam(name = "creditcard", partName = "creditcard")
        CreditcardType creditcard);

    /**
     * 
     * @param orderId
     * @param customerId
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "result", partName = "result")
    public String cancelOrder(
        @WebParam(name = "customerId", partName = "customerId")
        String customerId,
        @WebParam(name = "orderId", partName = "orderId")
        String orderId);

}
