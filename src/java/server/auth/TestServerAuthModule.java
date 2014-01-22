package server.auth;

import java.io.IOException;
import java.util.Map;
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
import login.example.UserRecord;
import login.example.Users;

/**
 *
 * @author Ryan
 */
public class TestServerAuthModule implements ServerAuthModule {
    public static final String HEADER_NAME = "EXAMPLE-AUTH";
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
        HttpServletRequest request = (HttpServletRequest)messageInfo.getRequestMessage();

        //check for presence of header
        String token = request.getHeader(HEADER_NAME);
        if (token != null) {

            //check validity of header
            if (Users.tokenIsValid(token)) {

                UserRecord user = Users.getUserFromToken(token);
                String[] groups = Users.getGroupsFromUser(user);

                setPrincipals(clientSubject, user.getUsername(), groups);
            }
        }
        return AuthStatus.SUCCESS;
    }

    
    private void setPrincipals(Subject clientSubject, String userid, String[] groups) {
                // Create a handler (kind of directive) to add the caller principal (AKA
        // user principal) "test" (=basically user name, or user id)
        // This will be the name of the principal returned by e.g.
        // HttpServletRequest#getUserPrincipal
        CallerPrincipalCallback callerPrincipalCallback =
                new CallerPrincipalCallback(clientSubject, userid);

        // Create a handler to add the group (AKA role) "architect"
        // This is what e.g. HttpServletRequest#isUserInRole and @RolesAllowed
        // test for
        GroupPrincipalCallback groupPrincipalCallback =
                new GroupPrincipalCallback(
                clientSubject, groups);

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
    }
    @Override
    public AuthStatus secureResponse(MessageInfo messageInfo, Subject serviceSubject) throws AuthException {
        return AuthStatus.SEND_SUCCESS;
    }

    @Override
    public void cleanSubject(MessageInfo messageInfo, Subject subject) throws AuthException {
    }
}
