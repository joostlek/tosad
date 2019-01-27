package nl.hu.tosad.domain.rule;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Operator;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;

public interface BusinessRuleBuilderInterface {
    BusinessRuleBuilderInterface setName(String name);

    BusinessRuleBuilderInterface setErrorMessage(String errorMessage);

    BusinessRuleBuilderInterface addTable(DbTable table);

    BusinessRuleBuilderInterface addColumn(DbColumn column);

    BusinessRuleBuilderInterface addValue(Value value);

    BusinessRuleBuilderInterface setOperator(Operator operator);

    BusinessRuleBuilderInterface setType(BusinessRuleType businessRuleType);

    BusinessRule build();
}
