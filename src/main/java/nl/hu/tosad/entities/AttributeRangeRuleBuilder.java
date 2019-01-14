package nl.hu.tosad.entities;

import nl.hu.tosad.entities.domain.AttributeRangeRule;
import nl.hu.tosad.entities.domain.BusinessRule;

public class AttributeRangeRuleBuilder implements BusinessRuleBuilder {
    private AttributeRangeRule attributeRangeRule;

    public AttributeRangeRuleBuilder() {
        this.attributeRangeRule = new AttributeRangeRule();
    }

    @Override
    public BusinessRuleBuilder setName(String name) {
        this.attributeRangeRule.setName(name);
        return this;
    }

    @Override
    public BusinessRuleBuilder setRange(String range) {
        this.attributeRangeRule.setRange(range);
        return this;
    }

    @Override
    public BusinessRule build() {
        return attributeRangeRule;
    }
}
