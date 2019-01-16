package nl.hu.tosad.domain.entities;

import nl.hu.tosad.domain.entities.domain.AttributeRangeRule;
import nl.hu.tosad.domain.entities.domain.BusinessRule;
import nl.hu.tosad.domain.entities.domain.Operator;

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
    public BusinessRuleBuilder setRangeStart(int rangeStart) {
        this.attributeRangeRule.setRangeStart(rangeStart);
        return this;
    }

    @Override
    public BusinessRuleBuilder setOperator(Operator operator) {
        this.attributeRangeRule.setOperator(operator);
        return this;
    }

    @Override
    public BusinessRuleBuilder setRangeEnd(int rangeEnd) {
        this.attributeRangeRule.setRangeEnd(rangeEnd);
        return this;
    }

    @Override
    public BusinessRule build() {
        return attributeRangeRule;
    }
}
