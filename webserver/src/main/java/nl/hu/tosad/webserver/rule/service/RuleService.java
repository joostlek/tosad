package nl.hu.tosad.webserver.rule.service;

import nl.hu.tosad.domain.rule.BusinessRule;
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
        return businessRuleRepository.save(businessRule);
    }

    @Override
    public List<BusinessRule> getAllBusinessRulesByDatabaseId(Long databaseId) {
        List<BusinessRule> businessRules = businessRuleRepository.findAll();
        List<BusinessRule> businessRulesFinal = new ArrayList<>();
        for (BusinessRule br : businessRules) {
            if (br.getDatabase().getId().equals(databaseId)) {
                businessRulesFinal.add(br);
            }
        }
        return businessRulesFinal;
    }

    @Override
    public List<BusinessRule> getAllBusinessRules() {
        return businessRuleRepository.findAll();
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
