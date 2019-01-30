package nl.hu.tosad.webserver.rule.service;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.Value;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;
import nl.hu.tosad.webserver.rule.data.BusinessRuleRepository;
import nl.hu.tosad.webserver.rule.data.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RuleService implements RuleServiceInterface {

    private static final String NUMBER = "NUMBER";
    private static final String VARCHAR2 = "VARCHAR2";
    private static final String COLUMN = "COLUMN";
    private static final String COLUMNO = "COLUMNO";
    private final BusinessRuleRepository businessRuleRepository;
    private final ValueRepository valueRepository;

    @Autowired
    public RuleService(BusinessRuleRepository businessRuleRepository, ValueRepository valueRepository) {
        this.businessRuleRepository = businessRuleRepository;
        this.valueRepository = valueRepository;
    }

    @Override
    public BusinessRule getBusinessRuleById(Long id) {
        return businessRuleRepository.findById(id).orElse(null);
    }

    @Override
    public BusinessRule saveBusinessRule(BusinessRule businessRule) {
        Value value1 = businessRule.getValue("column_column");
        if (value1 != null) {
            DbColumn column = value1.getColumn();
            for (Value value : businessRule.getValues()) {
                if (value.getType().equalsIgnoreCase(COLUMN) || value.getType().equalsIgnoreCase(COLUMNO)) {
                    if (!value.getColumn().getType().equals(column.getType())) {
                        throw new RuntimeException("Column not the same type");
                    }
                } else if (VARCHAR2.equals(column.getType()) && !value.getType().equalsIgnoreCase("statement")) {
                    value.setType(VARCHAR2);
                } else if (NUMBER.equals(column.getType()) && !value.getType().equalsIgnoreCase("statement")) {
                    try {
                        Long.parseLong(value.getField());
                    } catch (RuntimeException ex) {
                        throw new RuntimeException(value.getLabel() + " is not a number!");
                    }
                    value.setType(NUMBER);
                }
            }
        }
        return businessRuleRepository.save(businessRule);
    }

    @Override
    public List<BusinessRule> getAllBusinessRulesByDatabaseId(Long databaseId) {
        List<BusinessRule> businessRules = businessRuleRepository.findAllByOrderById();
        List<BusinessRule> businessRulesFinal = new ArrayList<>();
        for (BusinessRule br : businessRules) {
            if (br.getDatabase().getId().equals(databaseId)) {
                businessRulesFinal.add(br);
            }
        }
        return businessRulesFinal;
    }

    public List<BusinessRule> searchBusinessRules(Long databaseId, String value) {
        List<BusinessRule> businessRules = getAllBusinessRulesByDatabaseId(databaseId);
        List<BusinessRule> businessRulesFinal = new ArrayList<BusinessRule>();
        for (BusinessRule br : businessRules) {
            if (br.getBusinessRuleType().getName().equals(value) ||
                    br.getName().equals(value)) {
                businessRulesFinal.add(br);
            } else {
                for (DbTable table : br.getTables()) {
                    if (table.getName().equals(value)) {
                        businessRulesFinal.add(br);
                    }
                }
            }
        }
        return businessRulesFinal;
    }

    @Override
    public List<BusinessRule> getAllBusinessRules() {
        return businessRuleRepository.findAllByOrderById();
    }

    @Override
    public void deleteBusinessRule(BusinessRule br) {
        businessRuleRepository.delete(br);
    }

    @Override
    public void deleteRuleValues(Long ruleId) {
        valueRepository.deleteValuesByBusinessRuleId(ruleId);
    }
}
