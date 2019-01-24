package nl.hu.tosad.webserver.target_database;


import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;
import nl.hu.tosad.domain.target_database.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Service
public class DatabaseService implements DatabaseServiceInterface {
    private final DatabaseRepository databaseRepository;

    private final ColumnRepository columnRepository;

    private final TableRepository tableRepository;

    private final DialectRepository dialectRepository;

    @Autowired
    public DatabaseService(DatabaseRepository databaseRepository, ColumnRepository columnRepository, TableRepository tableRepository, DialectRepository dialectRepository) {
        this.databaseRepository = databaseRepository;
        this.columnRepository = columnRepository;
        this.tableRepository = tableRepository;
        this.dialectRepository = dialectRepository;
    }

    public Database validateDatabase(Database database) {
        DatabaseConnectionFactory databaseConnectionFactory = new DatabaseConnectionFactory(database);
        try {
            Connection connection = databaseConnectionFactory.createConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, connection.getSchema(), "%", null);
            while (resultSet.next()) {
                String tableName = resultSet.getString(3);
                System.out.println(tableName);
                if (database.hasTable(tableName)) {
                    DbTable table = tableRepository.findByNameAndDatabase(tableName, database);
                    table = validateTable(table);
                } else {
                    DbTable table = tableRepository.save(new DbTable(tableName, database));
                    table = validateTable(table);
                }
            }
            return databaseRepository.save(database);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DbTable validateTable(DbTable table) {
        DatabaseConnectionFactory databaseConnectionFactory = new DatabaseConnectionFactory(table.getDatabase());
        try {
            Connection connection = databaseConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + table.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int i = 1;
            while (metaData.getColumnCount() >= i) {
                if (table.hasColumn(metaData.getColumnName(i))) {
                    DbColumn column = table.getColumn(metaData.getColumnName(i));
                    if (!column.getType().equals(metaData.getColumnTypeName(i))) {
                        throw new SQLException("Definition changed");
                    }
                } else {
                    DbColumn column = new DbColumn(metaData.getColumnName(i), metaData.getColumnTypeName(i), table);
//                    table.addColumn(column);
                    columnRepository.save(column);
                }
                i++;
            }
            return tableRepository.save(table);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Database getDatabaseDefinition(Database database) {
        database.setTables(new ArrayList<>());
        DatabaseConnectionFactory databaseConnectionFactory = new DatabaseConnectionFactory(database);
        Connection connection;
        try {
            connection = databaseConnectionFactory.createConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, connection.getSchema(), "%", null);
            while (resultSet.next()) {
                database.addTable(getTableDefinition(database, resultSet.getString(3)));
            }
            return databaseRepository.save(database);
        } catch (SQLException e) {
            return null;
        }
    }

    private DbTable getTableDefinition(Database database, String tableName) throws SQLException {
        DatabaseConnectionFactory databaseConnectionFactory = new DatabaseConnectionFactory(database);
        Connection connection = databaseConnectionFactory.createConnection();
        ResultSet resultSet = connection.prepareStatement("select * from " + tableName).executeQuery();
        DbTable table = tableRepository.save(new DbTable(tableName, database));
        int i = 1;
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        while (resultSetMetaData.getColumnCount() >= i) {
            table.addColumn(getColumnDefinition(table, resultSetMetaData.getColumnName(i), resultSetMetaData.getColumnTypeName(i)));
            i++;
        }
        resultSet.close();
        return table;
    }

    private DbColumn getColumnDefinition(DbTable table, String columnName, String columnType) {
        return columnRepository.save(new DbColumn(columnName, columnType, table));
    }

    @Override
    public DbColumn getColumnById(Long id) {
        return columnRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public DbTable getTableById(Long id) {
        return tableRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Database getDatabaseById(Long id) {
        return databaseRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Dialect getDialectbyID(Long id) {
        return dialectRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    private DbColumn saveColumn(DbColumn column) {
        return this.columnRepository.save(column);
    }

    private DbTable saveTable(DbTable table) {
        return this.tableRepository.save(table);
    }

    public Database saveDatabase(Database database) {
        return this.databaseRepository.save(database);
    }
}
