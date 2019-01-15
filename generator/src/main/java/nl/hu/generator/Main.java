package nl.hu.generator;

import nl.hu.generator.dialects.Dialect;
import nl.hu.generator.dialects.PostgresqlDialect;
import nl.hu.generator.templates.Template;
import nl.hu.generator.validators.Validator;
import nl.hu.generator.validators.ValidatorFactory;
import nl.hu.tosad.entities.domain.AttributeRangeRule;
import nl.hu.tosad.entities.domain.Operator;

public class Main {
    public static void main(String[] args) {
        Dialect dialect = new PostgresqlDialect();
        AttributeRangeRule businessRule = new AttributeRangeRule();
        businessRule.setName("naam");
        businessRule.setOperator(Operator.IN_BETWEEN);
        businessRule.setRangeEnd(3);
        businessRule.setRangeStart(0);

        ValidatorFactory validatorFactory = new ValidatorFactory(businessRule);
        Validator validator = validatorFactory.getValidator();
        if (validator.validate()) {
            Template template = dialect.getTemplate(businessRule);
            System.out.println(template.getSQL());
        }
    }
}
