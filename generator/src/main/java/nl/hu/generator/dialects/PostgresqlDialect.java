package nl.hu.generator.dialects;

import nl.hu.generator.templates.AttributeRangeRulePostgresqlTemplate;
import nl.hu.generator.templates.Template;
import nl.hu.tosad.entities.domain.AttributeRangeRule;
import nl.hu.tosad.entities.domain.BusinessRule;

public class PostgresqlDialect implements Dialect {
    @Override
    public Template getTemplate(BusinessRule businessRule) {
        if (businessRule instanceof AttributeRangeRule) {
            return new AttributeRangeRulePostgresqlTemplate((AttributeRangeRule) businessRule);
        }
        return null;
    }
}
