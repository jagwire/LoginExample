
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.message.AuthException;
import javax.security.auth.message.MessageInfo;
import javax.security.auth.message.config.ServerAuthConfig;
import javax.security.auth.message.config.ServerAuthContext;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan
 */
class TestServerAuthConfig implements ServerAuthConfig {

    private final String layer;
    private final String appContext;
    private final CallbackHandler handler;
    private final Map<String, String> providerProperties;

    public TestServerAuthConfig(String layer, String appContext, CallbackHandler callbackHandler, Map<String, String> providerProperties) {
        this.layer = layer;
        this.appContext = appContext;
        this.handler = callbackHandler;
        this.providerProperties = providerProperties;
    }

    @Override
    public ServerAuthContext getAuthContext(String authContextID, Subject serviceSubject, Map properties) throws AuthException {
        return new TestServerAuthContext(handler);
    }

    @Override
    public String getMessageLayer() {
        return layer;
    }

    @Override
    public String getAppContext() {
        return appContext;
    }

    @Override
    public String getAuthContextID(MessageInfo messageInfo) {
        return appContext;
    }

    @Override
    public void refresh() {
    }

    @Override
    public boolean isProtected() {
        return false;
    }

    public Map<String, String> getProviderProperties() {
        return providerProperties;
    }
}
