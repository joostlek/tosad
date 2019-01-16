package nl.hu.tosad.webserver.business_rules;

import nl.hu.tosad.domain.entities.domain.BusinessRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessRuleService implements BusinessRuleServiceInterface {

    private final BusinessRuleRepository businessRuleRepository;

    @Autowired
    public BusinessRuleService(BusinessRuleRepository businessRuleRepository) {
        this.businessRuleRepository = businessRuleRepository;
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
    public List<BusinessRule> getAllBusinessRules() {
        return businessRuleRepository.findAll();
    }
}
