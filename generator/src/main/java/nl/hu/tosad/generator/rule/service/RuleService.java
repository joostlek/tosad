package nl.hu.tosad.generator.rule.service;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.Value;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.generator.rule.data.RuleRepository;
import nl.hu.tosad.generator.rule.data.RuleRepositoryInterface;
import nl.hu.tosad.generator.target_database.data.TargetDatabaseRepository;
import nl.hu.tosad.generator.target_database.service.TargetDatabaseService;
import nl.hu.tosad.generator.target_database.service.TargetDatabaseServiceInterface;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.List;

public class RuleService implements RuleServiceInterface {

    private RuleRepositoryInterface ruleRepositoryInterface;

    private TargetDatabaseServiceInterface targetDatabaseService;

    public RuleService() {
        this.ruleRepositoryInterface = new RuleRepository();
        this.targetDatabaseService = new TargetDatabaseService();
    }

    @Override
    public List<String> convertBusinessRulesWet(List<Long> businessRuleIds) {
        if (businessRuleIds.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> sql = this.convertBusinessRulesDry(businessRuleIds);
        BusinessRule businessRule = ruleRepositoryInterface.getBusinessRuleById(businessRuleIds.get(0));

        TargetDatabaseRepository targetDatabaseRepository = new TargetDatabaseRepository();
        Database database = targetDatabaseRepository.findDatabaseByBusinessRule(businessRule);
        targetDatabaseService.execute(sql, database);
        return sql;
    }

    @Override
    public List<BusinessRule> getBusinessRulesByIdList(List<Long> businessRuleIds) {
        List<BusinessRule> businessRules = new ArrayList<>();
        for (Long id : businessRuleIds) {
            try {
                businessRules.add(ruleRepositoryInterface.getBusinessRuleById(id));
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return businessRules;
    }

    @Override
    public List<String> convertBusinessRulesDry(List<Long> businessRuleIds) {
        List<BusinessRule> businessRules = this.getBusinessRulesByIdList(businessRuleIds);
        List<String> sql = new ArrayList<>();

        if (businessRules.isEmpty()) {
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
            stringTemplate.add("operator_operator", businessRule.getOperator());
            stringTemplate.add("error", businessRule.getErrorMessage());
            for (Value value : businessRule.getValues()) {
                stringTemplate.add(value.getPosition(), value);
            }
            String result = stringTemplate.render();
            sql.add(result);
        }
        return sql;
    }
}
