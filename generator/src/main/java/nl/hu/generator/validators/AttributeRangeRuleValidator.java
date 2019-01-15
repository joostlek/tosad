package nl.hu.generator.validators;

import nl.hu.tosad.entities.domain.AttributeRangeRule;

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
