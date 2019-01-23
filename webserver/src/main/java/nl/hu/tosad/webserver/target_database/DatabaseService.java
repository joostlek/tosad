package nl.hu.tosad.webserver.target_database;


import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DatabaseService implements DatabaseServiceInterface {
    private final DatabaseRepository databaseRepository;

    private final ColumnRepository columnRepository;

    private final TableRepository tableRepository;

    @Autowired
    public DatabaseService(DatabaseRepository databaseRepository, ColumnRepository columnRepository, TableRepository tableRepository) {
        this.databaseRepository = databaseRepository;
        this.columnRepository = columnRepository;
        this.tableRepository = tableRepository;
    }

    public boolean getDatabaseDefinition(Database database) {
        DatabaseConnectionFactory databaseConnectionFactory = new DatabaseConnectionFactory(database);
        Connection connection;
        try {
            connection = databaseConnectionFactory.createConnection();
            System.out.println(connection.getMetaData());
        } catch (SQLException e) {
            return false;
        }
        return true;
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
