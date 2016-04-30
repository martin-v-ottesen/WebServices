/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.whitepages.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;

/**
 * REST Web Service
 *
 * @author mkucharek
 */

public class PersonResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of PersonResource */
    public PersonResource() {
    }

    /**
     * Retrieves representation of an instance of ws.whitepages.rest.PersonResource
     * @param id resource URI parameter
     * @return an instance of ws.whitepages.rest.Person
     */
    @GET
    @Produces("application/xml")
    public Person getXml(@PathParam("id")
    String id) {

        for (Person p : PersonsResource.personList) {
            if (p.getId().equals(id)) {
                return p;
            }
        }

        return null;

    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     * @param id resource URI parameter
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(@PathParam("id")
    String id, Person content) {

        for (Person p : PersonsResource.personList) {
            if (p.getId().equals(id)) {
                p.setFirstName(content.getFirstName());
                p.setLastName(content.getLastName());
                p.setPhoneNumber(content.getPhoneNumber());

                p.setAddressList(content.getAddressList());
            }
        }
    }

    /**
     * DELETE method for resource PersonResource
     * @param id resource URI parameter
     */
    @DELETE
    public void delete(@PathParam("id")
    String id) {

        for (Person p : PersonsResource.personList) {
            if (p.getId().equals(id)) {
                PersonsResource.personList.remove(p);
                break;
            }
        }
    }
}
