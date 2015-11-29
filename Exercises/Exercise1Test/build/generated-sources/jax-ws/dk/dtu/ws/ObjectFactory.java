
package dk.dtu.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dk.dtu.ws package. 
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

    private final static QName _DivResponse_QNAME = new QName("http://ws.dtu.dk/", "divResponse");
    private final static QName _Mult_QNAME = new QName("http://ws.dtu.dk/", "mult");
    private final static QName _Minus_QNAME = new QName("http://ws.dtu.dk/", "minus");
    private final static QName _Div_QNAME = new QName("http://ws.dtu.dk/", "div");
    private final static QName _Plus_QNAME = new QName("http://ws.dtu.dk/", "plus");
    private final static QName _MinusResponse_QNAME = new QName("http://ws.dtu.dk/", "minusResponse");
    private final static QName _MultResponse_QNAME = new QName("http://ws.dtu.dk/", "multResponse");
    private final static QName _PlusResponse_QNAME = new QName("http://ws.dtu.dk/", "plusResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dk.dtu.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Div }
     * 
     */
    public Div createDiv() {
        return new Div();
    }

    /**
     * Create an instance of {@link Minus }
     * 
     */
    public Minus createMinus() {
        return new Minus();
    }

    /**
     * Create an instance of {@link DivResponse }
     * 
     */
    public DivResponse createDivResponse() {
        return new DivResponse();
    }

    /**
     * Create an instance of {@link Mult }
     * 
     */
    public Mult createMult() {
        return new Mult();
    }

    /**
     * Create an instance of {@link PlusResponse }
     * 
     */
    public PlusResponse createPlusResponse() {
        return new PlusResponse();
    }

    /**
     * Create an instance of {@link MultResponse }
     * 
     */
    public MultResponse createMultResponse() {
        return new MultResponse();
    }

    /**
     * Create an instance of {@link MinusResponse }
     * 
     */
    public MinusResponse createMinusResponse() {
        return new MinusResponse();
    }

    /**
     * Create an instance of {@link Plus }
     * 
     */
    public Plus createPlus() {
        return new Plus();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DivResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "divResponse")
    public JAXBElement<DivResponse> createDivResponse(DivResponse value) {
        return new JAXBElement<DivResponse>(_DivResponse_QNAME, DivResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "mult")
    public JAXBElement<Mult> createMult(Mult value) {
        return new JAXBElement<Mult>(_Mult_QNAME, Mult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Minus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "minus")
    public JAXBElement<Minus> createMinus(Minus value) {
        return new JAXBElement<Minus>(_Minus_QNAME, Minus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Div }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "div")
    public JAXBElement<Div> createDiv(Div value) {
        return new JAXBElement<Div>(_Div_QNAME, Div.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Plus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "plus")
    public JAXBElement<Plus> createPlus(Plus value) {
        return new JAXBElement<Plus>(_Plus_QNAME, Plus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MinusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "minusResponse")
    public JAXBElement<MinusResponse> createMinusResponse(MinusResponse value) {
        return new JAXBElement<MinusResponse>(_MinusResponse_QNAME, MinusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "multResponse")
    public JAXBElement<MultResponse> createMultResponse(MultResponse value) {
        return new JAXBElement<MultResponse>(_MultResponse_QNAME, MultResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "plusResponse")
    public JAXBElement<PlusResponse> createPlusResponse(PlusResponse value) {
        return new JAXBElement<PlusResponse>(_PlusResponse_QNAME, PlusResponse.class, null, value);
    }

}
