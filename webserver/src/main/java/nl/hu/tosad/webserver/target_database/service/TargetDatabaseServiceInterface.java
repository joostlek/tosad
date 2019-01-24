package nl.hu.tosad.webserver.target_database.service;

import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;
import nl.hu.tosad.domain.target_database.Dialect;

import java.util.List;

public interface TargetDatabaseServiceInterface {

    List<DbTable> getAllTables();

    DbColumn getColumnById(Long id);

    DbTable getTableById(Long id);

    Database getDatabaseById(Long id);

    Dialect getDialectById(Long id);

    List<Database> getAllDatabases();

    List<String> generateQueries(List<Long> businessRuleIds, boolean wet);

    Database validateDatabase(Database database);
}
