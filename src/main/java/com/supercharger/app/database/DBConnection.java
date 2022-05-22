package com.supercharger.app.database;

import java.sql.*;

public class DBConnection {

    private static final String connectionUrl = "jdbc:mysql://localhost:3306/supercharger_db?serverTimezone=America/Argentina/Ushuaia";
    private static final String userName = System.getenv("DB_USER");
    private static final String password = System.getenv("DB_PASS");

    private static Connection connection;

    private DBConnection() {
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

    private static final class InstanceHolder {
        private static final DBConnection instance = new DBConnection();
    }

    public static DBConnection getInstance() throws SQLException {
        return InstanceHolder.instance;
    }

}
