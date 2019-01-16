package nl.hu.tosad.generator;

import nl.hu.tosad.domain.entities.domain.AttributeRangeRule;
import nl.hu.tosad.domain.entities.domain.Operator;
import nl.hu.tosad.generator.dialects.Dialect;
import nl.hu.tosad.generator.dialects.PostgresqlDialect;
import nl.hu.tosad.generator.templates.Template;
import nl.hu.tosad.generator.validators.Validator;
import nl.hu.tosad.generator.validators.ValidatorFactory;

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
