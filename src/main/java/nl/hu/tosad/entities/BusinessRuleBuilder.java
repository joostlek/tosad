package nl.hu.tosad.entities;

import nl.hu.tosad.entities.domain.BusinessRule;

public interface BusinessRuleBuilder {
    BusinessRuleBuilder setName(String name);

    BusinessRuleBuilder setRange(String range);

    BusinessRule build();
}
