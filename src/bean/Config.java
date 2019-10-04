/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;

/**
 *
 * @author eyraf
 */
public class Config {
    public static User loggedInUser;
    public static List<User> users;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setLoggedInUser(User loggedInUser) {
        Config.loggedInUser = loggedInUser;
    }

    public static void setUsers(List<User> users) {
        Config.users = users;
    }
    
    
}
