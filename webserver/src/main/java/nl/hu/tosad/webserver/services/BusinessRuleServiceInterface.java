package nl.hu.tosad.webserver.services;

import nl.hu.tosad.domain.entities.domain.BusinessRule;

import java.util.List;

public interface BusinessRuleServiceInterface {
    BusinessRule getBusinessRuleById(Long id);

    BusinessRule saveBusinessRule(BusinessRule businessRule);

    List<BusinessRule> getAllBusinessRules();
}
