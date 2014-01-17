/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.security.auth.message.config.AuthConfigFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Ryan
 */
@WebListener()
public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AuthConfigFactory.getFactory().registerConfigProvider(new TestAuthConfigProvider(), "HttpServlet", null, "The test");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
