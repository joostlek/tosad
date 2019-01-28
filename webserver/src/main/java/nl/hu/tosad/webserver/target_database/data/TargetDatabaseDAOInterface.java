package nl.hu.tosad.webserver.target_database.data;

import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbTable;

import java.util.List;
import java.util.Map;

public interface TargetDatabaseDAOInterface {
    List<String> getTableList(Database database);

    Map<String, String> getColumnList(DbTable table);
}
