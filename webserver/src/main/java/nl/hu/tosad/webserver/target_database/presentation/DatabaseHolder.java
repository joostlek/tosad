package nl.hu.tosad.webserver.target_database.presentation;

import nl.hu.tosad.domain.target_database.Database;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class DatabaseHolder implements DatabaseHolderInterface {
    private Database database;

    public Database getDatabase() {
        return database;
    }

    @Override
    public void setDatabase(Database database) {
        this.database = database;
    }
}
