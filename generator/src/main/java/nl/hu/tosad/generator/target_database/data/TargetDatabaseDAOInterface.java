package nl.hu.tosad.generator.target_database.data;

import nl.hu.tosad.domain.target_database.Database;

import java.util.List;

public interface TargetDatabaseDAOInterface {
    boolean execute(List<String> sql, Database database);

    boolean dropExistingTriggers(Database database);
}
