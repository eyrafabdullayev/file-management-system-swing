/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author eyraf
 */
public class ConnectionDB {
    public static Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/filemansys";
            String username = "root";
            String password = "12345";
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
