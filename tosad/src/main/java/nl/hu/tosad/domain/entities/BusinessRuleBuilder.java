package nl.hu.tosad.domain.entities;

import nl.hu.tosad.domain.entities.domain.BusinessRule;
import nl.hu.tosad.domain.entities.domain.Operator;
import nl.hu.tosad.domain.entities.domain.database.DbColumn;

public interface BusinessRuleBuilder {
    BusinessRuleBuilder setName(String name);

    BusinessRuleBuilder setRangeStart(int rangeStart);

    BusinessRuleBuilder setOperator(Operator operator);

    BusinessRuleBuilder setRangeEnd(int rangeEnd);

    BusinessRuleBuilder setColumn(DbColumn dbColumn);

    BusinessRule build();
}
