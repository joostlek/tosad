package nl.hu.tosad.webserver.target_database.data;

import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbTable;
import nl.hu.tosad.webserver.target_database.service.DatabaseConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TargetDatabaseDAO implements TargetDatabaseDAOInterface {

    @Override
    public List<String> getTableList(Database database) {
        DatabaseConnectionFactory databaseConnectionFactory = new DatabaseConnectionFactory(database);
        try (Connection connection = databaseConnectionFactory.createConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, connection.getSchema(), "%", null);
            List<String> strings = new ArrayList<>();
            while (resultSet.next()) {
                String tableName = resultSet.getString(3);
                strings.add(tableName);
            }
            return strings;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Map<String, String> getColumnList(DbTable table) {
        DatabaseConnectionFactory databaseConnectionFactory = new DatabaseConnectionFactory(table.getDatabase());
        Map<String, String> columnDefinition = new HashMap<>();
        try (Connection connection = databaseConnectionFactory.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + table.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int i = 1;
            while (metaData.getColumnCount() >= i) {
                columnDefinition.put(metaData.getColumnName(i), metaData.getColumnTypeName(i));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnDefinition;
    }
}
