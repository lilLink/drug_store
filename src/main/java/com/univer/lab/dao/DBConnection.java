package com.univer.lab.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DBConnection {

    /*protected Connection connection;

    private final static String PROPERTIES_PATH = "database.properties";

    public DBConnection() {
        try {
            initConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initConnection() throws ClassNotFoundException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(PROPERTIES_PATH));
            Class.forName("org.postgresql.Driver");
            properties.setProperty("user", properties.getProperty("user"));
            properties.setProperty("password", properties.getProperty("password"));
            properties.setProperty("autoReconnect", "true");
            this.connection = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }*/

    private static final String URL = "jdbc:postgresql://localhost:5433/drugstore";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    protected Connection connection;

    public DBConnection() {
        try {
            initConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initConnection() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");

            Properties properties = new Properties();
            properties.setProperty("user", USER);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("autoReconnect", "true");
            this.connection = DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
