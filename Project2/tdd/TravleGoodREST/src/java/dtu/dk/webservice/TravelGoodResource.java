/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.dk.webservice;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Martin
 */
@Path("tg")
public class TravelGoodResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public TravelGoodResource() {
    }

    /**
     * Retrieves representation of an instance of dtu.dk.webservice.TravelGoodResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "DTU";
    }
}
