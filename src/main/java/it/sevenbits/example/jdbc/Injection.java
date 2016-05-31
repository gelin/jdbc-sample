package it.sevenbits.example.jdbc;

import java.sql.*;

public class Injection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost/test";
        String username = "postgres";
        String password = "postgres";
        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();
        String value = "'); DROP TABLE items; SELECT ('1";
        statement.execute("INSERT INTO items (name) VALUES ('" + value + "')");
        statement.close();

        connection.close();
    }

}
