
package orderprocess;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "orderProcessService", targetNamespace = "urn:orderProcess", wsdlLocation = "http://localhost:9080/orderProcessService/orderPTBindingPort?wsdl")
public class OrderProcessService
    extends Service
{

    private final static URL ORDERPROCESSSERVICE_WSDL_LOCATION;
    private final static WebServiceException ORDERPROCESSSERVICE_EXCEPTION;
    private final static QName ORDERPROCESSSERVICE_QNAME = new QName("urn:orderProcess", "orderProcessService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9080/orderProcessService/orderPTBindingPort?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ORDERPROCESSSERVICE_WSDL_LOCATION = url;
        ORDERPROCESSSERVICE_EXCEPTION = e;
    }

    public OrderProcessService() {
        super(__getWsdlLocation(), ORDERPROCESSSERVICE_QNAME);
    }

    public OrderProcessService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ORDERPROCESSSERVICE_QNAME, features);
    }

    public OrderProcessService(URL wsdlLocation) {
        super(wsdlLocation, ORDERPROCESSSERVICE_QNAME);
    }

    public OrderProcessService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ORDERPROCESSSERVICE_QNAME, features);
    }

    public OrderProcessService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OrderProcessService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns OrderPT
     */
    @WebEndpoint(name = "orderPTBindingPort")
    public OrderPT getOrderPTBindingPort() {
        return super.getPort(new QName("urn:orderProcess", "orderPTBindingPort"), OrderPT.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OrderPT
     */
    @WebEndpoint(name = "orderPTBindingPort")
    public OrderPT getOrderPTBindingPort(WebServiceFeature... features) {
        return super.getPort(new QName("urn:orderProcess", "orderPTBindingPort"), OrderPT.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ORDERPROCESSSERVICE_EXCEPTION!= null) {
            throw ORDERPROCESSSERVICE_EXCEPTION;
        }
        return ORDERPROCESSSERVICE_WSDL_LOCATION;
    }

}
