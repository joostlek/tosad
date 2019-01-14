package nl.hu.tosad.entities;

import javax.naming.OperationNotSupportedException;

public interface BusinessRuleBuilderFactoryInterface {
    BusinessRuleBuilder getBuilder(BusinessRuleTypes type) throws OperationNotSupportedException;
}
