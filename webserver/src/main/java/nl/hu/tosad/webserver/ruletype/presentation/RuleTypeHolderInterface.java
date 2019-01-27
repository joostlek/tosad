package nl.hu.tosad.webserver.ruletype.presentation;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;

public interface RuleTypeHolderInterface {
    public BusinessRuleType getBusinessRuleType();

    void setBusinessRuleType(BusinessRuleType businessRuleType);
}
