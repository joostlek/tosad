package nl.hu.tosad.webserver.ruletype;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;

import java.util.List;

public interface BusinessRuleTypeServiceInterface {
    BusinessRuleType getBusinessRulesTypesbyId(long ID);

    BusinessRuleType saveBusinessRulesTypes(BusinessRuleType businessRuleType);

    List<BusinessRuleType> getAllBusinessRules();
}
