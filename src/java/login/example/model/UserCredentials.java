package login.example.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * DataTransferObject to and from web client. Most likely marshalled to and from
 * JSON.
 *
 * @author Ryan
 */
@XmlRootElement(name = "user")
public class UserCredentials {
    public String username;
    public String password;

}
