package nl.hu.tosad.webserver.target_database.service;


import com.google.gson.Gson;
import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.webserver.target_database.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class TargetDatabaseService implements TargetDatabaseServiceInterface {
    private final DatabaseRepository databaseRepository;

    private final ColumnRepository columnRepository;

    private final TableRepository tableRepository;

    private final DialectRepository dialectRepository;

    private final TargetDatabaseDAOInterface targetDatabaseDAO;

    @Autowired
    public TargetDatabaseService(DatabaseRepository databaseRepository, ColumnRepository columnRepository, TableRepository tableRepository, DialectRepository dialectRepository, TargetDatabaseDAOInterface targetDatabaseDAO) {
        this.databaseRepository = databaseRepository;
        this.columnRepository = columnRepository;
        this.tableRepository = tableRepository;
        this.dialectRepository = dialectRepository;
        this.targetDatabaseDAO = targetDatabaseDAO;
    }

    @Override
    public Database validateDatabase(Database database) {
        List<String> tableNames = targetDatabaseDAO.getTableList(database);
        for (String tableName : tableNames) {
            if (database.hasTable(tableName)) {
                DbTable table = tableRepository.findByNameAndDatabase(tableName, database);
                table = validateTable(table);
            } else {
                DbTable table = tableRepository.save(new DbTable(tableName, database));
                table = validateTable(table);
            }
        }
        return databaseRepository.save(database);
    }

    @Override
    public boolean databaseExists(Long id) {
        return databaseRepository.existsById(id);
    }

    @Override
    public List<DbTable> getTablesByDatabaseId(Long id) {
        return tableRepository.findAllByDatabaseId(id);
    }

    @Override
    public List<DbColumn> getColumnsByTableId(Long id) {
        return columnRepository.findAllByTableId(id);
    }

    @Override
    public Database saveDatabase(Database database) {
        Database newDatabase = databaseRepository.save(database);
        return this.validateDatabase(newDatabase);
    }

    private DbTable validateTable(DbTable table) {
        Map<String, String> columnDefinition = targetDatabaseDAO.getColumnList(table);
        columnDefinition
                .entrySet()
                .stream()
                .parallel()
                .forEach(
                        e -> {
                            if (table.hasColumn(e.getKey())) {
                                DbColumn column = table.getColumn(e.getKey());
                                if (!column.getType().equals(e.getValue())) {
                                    throw new RuntimeException("Definition changed");
                                }
                            } else {
                                DbColumn column = new DbColumn(e.getKey(), e.getValue(), table);
                                columnRepository.save(column);
                            }
                        }
                );
        return tableRepository.save(table);
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
    public List<Dialect> getAllDialects() {
        return dialectRepository.findAll();
    }

    @Override
    public List<Database> getAllDatabases() {
        return databaseRepository.findAll();
    }

    @Override
    public List<String> generateQueries(List<Long> businessRuleIds, boolean wet) {
        try (Socket s = new Socket(System.getenv("GEN_URL"), wet ? Integer.parseInt(System.getenv("GEN_PORT_WET")) : Integer.parseInt(System.getenv("GEN_PORT_DRY")))) {
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
