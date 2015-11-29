
package dk.dtu.ws;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HotelReservation", targetNamespace = "http://ws.dtu.dk/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HotelReservation {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "cancelHotel", targetNamespace = "http://ws.dtu.dk/", className = "dk.dtu.ws.CancelHotel")
    @ResponseWrapper(localName = "cancelHotelResponse", targetNamespace = "http://ws.dtu.dk/", className = "dk.dtu.ws.CancelHotelResponse")
    @Action(input = "http://ws.dtu.dk/HotelReservation/cancelHotelRequest", output = "http://ws.dtu.dk/HotelReservation/cancelHotelResponse")
    public String cancelHotel();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getHotels", targetNamespace = "http://ws.dtu.dk/", className = "dk.dtu.ws.GetHotels")
    @ResponseWrapper(localName = "getHotelsResponse", targetNamespace = "http://ws.dtu.dk/", className = "dk.dtu.ws.GetHotelsResponse")
    @Action(input = "http://ws.dtu.dk/HotelReservation/getHotelsRequest", output = "http://ws.dtu.dk/HotelReservation/getHotelsResponse")
    public String getHotels();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "bookHotel", targetNamespace = "http://ws.dtu.dk/", className = "dk.dtu.ws.BookHotel")
    @ResponseWrapper(localName = "bookHotelResponse", targetNamespace = "http://ws.dtu.dk/", className = "dk.dtu.ws.BookHotelResponse")
    @Action(input = "http://ws.dtu.dk/HotelReservation/bookHotelRequest", output = "http://ws.dtu.dk/HotelReservation/bookHotelResponse")
    public String bookHotel();

}