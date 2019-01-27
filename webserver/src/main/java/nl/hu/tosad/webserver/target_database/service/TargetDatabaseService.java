package nl.hu.tosad.webserver.target_database.service;


import com.google.gson.Gson;
import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.webserver.target_database.data.ColumnRepository;
import nl.hu.tosad.webserver.target_database.data.DatabaseRepository;
import nl.hu.tosad.webserver.target_database.data.DialectRepository;
import nl.hu.tosad.webserver.target_database.data.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class TargetDatabaseService implements TargetDatabaseServiceInterface {
    private final DatabaseRepository databaseRepository;

    private final ColumnRepository columnRepository;

    private final TableRepository tableRepository;

    private final DialectRepository dialectRepository;

    @Autowired
    public TargetDatabaseService(DatabaseRepository databaseRepository, ColumnRepository columnRepository, TableRepository tableRepository, DialectRepository dialectRepository) {
        this.databaseRepository = databaseRepository;
        this.columnRepository = columnRepository;
        this.tableRepository = tableRepository;
        this.dialectRepository = dialectRepository;
    }

    @Override
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

    @Override
    public boolean databaseExists(Long id) {
        return databaseRepository.existsById(id);
    }

    private DbTable validateTable(DbTable table) {
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

    @Override
    public List<DbTable> getAllTables() {
        return tableRepository.findAll();
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

    @Override
    public Dialect getDialectById(Long id) {
        return dialectRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Database> getAllDatabases(){
        return databaseRepository.findAll();
    }

    @Override
    public List<String> generateQueries(List<Long> businessRuleIds, boolean wet) {
        try {
            Socket s = new Socket(System.getenv("GEN_URL"), wet ? Integer.parseInt(System.getenv("GEN_PORT_WET")) : Integer.parseInt(System.getenv("GEN_PORT_DRY")));
            OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            Gson gson = new Gson();
            pw.println(gson.toJson(businessRuleIds) + "\r\n");
            pw.flush();
            pw.println("\r\n");
            pw.flush();
            InputStream inputStream = s.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String sql = "[]";
            while (scanner.hasNextLine()) {
                sql = scanner.nextLine();
            }
            scanner.close();
            inputStream.close();
            pw.close();
            s.close();
            String[] sqlQueries = gson.fromJson(sql, String[].class);
            return Arrays.asList(sqlQueries);
        } catch (IOException e) {
            throw new RuntimeException("");
        }
    }
}
