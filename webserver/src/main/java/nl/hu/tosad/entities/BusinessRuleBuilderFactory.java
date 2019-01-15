package nl.hu.tosad.entities;

import org.springframework.stereotype.Component;

import javax.naming.OperationNotSupportedException;

@Component
public class BusinessRuleBuilderFactory implements BusinessRuleBuilderFactoryInterface {
    public BusinessRuleBuilder getBuilder(BusinessRuleTypes type) throws OperationNotSupportedException {
        switch (type) {
            case ATTRIBUTE_RANGE_RULE:
                return new AttributeRangeRuleBuilder();
            case ATTRIBUTE_COMPARE_RULE:
                return new AttributeRangeRuleBuilder();
            case ATTRIBUTE_LIST_RULE:
                throw new OperationNotSupportedException();
            case ATTRIBUTE_OTHER_RULE:
                throw new OperationNotSupportedException();
            case TUPLE_COMPARE_RULE:
                throw new OperationNotSupportedException();
            case TUPLE_OTHER_RULE:
                throw new OperationNotSupportedException();
            case INTER_ENTITY_COMPARE_RULE:
                throw new OperationNotSupportedException();
            case ENTITY_OTHER_RULE:
                throw new OperationNotSupportedException();
            case MODIFY_RULE:
                throw new OperationNotSupportedException();
        }
        throw new OperationNotSupportedException();
    }


}
