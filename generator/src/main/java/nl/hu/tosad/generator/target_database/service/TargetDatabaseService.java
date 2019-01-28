package nl.hu.tosad.generator.target_database.service;

import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.generator.target_database.data.TargetDatabaseDAO;
import nl.hu.tosad.generator.target_database.data.TargetDatabaseDAOInterface;

import java.util.List;
import java.util.logging.Logger;

public class TargetDatabaseService implements TargetDatabaseServiceInterface {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private TargetDatabaseDAOInterface targetDatabaseDAO = new TargetDatabaseDAO();

    @Override
    public boolean execute(List<String> sql, Database database) {
        this.dropExistingTriggers(database);
        return targetDatabaseDAO.execute(sql, database);
    }

    private boolean dropExistingTriggers(Database database) {
        return targetDatabaseDAO.dropExistingTriggers(database);
    }


}
