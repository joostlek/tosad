package nl.hu.tosad.generator.validators;

import nl.hu.tosad.domain.entities.domain.AttributeRangeRule;

public class AttributeRangeRuleValidator implements Validator {
    private AttributeRangeRule attributeRangeRule;

    public AttributeRangeRuleValidator(AttributeRangeRule attributeRangeRule) {
        this.attributeRangeRule = attributeRangeRule;
    }

    @Override
    public boolean validate() {
        return attributeRangeRule.getOperator() != null && attributeRangeRule.getName() != null;
    }
}
