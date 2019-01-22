package nl.hu.tosad.generator.target_database;

import nl.hu.tosad.domain.target_database.Database;

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
        try {
            DatabaseConnectionFactory connectionFactory = new DatabaseConnectionFactory(database);
            Connection connection = connectionFactory.createConnection();
            logger.log(Level.INFO, "Created connection");
            for (String query : sql) {
                Statement statement = connection.createStatement();
                statement.execute(query);
                statement.close();
                logger.log(Level.INFO, "Executed ", sql);
            }
            connection.close();
            logger.log(Level.INFO, "Done!");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
