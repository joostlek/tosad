package nl.hu.tosad.generator.rule.data;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.generator.utils.BaseDAO;
import org.hibernate.Session;

public class RuleRepository implements RuleRepositoryInterface {

    private BaseDAO baseDAO;

    public RuleRepository() {
        baseDAO = new BaseDAO();
    }

    public BusinessRule getBusinessRuleById(Long id) {
        Session session = baseDAO.openCurrentSession();
        BusinessRule businessRule = session.find(BusinessRule.class, id);
        baseDAO.closeCurrentSession();
        if (businessRule != null) {
            return businessRule;
        } else {
            throw new RuntimeException("Businessrule not found");
        }
    }
}