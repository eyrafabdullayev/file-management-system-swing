/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eyraf
 */
public class FileDB {
    
    
    public List<String> getNonAccessibleFolders(int userId){
        List<String> nonList = new ArrayList<>();
        
        try (Connection conn = ConnectionDB.connect()){
            
            PreparedStatement stmt = conn.prepareStatement("select * from nonaccess where user_id = ?");
            stmt.setInt(1, userId);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                nonList.add(rs.getString("nonAccessibleFileOrFolderName"));
            }
            
            return nonList;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    
    public boolean addNonAccessibleFolders(String folderName,int userId){
        try(Connection conn = ConnectionDB.connect()){
            
            PreparedStatement stmt = conn.prepareStatement("insert nonaccess(user_id,nonAccessibleFileOrFolderName) values(?,?)");
            stmt.setInt(1, userId);
            stmt.setString(2, folderName);
            
            stmt.executeUpdate();
            
            return true;
        }catch(Exception e){
            e.getMessage();
            return false;
        }
    }
    
    public boolean deleteNonAccessibleFolders(int userId,String folderName){
        try(Connection conn = ConnectionDB.connect()){
            
            PreparedStatement stmt = conn.prepareStatement("delete from nonaccess where user_id = ? and nonAccessibleFileOrFolderName = ?");
            
            stmt.setInt(1, userId);
            stmt.setString(2, folderName);
            
            stmt.executeUpdate();
            
            return true;
        }catch(Exception e){
            e.getMessage();
            return false;
        }
    }
}
