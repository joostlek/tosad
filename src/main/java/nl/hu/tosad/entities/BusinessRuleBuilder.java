package nl.hu.tosad.entities;

import nl.hu.tosad.entities.domain.BusinessRule;
import nl.hu.tosad.entities.domain.Operator;

public interface BusinessRuleBuilder {
    BusinessRuleBuilder setName(String name);

    BusinessRuleBuilder setRangeStart(int rangeStart);

    BusinessRuleBuilder setOperator(Operator operator);

    BusinessRuleBuilder setRangeEnd(int rangeEnd);

    BusinessRule build();
}
