package login.example.security;

/**
 *
 * @author Ryan
 */
public interface PasswordValidator {

    public boolean isValidUser(String password, String storedPassword);
}
