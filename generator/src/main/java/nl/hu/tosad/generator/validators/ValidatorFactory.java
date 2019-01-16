package nl.hu.tosad.generator.validators;

import nl.hu.tosad.domain.entities.domain.AttributeRangeRule;
import nl.hu.tosad.domain.entities.domain.BusinessRule;

public class ValidatorFactory {
    private BusinessRule businessRule;

    public ValidatorFactory(BusinessRule businessRule) {
        this.businessRule = businessRule;
    }

    public Validator getValidator() {
        if (businessRule instanceof AttributeRangeRule) {
            return new AttributeRangeRuleValidator((AttributeRangeRule) businessRule);
        }
        return null;
    }
}
