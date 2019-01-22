package nl.hu.tosad.generator.rule;
import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.generator.utils.BaseDAO;

import java.util.List;



public class BusinessRuleRepository implements BusinessRuleRepositoryInterface {

    private static BaseDAO baseDAO;

    public BusinessRuleRepository() {
        baseDAO = new BaseDAO();
    }

    public void persist(BusinessRule entity) {
        baseDAO.openCurrentSessionwithTransaction();
        baseDAO.persist(entity);
        baseDAO.closeCurrentSessionwithTransaction();
    }

    public void update(BusinessRule entity) {
        baseDAO.openCurrentSessionwithTransaction();
        baseDAO.update(entity);
        baseDAO.closeCurrentSessionwithTransaction();
    }

    public BusinessRule getBusinessRuleById(Long id) {
        baseDAO.openCurrentSession();
        BusinessRule businessRule = baseDAO.findById(id);
        baseDAO.closeCurrentSession();
        return businessRule;
    }

    public void delete(Long id) {
        baseDAO.openCurrentSessionwithTransaction();
        BusinessRule businessRule = baseDAO.findById(id);
        baseDAO.delete(businessRule);
        baseDAO.closeCurrentSessionwithTransaction();
    }

    public List<BusinessRule> findAll() {
        baseDAO.openCurrentSession();
        List<BusinessRule> businessRules = baseDAO.findAll();
        baseDAO.closeCurrentSession();
        return businessRules;
    }

    public void deleteAll() {
        baseDAO.openCurrentSessionwithTransaction();
        baseDAO.deleteAll();
        baseDAO.closeCurrentSessionwithTransaction();
    }

    public BaseDAO businessRuleDAO() {
        return baseDAO;
    }
}