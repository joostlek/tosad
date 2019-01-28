package nl.hu.tosad.webserver.rule.service;

import nl.hu.tosad.domain.rule.BusinessRule;

import java.util.List;

public interface RuleServiceInterface {
    BusinessRule getBusinessRuleById(Long id);

    BusinessRule saveBusinessRule(BusinessRule businessRule);

    List<BusinessRule> getAllBusinessRulesByDatabaseId(Long databaseId);

    List<BusinessRule> getAllBusinessRules();

    void deleteBusinessRule(BusinessRule br);

    void deleteRuleValues(Long ruleId);
    List<BusinessRule> searchBusinessRules(Long databaseId, String value);

}
