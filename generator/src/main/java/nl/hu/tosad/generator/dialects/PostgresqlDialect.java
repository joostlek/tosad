package nl.hu.tosad.generator.dialects;

import nl.hu.tosad.domain.entities.domain.AttributeRangeRule;
import nl.hu.tosad.domain.entities.domain.BusinessRule;
import nl.hu.tosad.generator.templates.AttributeRangeRulePostgresqlTemplate;
import nl.hu.tosad.generator.templates.Template;

public class PostgresqlDialect implements Dialect {
    @Override
    public Template getTemplate(BusinessRule businessRule) {
        if (businessRule instanceof AttributeRangeRule) {
            return new AttributeRangeRulePostgresqlTemplate((AttributeRangeRule) businessRule);
        }
        return null;
    }
}
