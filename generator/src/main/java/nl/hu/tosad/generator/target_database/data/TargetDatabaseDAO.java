package nl.hu.tosad.generator.target_database.data;

import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbTable;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TargetDatabaseDAO implements TargetDatabaseDAOInterface {
    private static final String TRIGGER_NAME = "TRIGGER_NAME";
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
                logger.log(Level.INFO, "Executed {0}", query);
            }
            logger.log(Level.INFO, "Done!");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean dropExistingTriggers(Database database) {
        DatabaseConnectionFactory connectionFactory = new DatabaseConnectionFactory(database);
        try (Connection connection = connectionFactory.createConnection()) {
            logger.log(Level.INFO, "Created communication with target database");
            for (DbTable table : database.getTables()) {
                logger.log(Level.INFO, "Drop triggers for table {0}", table.getName());
                try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM ALL_TRIGGERS where TABLE_NAME = ?")) {
                    statement.setString(1, table.getName());
                    try (ResultSet resultSet = statement.executeQuery();) {
                        while (resultSet.next()) {
                            try (PreparedStatement dropStatement = connection.prepareStatement("DROP TRIGGER \"" + resultSet.getString(TRIGGER_NAME) + "\"")) {
                                if (dropStatement.execute()) {
                                    logger.log(Level.INFO, "Dropped trigger {0}", resultSet.getString(TRIGGER_NAME));
                                } else {
                                    logger.log(Level.SEVERE, "Error dropping trigger {0}", resultSet.getString(TRIGGER_NAME));
                                }
                            }
                        }
                    }
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
        logger.log(Level.INFO, "Finished dropping all triggers");
        return true;
    }
}
