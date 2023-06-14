package data.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection conn = null;

    public Database() {
        try {
            if (conn == null || conn.isClosed()) {
                String url = "jdbc:postgresql://localhost:5432/mydiary";
                String user = "postgres";
                String password = "81520";
       
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the PostgreSQL server successfully.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // finally {
        // try {
        // if (conn != null) {
        // conn.close();
        // }
        // } catch (SQLException ex) {
        // System.out.println(ex.getMessage());
        // }
        // }
    }

    public static Connection getConnection() {
        return conn;
    }
}
