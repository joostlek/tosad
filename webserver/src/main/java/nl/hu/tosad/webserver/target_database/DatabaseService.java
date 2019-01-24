package nl.hu.tosad.webserver.target_database;


import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;
import nl.hu.tosad.domain.target_database.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    public Dialect getDialectbyID(Long id) {
        return dialectRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Database> getAllDatabases(){
        return databaseRepository.findAll();
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
