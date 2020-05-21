package models;

import java.sql.*;

public class Database {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/poll?user=test&password=test");
            return conn;

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL driver not found");
        } catch (SQLException se) {
            System.err.println("Cannot connect to DB");
        }
        return null;
    }
}
