package nl.hu.tosad.generator.utils;

import java.util.List;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.generator.dao.BusinessRuleDAOInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class BaseDAO implements BusinessRuleDAOInterface<BusinessRule, Long> {

    private Session currentSession;

    private Transaction currentTransaction;

    public BaseDAO() {}

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        configuration.addPackage("nl.hu.tosad.domain");
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(BusinessRule entity) {
        getCurrentSession().save(entity);
    }

    public void update(BusinessRule entity) {
        getCurrentSession().update(entity);
    }

    public BusinessRule findById(Long id) {
        BusinessRule businessRule = getCurrentSession().get(BusinessRule.class, id);
        return businessRule;
    }

    public void delete(BusinessRule entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessRule> findAll() {
        List<BusinessRule> businessRules = getCurrentSession().createQuery("select br from BusinessRule br").list();
        return businessRules;
    }

    public void deleteAll() {
        List<BusinessRule> entityList = findAll();
        for (BusinessRule entity : entityList) {
            delete(entity);
        }
    }
}
