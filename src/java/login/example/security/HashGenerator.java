package login.example.security;

/**
 *
 * @author Ryan
 */
public interface HashGenerator {

    public String generateHash(String password);

    public PasswordValidator validator();
}
