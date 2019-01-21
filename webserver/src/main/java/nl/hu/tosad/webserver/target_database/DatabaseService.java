package nl.hu.tosad.webserver.target_database;


import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseService implements DatabaseServiceInterface {
    private final DatabaseRepository databaseRepository;

    @Autowired
    public DatabaseService(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }


}
