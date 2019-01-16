package nl.hu.tosad.webserver.controllers;

import nl.hu.tosad.domain.entities.BusinessRuleBuilder;
import nl.hu.tosad.domain.entities.BusinessRuleBuilderFactory;
import nl.hu.tosad.domain.entities.BusinessRuleBuilderFactoryInterface;
import nl.hu.tosad.domain.entities.BusinessRuleTypes;
import nl.hu.tosad.domain.entities.domain.BusinessRule;
import nl.hu.tosad.domain.entities.domain.dto.AttributeRangeRuleDTO;
import nl.hu.tosad.webserver.services.BusinessRuleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.OperationNotSupportedException;

@Controller
public class BusinessRuleController {
    @Autowired
    BusinessRuleServiceInterface businessRuleService;

    private BusinessRuleBuilderFactoryInterface builderFactoryInterface = new BusinessRuleBuilderFactory();

    @GetMapping("/")
    public String ruleList(Model model) {
        model.addAttribute("businessRules", businessRuleService.getAllBusinessRules());
        return "ruleList";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("businessRule", new AttributeRangeRuleDTO());
        return "add";
    }

    @PostMapping("/addRule")
    public String greetingSubmit(@ModelAttribute AttributeRangeRuleDTO businessRule, Model model) throws OperationNotSupportedException {
        BusinessRuleBuilder businessRuleBuilder = builderFactoryInterface.getBuilder(BusinessRuleTypes.ATTRIBUTE_RANGE_RULE);
            BusinessRule businessRule1 = businessRuleBuilder
                    .setName(businessRule.getName())
                    .setRangeStart(businessRule.getRangeStart())
                    .setRangeEnd(businessRule.getRangeEnd())
                    .setOperator(businessRule.getOperator())
                    .build();
            BusinessRule businessRule2 = businessRuleService.saveBusinessRule(businessRule1);
            model.addAttribute("MadeBusinessRule", businessRule2);
            return "define_rule_succeed";
    }

}
