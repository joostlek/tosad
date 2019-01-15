package nl.hu.generator.dialects;

import nl.hu.generator.templates.Template;
import nl.hu.tosad.entities.domain.BusinessRule;

public interface Dialect {
    Template getTemplate(BusinessRule businessRule);
}
