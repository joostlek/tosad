package nl.hu.tosad.controllers;

import nl.hu.tosad.entities.BusinessRuleBuilder;
import nl.hu.tosad.entities.BusinessRuleBuilderFactoryInterface;
import nl.hu.tosad.entities.BusinessRuleTypes;
import nl.hu.tosad.entities.domain.BusinessRule;
import nl.hu.tosad.entities.domain.dto.AttributeRangeRuleDTO;
import nl.hu.tosad.services.BusinessRuleServiceInterface;
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

    @Autowired
    BusinessRuleBuilderFactoryInterface businessRuleBuilderFactoryInterface;

    @GetMapping("/")
    public String test(Model model) {
        model.addAttribute("businessRule", new AttributeRangeRuleDTO());
        model.addAttribute("businessRules", businessRuleService.getAllBusinessRules());
        return "test";
    }

    @PostMapping("/addRule")
    public String greetingSubmit(@ModelAttribute AttributeRangeRuleDTO businessRule) {
        try {
            BusinessRuleBuilder businessRuleBuilder = businessRuleBuilderFactoryInterface.getBuilder(BusinessRuleTypes.ATTRIBUTE_RANGE_RULE);
            BusinessRule businessRule1 = businessRuleBuilder
                    .setName(businessRule.getName())
                    .setRange(businessRule.getRange())
                    .build();
            BusinessRule businessRule2 = businessRuleService.saveBusinessRule(businessRule1);
            System.out.println(businessRule2);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
        return "test";
    }

}