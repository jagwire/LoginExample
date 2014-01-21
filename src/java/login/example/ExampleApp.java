/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login.example;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import login.resources.LoginResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ResourceFinder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 *
 * @author Ryan
 */
@ApplicationPath("resources")
public class ExampleApp extends ResourceConfig {
    public ExampleApp() {
        super();
        packages("login.resources");
        register(RolesAllowedDynamicFeature.class);
    }

}
