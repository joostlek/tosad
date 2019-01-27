package nl.hu.tosad.webserver.ruletype.service;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;

import java.util.List;

public interface RuleTypeServiceInterface {
    BusinessRuleType getBusinessRuleTypeByCode(String code);

    BusinessRuleType saveBusinessRulesType(BusinessRuleType businessRuleType);

    List<BusinessRuleType> getAllBusinessRules();
}
