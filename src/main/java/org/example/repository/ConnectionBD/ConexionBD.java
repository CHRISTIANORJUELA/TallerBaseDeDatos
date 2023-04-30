package org.example.repository.ConnectionBD;
import java.sql.*;
public class ConexionBD {
    private static String url = "jdbc:mysql://127.0.0.1:3307/practicas";
    private  static String user ="root";
    private static String password = "sql1234";
    private static Connection connection;
    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
