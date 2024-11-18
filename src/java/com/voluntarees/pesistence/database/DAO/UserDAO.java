package java.com.voluntarees.pesistence.database.DAO;

import java.com.voluntarees.entities.User;
import java.com.voluntarees.pesistence.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {
    public void addUser(User objUser){
        String sql= "INSERT INTO users (name, email, password, role) VALUES(?, ?, ?, ?);";
        try(
            Connection conn = DatabaseConnection.getConnetction();
            PreparedStatement stament = conn.prepareStatement(sql)
        ){
            stament.setString(1, objUser.getName());
            stament.setString(2, objUser.getEmail());
            stament.setString(3, objUser.getPassword());
            stament.setString(4, objUser.getRole().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
