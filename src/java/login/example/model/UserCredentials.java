/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login.example.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@XmlRootElement(name = "user")
public class UserCredentials {
    public String username;
    public String password;

}
