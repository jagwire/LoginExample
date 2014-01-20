/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login.example.security;

/**
 *
 * @author Ryan
 */
public interface PasswordValidator {

    public boolean isValidUser(String password, String storedPassword);
}
