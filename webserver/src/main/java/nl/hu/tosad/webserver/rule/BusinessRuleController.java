package nl.hu.tosad.webserver.rule;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.webserver.ruletype.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpSession;

@Controller
public class BusinessRuleController {
    @Autowired
    BusinessRuleServiceInterface businessRuleService;

    @Autowired
    private TemplateRepository templateRepository;

//    private BusinessRuleBuilderFactoryInterface builderFactoryInterface = new BusinessRuleBuilderFactory();

    @GetMapping("/")
    public String ruleList(Model model) {
        model.addAttribute("businessRules", businessRuleService.getAllBusinessRules());
        return "ruleList";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("templates", templateRepository.findAll());
        return "add";
    }

//    @PostMapping("/addRule")
//    public String greetingSubmit(@ModelAttribute AttributeRangeRuleDTO businessRule, Model model) throws OperationNotSupportedException {
//        BusinessRuleBuilder businessRuleBuilder = builderFactoryInterface.getBuilder(BusinessRuleTypes.ATTRIBUTE_RANGE_RULE);
//            BusinessRule businessRule1 = businessRuleBuilder
//                    .setName(businessRule.getName())
//                    .setRangeStart(businessRule.getRangeStart())
//                    .setRangeEnd(businessRule.getRangeEnd())
//                    .setOperator(businessRule.getOperator())
//                    .build();
//            BusinessRule businessRule2 = businessRuleService.saveBusinessRule(businessRule1);
//            model.addAttribute("MadeBusinessRule", businessRule2);
//            return "define_rule_succeed";
//    }

}
