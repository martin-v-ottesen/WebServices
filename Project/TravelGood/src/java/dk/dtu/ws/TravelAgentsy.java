package dk.dtu.ws;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Martin
 */

@Path("TravelGood")
public class TravelAgentsy {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return ("DTU");
    }
    
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void put(String input) {
        
    }
    
    @Path("")
    @POST
    public void post(){
        
    }
    
    

    
    
    
}
