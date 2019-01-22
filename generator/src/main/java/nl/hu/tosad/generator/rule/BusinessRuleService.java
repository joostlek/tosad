package nl.hu.tosad.generator.rule;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.Value;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.Dialect;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleService implements BusinessRuleServiceInterface {

    private BusinessRuleRepository businessRuleRepository;

    public BusinessRuleService(BusinessRuleRepository businessRuleRepository) {
        this.businessRuleRepository = businessRuleRepository;
    }

    @Override
    public List<String> convertBusinessRulesWet(List<Long> businessRuleIds) {
        if (businessRuleIds.size() == 0) {
            return new ArrayList<>();
        }
        List<String> sql = this.convertBusinessRulesDry(businessRuleIds);
        BusinessRule businessRule = businessRuleRepository.getBusinessRuleById(businessRuleIds.get(0));
        Database database = businessRule.getDatabase();

        /* Add function to put the SQL in the server */
        return null;
    }

    @Override
    public List<String> convertBusinessRulesDry(List<Long> businessRuleIds) {
        List<BusinessRule> businessRules = businessRuleRepository.getBusinessRuleByList(businessRuleIds);
        List<String> sql = new ArrayList<>();

        if (businessRules.size() == 0) {
            return sql;
        }
        Database database;
        if (businessRules.get(0).getDatabase() != null) {
            database = businessRules.get(0).getDatabase();
        } else {
            return sql;
        }
        Dialect dialect = database.getDialect();

        for (BusinessRule businessRule : businessRules) {
            Template template = businessRule.getBusinessRuleType().getTemplate(dialect);
            ST stringTemplate = new ST(template.getText());
            stringTemplate.add("name", businessRule.getTriggerName());
            stringTemplate.add("table", businessRule.getTables().get(0));
            if (businessRule.getColumns() != null) {
                stringTemplate.add("column", businessRule.getColumns().get(0));
            }
            stringTemplate.add("operator", businessRule.getOperator());
            stringTemplate.add("error", businessRule.getErrorMessage());
            for (Value value : businessRule.getValues()) {
                stringTemplate.add(value.getPosition(), value.getValue());
            }
            String result = stringTemplate.render();
            sql.add(result);
        }
        return sql;
    }
}
