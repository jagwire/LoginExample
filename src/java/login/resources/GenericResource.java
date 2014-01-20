/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login.resources;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * REST Web Service
 *
 * @author ryan
 */
@DenyAll
//@RolesAllowed({"super-secret-agents"})
@Path("generic")
public class GenericResource {

    @Context SecurityContext context;
    public GenericResource() {
        
    }
    

    /**
     * Retrieves representation of an instance of login.resources.GenericResource
     * @return an instance of java.lang.String
     */
    @DenyAll
    @GET
    @Produces("text/plain")
    public Response getJson() {
        
        
        return Response.ok("Hola Mundo? "+context.isUserInRole("Admin")).build();
    }
}
