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
            PreparedStatement statement = conn.prepareStatement(sql)
        ){
            statement.setString(1, objUser.getName());
            statement.setString(2, objUser.getEmail());
            statement.setString(3, objUser.getPassword());
            statement.setString(4, objUser.getRole().toString());

            statement.executeUpdate();

            System.out.println("El usuario se agrego correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
