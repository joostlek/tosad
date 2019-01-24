package nl.hu.tosad.generator.target_database.service;

import nl.hu.tosad.domain.target_database.Database;

import java.util.List;

public interface TargetDatabaseServiceInterface {
    boolean execute(List<String> sql, Database database);
}
