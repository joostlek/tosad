package nl.hu.tosad.generator.templates;

import nl.hu.tosad.domain.entities.domain.AttributeRangeRule;

public class AttributeRangeRulePostgresqlTemplate implements Template {
    private AttributeRangeRule attributeRangeRule;

    public AttributeRangeRulePostgresqlTemplate(AttributeRangeRule attributeRangeRule) {
        this.attributeRangeRule = attributeRangeRule;
    }

    @Override
    public String getSQL() {
        return "CHECK (" + attributeRangeRule.getName() + " " + attributeRangeRule.getOperator().name() + " " + attributeRangeRule.getRangeStart() + " AND " + attributeRangeRule.getRangeEnd() + ")";
    }
}
