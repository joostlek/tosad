package nl.hu.tosad.generator.target_database.data;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.generator.utils.BaseDAO;
import org.hibernate.Session;

public class TargetDatabaseRepository implements TargetDatabaseRepositoryInterface {

    private BaseDAO baseDAO;

    public TargetDatabaseRepository() {
        this.baseDAO = new BaseDAO();
    }

    @Override
    public Database findDatabaseByBusinessRule(BusinessRule businessRule) {
        Session session = baseDAO.openCurrentSessionWithTransaction();
        Database database = businessRule.getDatabase();
        baseDAO.closeCurrentSessionWithTransaction();
        return database;
    }
}
