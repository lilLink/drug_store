package com.univer.lab.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class Dao {

    protected Connection connection;

    public Dao() {
        initConnection();
    }

    private void initConnection() {
        try(InputStream input = new FileInputStream("path/to/database.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            properties.setProperty("user", properties.getProperty("user"));
            properties.setProperty("password", properties.getProperty("password"));
            properties.setProperty("autoReconnect", "true");
            this.connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
