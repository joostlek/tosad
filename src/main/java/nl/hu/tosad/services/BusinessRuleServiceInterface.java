package nl.hu.tosad.services;

import nl.hu.tosad.entities.BusinessRule;

public interface BusinessRuleServiceInterface {
    BusinessRule getBusinessRuleById(Long id);

    BusinessRule save(BusinessRule businessRule);
}
