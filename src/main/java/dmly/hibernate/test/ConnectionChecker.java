package dmly.hibernate.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionChecker {
    private static final String URL = "jdbc:postgresql://localhost/hibernate_test";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            System.out.println("Successfully connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
