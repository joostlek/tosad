package nl.hu.tosad.generator.services;
import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.generator.dao.BusinessRuleDAO;

import java.util.List;



public class BusinessRuleService {

    private static BusinessRuleDAO businessRuleDAO;

    public BusinessRuleService() {
        businessRuleDAO = new BusinessRuleDAO();
    }

    public void persist(BusinessRule entity) {
        businessRuleDAO.openCurrentSessionwithTransaction();
        businessRuleDAO.persist(entity);
        businessRuleDAO.closeCurrentSessionwithTransaction();
    }

    public void update(BusinessRule entity) {
        businessRuleDAO.openCurrentSessionwithTransaction();
        businessRuleDAO.update(entity);
        businessRuleDAO.closeCurrentSessionwithTransaction();
    }

    public BusinessRule findById(Long id) {
        businessRuleDAO.openCurrentSession();
        BusinessRule businessRule = businessRuleDAO.findById(id);
        businessRuleDAO.closeCurrentSession();
        return businessRule;
    }

    public void delete(Long id) {
        businessRuleDAO.openCurrentSessionwithTransaction();
        BusinessRule businessRule = businessRuleDAO.findById(id);
        businessRuleDAO.delete(businessRule);
        businessRuleDAO.closeCurrentSessionwithTransaction();
    }

    public List<BusinessRule> findAll() {
        businessRuleDAO.openCurrentSession();
        List<BusinessRule> businessRules = businessRuleDAO.findAll();
        businessRuleDAO.closeCurrentSession();
        return businessRules;
    }

    public void deleteAll() {
        businessRuleDAO.openCurrentSessionwithTransaction();
        businessRuleDAO.deleteAll();
        businessRuleDAO.closeCurrentSessionwithTransaction();
    }

    public BusinessRuleDAO businessRuleDAO() {
        return businessRuleDAO;
    }
}