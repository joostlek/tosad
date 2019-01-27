package nl.hu.tosad.webserver.ruletype.service;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Operator;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.webserver.ruletype.data.BusinessRuleTypeRepository;
import nl.hu.tosad.webserver.ruletype.data.OperatorRepository;
import nl.hu.tosad.webserver.ruletype.data.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleTypeService implements RuleTypeServiceInterface {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    private final BusinessRuleTypeRepository businessRuleTypeRepository;

    @Autowired
    public RuleTypeService(BusinessRuleTypeRepository businessRuleTypeRepository) {
        this.businessRuleTypeRepository = businessRuleTypeRepository;
    }


    @Override
    public BusinessRuleType getBusinessRuleTypeByCode(String code) {
        return businessRuleTypeRepository.findById(code).orElse(null);
    }

    @Override
    public BusinessRuleType saveBusinessRulesType(BusinessRuleType businessRuleType) {
        return businessRuleTypeRepository.save(businessRuleType);
    }

    @Override
    public List<BusinessRuleType> getAllBusinessRules() {
        return businessRuleTypeRepository.findAll();
    }

    @Override
    public List<Template> getTemplatesByDialect(Dialect dialect) {
        return templateRepository.findAllByDialect(dialect);
    }

    @Override
    public Operator getOperator(Long id) {
        return operatorRepository.findById(id).orElse(null);
    }

}
