/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package week05;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("hello")
public class HelloService {

    @GET
    public String helloWorld() {
        return "hello world\n";
    }

    @POST
    public Response hello(String input) {
        if ("world".equals(input)) {
           return Response.status(Status.INTERNAL_SERVER_ERROR).entity("not again :-(").build();
        }
        return Response.ok().entity("hello " + input+"\n").build();
    }
}
