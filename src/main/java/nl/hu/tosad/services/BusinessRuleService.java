package nl.hu.tosad.services;

import nl.hu.tosad.entities.BusinessRule;
import nl.hu.tosad.repositories.BusinessRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public BusinessRule save(BusinessRule businessRule) {
        return businessRuleRepository.save(businessRule);
    }
}
