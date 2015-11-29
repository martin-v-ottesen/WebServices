
package orderdata;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the orderdata package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Creditcard_QNAME = new QName("urn:orderData", "creditcard");
    private final static QName _Customer_QNAME = new QName("urn:orderData", "customer");
    private final static QName _Order_QNAME = new QName("urn:orderData", "order");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: orderdata
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrderType }
     * 
     */
    public OrderType createOrderType() {
        return new OrderType();
    }

    /**
     * Create an instance of {@link CreditcardType }
     * 
     */
    public CreditcardType createCreditcardType() {
        return new CreditcardType();
    }

    /**
     * Create an instance of {@link CustomerType }
     * 
     */
    public CustomerType createCustomerType() {
        return new CustomerType();
    }

    /**
     * Create an instance of {@link OrderType.Item }
     * 
     */
    public OrderType.Item createOrderTypeItem() {
        return new OrderType.Item();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditcardType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:orderData", name = "creditcard")
    public JAXBElement<CreditcardType> createCreditcard(CreditcardType value) {
        return new JAXBElement<CreditcardType>(_Creditcard_QNAME, CreditcardType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:orderData", name = "customer")
    public JAXBElement<CustomerType> createCustomer(CustomerType value) {
        return new JAXBElement<CustomerType>(_Customer_QNAME, CustomerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:orderData", name = "order")
    public JAXBElement<OrderType> createOrder(OrderType value) {
        return new JAXBElement<OrderType>(_Order_QNAME, OrderType.class, null, value);
    }

}
