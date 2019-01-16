package nl.hu.tosad.generator.dialects;

import nl.hu.tosad.domain.entities.domain.BusinessRule;
import nl.hu.tosad.generator.templates.Template;

public interface Dialect {
    Template getTemplate(BusinessRule businessRule);
}
