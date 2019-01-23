package nl.hu.tosad.webserver.target_database;

import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;

public interface DatabaseServiceInterface {
    boolean getDatabaseDefinition(Database database);

    DbColumn getColumnById(Long id);

    DbTable getTableById(Long id);

    Database getDatabaseById(Long id);
}
