
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.message.AuthException;
import javax.security.auth.message.AuthStatus;
import javax.security.auth.message.MessageInfo;
import javax.security.auth.message.MessagePolicy;
import javax.security.auth.message.callback.CallerPrincipalCallback;
import javax.security.auth.message.callback.GroupPrincipalCallback;
import javax.security.auth.message.module.ServerAuthModule;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ryan
 */
class TestServerAuthModule implements ServerAuthModule {
    private CallbackHandler handler;

    public TestServerAuthModule() {
    }

    @Override
    public void initialize(MessagePolicy requestPolicy, MessagePolicy responsePolicy, CallbackHandler handler, Map options) throws AuthException {
        this.handler = handler;
    }

    @Override
    public Class[] getSupportedMessageTypes() {
        return new Class[]{HttpServletRequest.class, HttpServletResponse.class};
    }

    @Override
    public AuthStatus validateRequest(MessageInfo messageInfo, Subject clientSubject, Subject serviceSubject) throws AuthException {
        System.out.println("INSIDE VALIDATE REQUEST!!! POOP-POOP");
        // Normally we would check here for authentication credentials being
        // present and perform actual authentication, or in absence of those
        // ask the user in some way to authenticate.

        // Here we just create the user and associated roles directly.

        // Create a handler (kind of directive) to add the caller principal (AKA
        // user principal) "test" (=basically user name, or user id)
        // This will be the name of the principal returned by e.g.
        // HttpServletRequest#getUserPrincipal
        CallerPrincipalCallback callerPrincipalCallback =
                new CallerPrincipalCallback(clientSubject, "test");

        // Create a handler to add the group (AKA role) "architect"
        // This is what e.g. HttpServletRequest#isUserInRole and @RolesAllowed
        // test for
        GroupPrincipalCallback groupPrincipalCallback =
                new GroupPrincipalCallback(
                clientSubject, new String[]{"architect"});

        // Execute the handlers we created above. This will typically add the
        // "test" principal and the "architect"
        // role in an application server specific way to the JAAS Subject.
        try {
            handler.handle(new Callback[]{callerPrincipalCallback,
                groupPrincipalCallback});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        return AuthStatus.SUCCESS;
    }

    @Override
    public AuthStatus secureResponse(MessageInfo messageInfo, Subject serviceSubject) throws AuthException {
        return AuthStatus.SEND_SUCCESS;
    }

    @Override
    public void cleanSubject(MessageInfo messageInfo, Subject subject) throws AuthException {
    }
}
