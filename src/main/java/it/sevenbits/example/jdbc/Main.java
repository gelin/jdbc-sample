package it.sevenbits.example.jdbc;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost/test";
        String username = "postgres";
        String password = "postgres";
        Connection connection = DriverManager.getConnection(url, username, password);
        // do something

        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO items (name) VALUES (?)");
            statement.setString(1, String.format("New Item at %s", new java.util.Date()));
            statement.execute();
            statement.close();
        }

        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
            while (resultSet.next())
            {
                System.out.println(String.format("%s\t%s", resultSet.getInt(1), resultSet.getString(2)));
            }
            resultSet.close();
            statement.close();
        }

        connection.close();
    }

}
