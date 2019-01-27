package nl.hu.tosad.webserver.ruletype.service;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;

import java.util.List;

public interface RuleTypeServiceInterface {
    BusinessRuleType getBusinessRulesTypesbyId(long ID);

    BusinessRuleType saveBusinessRulesTypes(BusinessRuleType businessRuleType);

    List<BusinessRuleType> getAllBusinessRules();

}
