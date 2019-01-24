package nl.hu.tosad.generator.rule.data;

import nl.hu.tosad.domain.rule.BusinessRule;

public interface RuleRepositoryInterface {
    BusinessRule getBusinessRuleById(Long id);
}
