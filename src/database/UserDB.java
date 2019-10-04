/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import bean.Config;
import bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eyraf
 */
public class UserDB {

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();

        try (Connection conn = ConnectionDB.connect()) {

            PreparedStatement stmt = conn.prepareStatement("select * from users");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                allUsers.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("password"), rs.getInt("status"), rs.getInt("position")));
            }

            return allUsers;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public boolean register(User u) {
        try (Connection conn = ConnectionDB.connect()) {

            PreparedStatement stmt = conn.prepareStatement("insert users(name,surname,username,password,position,status) values(?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getUsername());
            stmt.setString(4, u.getPassword());
            stmt.setInt(5, 0);
            stmt.setInt(6, 1);

            stmt.executeUpdate();

            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public User login(User u) {
        try (Connection conn = ConnectionDB.connect()) {

            PreparedStatement stmt = conn.prepareStatement("select * from users where username = ? and password = ?");
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());

            ResultSet rs = stmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    User loggedInUser = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("password"), rs.getInt("status"), rs.getInt("position"));
                   
                    Config.setLoggedInUser(loggedInUser);
                    return loggedInUser;
                }
            }
            return null;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    
    public boolean makeActive(int id){
        try(Connection conn = ConnectionDB.connect()){
            
            PreparedStatement stmt = conn.prepareStatement("update users set status = ? where id = ?");
            stmt.setInt(1, 1);
            stmt.setInt(2, id);
            
            stmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.getMessage();
            return false;
        }
    }
    
    public boolean deactivate(int id){
        try(Connection conn = ConnectionDB.connect()){
            
            PreparedStatement stmt = conn.prepareStatement("update users set status = ? where id = ?");
            stmt.setInt(1, 0);
            stmt.setInt(2, id);
            
            stmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.getMessage();
            return false;
        }
    }
    
    public boolean deleteUser(int id){
        try(Connection conn = ConnectionDB.connect()){
            
            PreparedStatement stmt = conn.prepareStatement("delete from users where id = ?");
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.getMessage();
            return false;
        }
    }
}
