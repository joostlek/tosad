package nl.hu.tosad.webserver.bussiness_rules_types;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessRuleTypeService implements BusinessRuleTypeServiceInterface{

    private final BusinessRuleTypeRepository businessRuleTypeRepository;

    @Autowired
    public BusinessRuleTypeService(BusinessRuleTypeRepository businessRuleTypeRepository) {
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
