/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author ryan
 */
@Path("login")
public class LoginResource {
    
    
    @GET
    @Produces("text/plain")
    public Response ohlala() {
        System.out.println("HANDLING LOGIN FROM REST SERVICE!");
        return Response.ok("ohlala").build();
    }
    
    @POST
    @Consumes("text/plain")
    @Produces("text/plain")
    public Response handleLoginCredentials(String credentials, @Context HttpServletRequest hsr) {
        String[] tokens = credentials.split(":");
        String username = tokens[0];
        String password = tokens[1];
        
        
        //validate credentials
        
        //generate token
        
        
        
        return Response.ok("ohlala").build();
    }
}
