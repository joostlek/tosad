package nl.hu.tosad.webserver.target_database.presentation;

import nl.hu.tosad.domain.target_database.Database;

public interface DatabaseHolderInterface {
    Database getDatabase();

    void setDatabase(Database database);
}
