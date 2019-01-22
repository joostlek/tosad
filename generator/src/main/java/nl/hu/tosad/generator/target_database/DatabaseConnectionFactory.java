package nl.hu.tosad.generator.target_database;

import nl.hu.tosad.domain.target_database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {

    private final Database config;

    public DatabaseConnectionFactory(Database config) {
        this.config = config;
    }

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(this.config.getJdbcUrl(), this.config.getUsername(), this.config.getPassword());
    }

}
