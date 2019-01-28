package nl.hu.tosad.webserver.rule.service;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.webserver.rule.data.BusinessRuleRepository;
import nl.hu.tosad.domain.target_database.DbTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService implements RuleServiceInterface {

    private final BusinessRuleRepository businessRuleRepository;

    @Autowired
    public RuleService(BusinessRuleRepository businessRuleRepository) {
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
    public List<BusinessRule> getAllBusinessRulesByDatabaseId(Long databaseId) {
        List<BusinessRule> businessRules = businessRuleRepository.findAll();
        List<BusinessRule> businessRulesFinal = new ArrayList<>();
        for(BusinessRule br : businessRules){
            if(br.getDatabase().getId() == databaseId){
                businessRulesFinal.add(br);
            }
        }
        return businessRulesFinal;
    }

    public List<BusinessRule> searchBusinessRules(Long databaseId, String value) {
        List<BusinessRule> businessRules = getAllBusinessRulesByDatabaseId(databaseId);
        List<BusinessRule> businessRulesFinal = new ArrayList<BusinessRule>();
        for(BusinessRule br: businessRules){
            if(br.getBusinessRuleType().getName().equals(value) ||
                br.getName().equals(value)){
                businessRulesFinal.add(br);
            }
            else {
                for(DbTable table:br.getTables()){
                    if(table.getName().equals(value)){
                        businessRulesFinal.add(br);
                    }
                }
            }
        }
        return businessRulesFinal;
    }

    @Override
    public List<BusinessRule> getAllBusinessRules() {
        return businessRuleRepository.findAll();
    }


    public void deleteBusinessRule(BusinessRule br) {
        businessRuleRepository.delete(br);
    }
}
