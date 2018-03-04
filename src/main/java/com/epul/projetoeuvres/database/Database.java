package com.epul.projetoeuvres.database;

import java.sql.*;

public class Database {
    private static final long MAX_TIME_ALIVE = 60 * 60 * 1000;

    private static Database INSTANCE;
    private static long lastConnection;
    private Connection sql;

    private Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            sql = DriverManager.getConnection(
                    String.format("jdbc:%s://%s/%s?characterEncoding=UTF-8&useSSL=%s", "mysql",
                            "localhost", "baseoeuvre", true),
                    "epul", "epul");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        lastConnection = new java.util.Date().getTime();
    }

    public static Database getInstance() {
        if (INSTANCE == null || (new java.util.Date().getTime() - lastConnection > MAX_TIME_ALIVE)) {
            try {
                if (INSTANCE != null)
                    INSTANCE.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    private void close() throws SQLException {
        sql.close();
    }

    private Statement createStatement() throws SQLException {
        return sql.createStatement();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return createStatement().executeQuery(query);
    }

    public void executeUpdate(String query) throws SQLException {
        createStatement().executeUpdate(query);
    }
}
