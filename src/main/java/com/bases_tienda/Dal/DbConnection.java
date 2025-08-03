package com.bases_tienda.Dal;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private String database;
    private String host;
    private String port;
    private String scheme;
    private String user;
    private String password;

    private Connection connection;

    public DbConnection() {
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return;
            }
            properties.load(input);
            database = properties.getProperty("database");
            host = properties.getProperty("host");
            port = properties.getProperty("port");
            scheme = properties.getProperty("scheme");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
    }

     private String getStringConnection(){
        StringBuilder str = new StringBuilder();
        str.append(database);
        str.append(host);
        str.append(":");
        str.append(port);
        str.append("/");
        str.append(scheme);
        return str.toString();
    }

    public void connect() {
        try {
            String stringConnection = getStringConnection();
            this.connection = DriverManager.getConnection(stringConnection, user, password);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void disconnect() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }
}
