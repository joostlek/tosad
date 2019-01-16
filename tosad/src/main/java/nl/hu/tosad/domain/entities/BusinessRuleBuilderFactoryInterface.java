package nl.hu.tosad.domain.entities;

import javax.naming.OperationNotSupportedException;

public interface BusinessRuleBuilderFactoryInterface {
    BusinessRuleBuilder getBuilder(BusinessRuleTypes type) throws OperationNotSupportedException;
}
