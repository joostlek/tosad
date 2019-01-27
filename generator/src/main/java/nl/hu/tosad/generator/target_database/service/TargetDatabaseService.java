package nl.hu.tosad.generator.target_database.service;

import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.generator.target_database.data.DatabaseConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TargetDatabaseService implements TargetDatabaseServiceInterface {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public boolean execute(List<String> sql, Database database) {
        DatabaseConnectionFactory connectionFactory = new DatabaseConnectionFactory(database);
        try (Connection connection = connectionFactory.createConnection()) {
            logger.log(Level.INFO, "Created communication");
            for (String query : sql) {
                try (Statement statement = connection.createStatement()) {
                    statement.execute(query);
                }
                logger.log(Level.INFO, String.format("Executed %s", sql));
            }
            connection.close();
            logger.log(Level.INFO, "Done!");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
        return true;
    }
}
