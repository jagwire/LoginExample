/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login.resources;


import server.auth.TestServerAuthModule;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import login.example.Users;
import login.example.model.UserCredentials;

/**
 *
 * @author ryan
 */
@DeclareRoles({"User"})
@ServletSecurity(
        @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL))
@Path("login")
public class LoginResource {
    
    @Context
    private HttpServletRequest servletRequest;
    @GET
    @Produces("text/plain")
    public Response ohlala() {
        System.out.println("HANDLING LOGIN FROM REST SERVICE!");
        return Response.ok("ohlala").build();
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    @Path("/go")
    public Response register(UserCredentials uc) {

        Users.registerUser(uc.username, uc.password);

        return Response.ok(uc).build();
    }

    // @RolesAllowed("User")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public Response handleLoginCredentials(UserCredentials uc) {
        System.out.println("RECEIVED REQUEST TO LOG IN->USER: " + uc.username + " PASS: " + uc.password);
        String token = Users.logUserIn(uc.username, uc.password);
        
        
        //validate credentials
        
        //generate token
        
        return Response.ok(token).build();
    }

    @RolesAllowed("User")
    @POST
    @Path("/me")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sillyHandler(@Context SecurityContext sc) {

        System.out.println("REQUEST SENT BY USER: " + sc.isUserInRole("User"));
        System.out.println("REQUEST FROM: " + servletRequest.getUserPrincipal().getName());

        return Response.ok("YOU SO SILLY!").build();

    }

    @RolesAllowed("User")
    @POST
    @Path("/logout")
    public Response logout() {
        Users.logUserOut(servletRequest.getHeader(TestServerAuthModule.HEADER_NAME));

        return Response.ok("logged out").build();
    }
}
