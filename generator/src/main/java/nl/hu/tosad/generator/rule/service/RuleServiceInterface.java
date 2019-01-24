package nl.hu.tosad.generator.rule.service;

import nl.hu.tosad.domain.rule.BusinessRule;

import java.util.List;

public interface RuleServiceInterface {
    List<String> convertBusinessRulesDry(List<Long> businessRuleIds);

    List<String> convertBusinessRulesWet(List<Long> businessRuleIds);

    List<BusinessRule> getBusinessRulesByIdList(List<Long> businessRuleIds);
}
