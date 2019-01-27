package nl.hu.tosad.webserver.ruletype.service;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.Dialect;

import java.util.List;

public interface RuleTypeServiceInterface {
    BusinessRuleType getBusinessRuleTypeByCode(String code);

    BusinessRuleType saveBusinessRulesType(BusinessRuleType businessRuleType);

    List<BusinessRuleType> getAllBusinessRules();

    List<Template> getTemplatesByDialect(Dialect dialect);

}
