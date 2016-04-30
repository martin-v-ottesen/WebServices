/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.whitepages.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlElement;

/**
 * REST Web Service
 *
 * @author mkucharek
 */
@Path("/persons")
public class PersonsResource {

    @Context
    private UriInfo context;
    
    static List<Person> personList = new ArrayList<Person>();
    private static int i = 0;

    /** Creates a new instance of PersonsResource */
    public PersonsResource() {

    }

    @PUT
    public void setup() {
        Person p = new Person();

        p.setFirstName("ss");
        p.setLastName("ss");
        p.setPhoneNumber("213123");

        p.setId(String.valueOf(1));

        personList.add(p);
    }

    /**
     * Retrieves representation of an instance of ws.whitepages.rest.PersonsResource
     * @return an instance of java.util.Set
     */
    @GET
    @Produces("application/xml")
    public List<Person> getXml() {

        return personList;
    }

    /**
     * POST method for creating an instance of PersonsResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    public Response postXml(Person content) {

        content.setId(String.valueOf(++i));

        personList.add(content);

        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public PersonResource getPersonResource() {
        return new PersonResource();
    }
}
