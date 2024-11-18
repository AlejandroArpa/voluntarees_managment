package java.com.voluntarees.pesistence.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/voluntarees" ;
    private  static  final String USER = "alejo";
    private  static  final String PASSWORD = "password";

    private DatabaseConnection(){}

    public static Connection getConnetction() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
