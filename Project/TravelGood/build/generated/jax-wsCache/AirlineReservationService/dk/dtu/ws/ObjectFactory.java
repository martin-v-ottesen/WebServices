
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

    private final static QName _SetFightData_QNAME = new QName("http://ws.dtu.dk/", "setFightData");
    private final static QName _SetFightDataResponse_QNAME = new QName("http://ws.dtu.dk/", "setFightDataResponse");
    private final static QName _BookFlight_QNAME = new QName("http://ws.dtu.dk/", "bookFlight");
    private final static QName _CancelFlight_QNAME = new QName("http://ws.dtu.dk/", "cancelFlight");
    private final static QName _CancelFlightResponse_QNAME = new QName("http://ws.dtu.dk/", "cancelFlightResponse");
    private final static QName _CreateAirlineResponse_QNAME = new QName("http://ws.dtu.dk/", "createAirlineResponse");
    private final static QName _GetFlights_QNAME = new QName("http://ws.dtu.dk/", "getFlights");
    private final static QName _BookFlightResponse_QNAME = new QName("http://ws.dtu.dk/", "bookFlightResponse");
    private final static QName _CreateAirline_QNAME = new QName("http://ws.dtu.dk/", "createAirline");
    private final static QName _GetFlightsResponse_QNAME = new QName("http://ws.dtu.dk/", "getFlightsResponse");
    private final static QName _FileNotFoundException_QNAME = new QName("http://ws.dtu.dk/", "FileNotFoundException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dk.dtu.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookFlightResponse }
     * 
     */
    public BookFlightResponse createBookFlightResponse() {
        return new BookFlightResponse();
    }

    /**
     * Create an instance of {@link CreateAirlineResponse }
     * 
     */
    public CreateAirlineResponse createCreateAirlineResponse() {
        return new CreateAirlineResponse();
    }

    /**
     * Create an instance of {@link GetFlights }
     * 
     */
    public GetFlights createGetFlights() {
        return new GetFlights();
    }

    /**
     * Create an instance of {@link CancelFlightResponse }
     * 
     */
    public CancelFlightResponse createCancelFlightResponse() {
        return new CancelFlightResponse();
    }

    /**
     * Create an instance of {@link BookFlight }
     * 
     */
    public BookFlight createBookFlight() {
        return new BookFlight();
    }

    /**
     * Create an instance of {@link CancelFlight }
     * 
     */
    public CancelFlight createCancelFlight() {
        return new CancelFlight();
    }

    /**
     * Create an instance of {@link SetFightData }
     * 
     */
    public SetFightData createSetFightData() {
        return new SetFightData();
    }

    /**
     * Create an instance of {@link SetFightDataResponse }
     * 
     */
    public SetFightDataResponse createSetFightDataResponse() {
        return new SetFightDataResponse();
    }

    /**
     * Create an instance of {@link FileNotFoundException }
     * 
     */
    public FileNotFoundException createFileNotFoundException() {
        return new FileNotFoundException();
    }

    /**
     * Create an instance of {@link CreateAirline }
     * 
     */
    public CreateAirline createCreateAirline() {
        return new CreateAirline();
    }

    /**
     * Create an instance of {@link GetFlightsResponse }
     * 
     */
    public GetFlightsResponse createGetFlightsResponse() {
        return new GetFlightsResponse();
    }

    /**
     * Create an instance of {@link DateTime }
     * 
     */
    public DateTime createDateTime() {
        return new DateTime();
    }

    /**
     * Create an instance of {@link FlighListObject }
     * 
     */
    public FlighListObject createFlighListObject() {
        return new FlighListObject();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetFightData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "setFightData")
    public JAXBElement<SetFightData> createSetFightData(SetFightData value) {
        return new JAXBElement<SetFightData>(_SetFightData_QNAME, SetFightData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetFightDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "setFightDataResponse")
    public JAXBElement<SetFightDataResponse> createSetFightDataResponse(SetFightDataResponse value) {
        return new JAXBElement<SetFightDataResponse>(_SetFightDataResponse_QNAME, SetFightDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookFlight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "bookFlight")
    public JAXBElement<BookFlight> createBookFlight(BookFlight value) {
        return new JAXBElement<BookFlight>(_BookFlight_QNAME, BookFlight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelFlight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "cancelFlight")
    public JAXBElement<CancelFlight> createCancelFlight(CancelFlight value) {
        return new JAXBElement<CancelFlight>(_CancelFlight_QNAME, CancelFlight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelFlightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "cancelFlightResponse")
    public JAXBElement<CancelFlightResponse> createCancelFlightResponse(CancelFlightResponse value) {
        return new JAXBElement<CancelFlightResponse>(_CancelFlightResponse_QNAME, CancelFlightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAirlineResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "createAirlineResponse")
    public JAXBElement<CreateAirlineResponse> createCreateAirlineResponse(CreateAirlineResponse value) {
        return new JAXBElement<CreateAirlineResponse>(_CreateAirlineResponse_QNAME, CreateAirlineResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlights }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "getFlights")
    public JAXBElement<GetFlights> createGetFlights(GetFlights value) {
        return new JAXBElement<GetFlights>(_GetFlights_QNAME, GetFlights.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookFlightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "bookFlightResponse")
    public JAXBElement<BookFlightResponse> createBookFlightResponse(BookFlightResponse value) {
        return new JAXBElement<BookFlightResponse>(_BookFlightResponse_QNAME, BookFlightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAirline }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "createAirline")
    public JAXBElement<CreateAirline> createCreateAirline(CreateAirline value) {
        return new JAXBElement<CreateAirline>(_CreateAirline_QNAME, CreateAirline.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFlightsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "getFlightsResponse")
    public JAXBElement<GetFlightsResponse> createGetFlightsResponse(GetFlightsResponse value) {
        return new JAXBElement<GetFlightsResponse>(_GetFlightsResponse_QNAME, GetFlightsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dtu.dk/", name = "FileNotFoundException")
    public JAXBElement<FileNotFoundException> createFileNotFoundException(FileNotFoundException value) {
        return new JAXBElement<FileNotFoundException>(_FileNotFoundException_QNAME, FileNotFoundException.class, null, value);
    }

}
