package nl.hu.tosad.services;

import nl.hu.tosad.entities.domain.BusinessRule;

import java.util.List;

public interface BusinessRuleServiceInterface {
    BusinessRule getBusinessRuleById(Long id);

    BusinessRule saveBusinessRule(BusinessRule businessRule);

    List<BusinessRule> getAllBusinessRules();
}
