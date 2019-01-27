package nl.hu.tosad.webserver.ruletype.presentation;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;

public class RuleTypeHolder implements RuleTypeHolderInterface {
    private BusinessRuleType businessRuleType;

    @Override
    public BusinessRuleType getBusinessRuleType() {
        return businessRuleType;
    }

    @Override
    public void setBusinessRuleType(BusinessRuleType businessRuleType) {
        this.businessRuleType = businessRuleType;
    }
}
