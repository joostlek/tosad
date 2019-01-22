package nl.hu.tosad.generator.rule;

import nl.hu.tosad.domain.rule.BusinessRule;

import java.util.List;

public interface BusinessRuleRepositoryInterface {
//    public List<BusinessRule> getBusinessRuleByList(List<Long> ids);

    BusinessRule getBusinessRuleById(Long id);
}
