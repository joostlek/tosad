package nl.hu.tosad.domain.rule;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Operator;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;

public class BusinessRuleBuilder implements BusinessRuleBuilderInterface {
    private BusinessRule businessRule;

    public BusinessRuleBuilder() {
        businessRule = new BusinessRule();
    }

    @Override
    public BusinessRuleBuilderInterface setName(String name) {
        businessRule.setName(name);
        return this;
    }

    @Override
    public BusinessRuleBuilderInterface setErrorMessage(String errorMessage) {
        businessRule.setErrorMessage(errorMessage);
        return this;
    }

    @Override
    public BusinessRuleBuilderInterface addTable(DbTable table) {
        businessRule.addDbTable(table);
        return this;
    }

    @Override
    public BusinessRuleBuilderInterface addColumn(DbColumn column) {
        businessRule.addDbColumn(column);
        return this;
    }

    @Override
    public BusinessRuleBuilderInterface addValue(Value value) {
        businessRule.addValue(value);
        value.setBusinessRule(businessRule);
        return this;
    }

    @Override
    public BusinessRuleBuilderInterface setOperator(Operator operator) {
        businessRule.setOperator(operator);
        return this;
    }

    @Override
    public BusinessRuleBuilderInterface setType(BusinessRuleType businessRuleType) {
        businessRule.setBusinessRuleType(businessRuleType);
        return this;
    }

    @Override
    public BusinessRule build() {
        return this.businessRule;
    }
}
