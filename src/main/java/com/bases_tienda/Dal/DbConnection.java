package com.bases_tienda.Dal;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase de conexión a la base de datos MySQL usando properties externos.
 */
public class DbConnection {
    private String url;
    private String user;
    private String password;

    private Connection connection;

    public DbConnection() {
        loadProperties();
    }

    /**
     * Carga configuración de db.properties en el classpath.
     */
    private void loadProperties() {
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (in == null) {
                throw new IOException("No se encontró el archivo db.properties en classpath");
            }
            props.load(in);
            String host = props.getProperty("db.host");
            String port = props.getProperty("db.port");
            String schema = props.getProperty("db.schema");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
            this.url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8mb4&serverTimezone=UTC",
                    host, port, schema);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar configuración de base de datos", e);
        }
    }

    /**
     * Abre la conexión si no está abierta.
     */
    public void connect() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                this.connection = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo conectar a la base de datos", e);
        }
    }

    /**
     * Devuelve la conexión activa.
     */
    public Connection getConnection() {
        connect();
        return this.connection;
    }

    /**
     * Cierra la conexión si está abierta.
     */
    public void disconnect() {
        if (this.connection != null) {
            try {
                if (!this.connection.isClosed()) {
                    this.connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar la conexión", e);
            }
        }
    }
}
