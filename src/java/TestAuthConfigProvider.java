
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.message.AuthException;
import javax.security.auth.message.config.AuthConfigFactory;
import javax.security.auth.message.config.AuthConfigProvider;
import javax.security.auth.message.config.ClientAuthConfig;
import javax.security.auth.message.config.ServerAuthConfig;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan
 */
class TestAuthConfigProvider implements AuthConfigProvider {

    private static final String CALLBACK_HANDLER_PROPERTY_NAME = "authconfigprovider.client.callbackhandler";
    private Map<String, String> providerProperties;

    public TestAuthConfigProvider() {
    }

    public TestAuthConfigProvider(Map<String, String> properties, AuthConfigFactory factory) {
        this.providerProperties = properties;

        if (factory != null) {
            factory.registerConfigProvider(this, null, null, "Auto registration");
        }
    }

    @Override
    public ClientAuthConfig getClientAuthConfig(String layer, String appContext, CallbackHandler handler) throws AuthException {
        return null;
    }

    @Override
    public ServerAuthConfig getServerAuthConfig(String layer, String appContext, CallbackHandler handler) throws AuthException {
        return new TestServerAuthConfig(layer, appContext, handler == null ? createDefaultCallbackHandler() : handler, providerProperties);
    }

    @Override
    public void refresh() {
    }

    private CallbackHandler createDefaultCallbackHandler() throws AuthException {
        String callbackClassName = System.getProperty(CALLBACK_HANDLER_PROPERTY_NAME);
        if (callbackClassName == null) {
            throw new AuthException("NO DEFAULT HANDLER SET VIA SYSTEM PROPERTY: " + CALLBACK_HANDLER_PROPERTY_NAME);
        }

        try {
            return (CallbackHandler) Thread.currentThread().getContextClassLoader().loadClass(callbackClassName).newInstance();
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }
}
