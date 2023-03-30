package org.example.repository.ConnectionBD;
import java.sql.*;
public class ConexionBD {
    private static String url = "jdbc:mysql://127.0.0.1:3307/test";
    private  static String user ="root";
    private static String password = "sql1234";
    private static Connection connection;
    public static Connection getInstance() throws SQLException {
        if(connection==null){
            connection = DriverManager.getConnection(url,user,password);
        }
        return connection;
    }
}
