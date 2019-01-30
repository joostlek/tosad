package nl.hu.tosad.generator.target_database.service;

import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.generator.target_database.data.TargetDatabaseDAO;
import nl.hu.tosad.generator.target_database.data.TargetDatabaseDAOInterface;

import java.util.List;

public class TargetDatabaseService implements TargetDatabaseServiceInterface {
    private TargetDatabaseDAOInterface targetDatabaseDAO = new TargetDatabaseDAO();

    @Override
    public boolean execute(List<String> sql, Database database) {
        if (this.dropExistingTriggers(database)) {
            return targetDatabaseDAO.execute(sql, database);
        }
        return false;
    }

    private boolean dropExistingTriggers(Database database) {
        return targetDatabaseDAO.dropExistingTriggers(database);
    }


}
