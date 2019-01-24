package nl.hu.tosad.webserver.ruletype.service;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.webserver.ruletype.data.BusinessRuleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleTypeService implements RuleTypeServiceInterface {

    private final BusinessRuleTypeRepository businessRuleTypeRepository;

    @Autowired
    public RuleTypeService(BusinessRuleTypeRepository businessRuleTypeRepository) {
        this.businessRuleTypeRepository = businessRuleTypeRepository;
    }


    @Override
    public BusinessRuleType getBusinessRulesTypesbyId(long ID) {
        return businessRuleTypeRepository.findById(ID).orElse(null);
    }

    @Override
    public BusinessRuleType saveBusinessRulesTypes(BusinessRuleType businessRuleType) {
        return businessRuleTypeRepository.save(businessRuleType);
    }

    @Override
    public List<BusinessRuleType> getAllBusinessRules() {
        return (List<BusinessRuleType>) businessRuleTypeRepository.findAll();
    }
}
