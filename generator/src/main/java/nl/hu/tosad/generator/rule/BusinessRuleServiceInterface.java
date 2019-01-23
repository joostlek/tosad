package nl.hu.tosad.generator.rule;

import nl.hu.tosad.domain.rule.BusinessRule;

import java.util.List;

public interface BusinessRuleServiceInterface {
    List<String> convertBusinessRulesDry(List<Long> businessRuleIds);

    List<String> convertBusinessRulesWet(List<Long> businessRuleIds);

    List<BusinessRule> getBusinessRulesByIdList(List<Long> businessRuleIds);
}
