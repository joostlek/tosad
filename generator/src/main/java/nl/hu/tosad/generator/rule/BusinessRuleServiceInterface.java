package nl.hu.tosad.generator.rule;

import java.util.List;

public interface BusinessRuleServiceInterface {
    List<String> convertBusinessRulesDry(List<Long> businessRuleIds);

    List<String> convertBusinessRulesWet(List<Long> businessRuleIds);
}
