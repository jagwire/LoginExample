/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login.resources;

import javax.annotation.security.DenyAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import login.example.model.UserCredentials;

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

    @DenyAll
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Response handleLoginCredentials(UserCredentials uc) {

        
        
        //validate credentials
        
        //generate token
        
        
        UserCredentials silly = new UserCredentials();
        silly.username = "use";
        silly.password = "pass";
        return Response.ok(silly).build();
    }
}
