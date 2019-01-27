package nl.hu.tosad.webserver.rule.presentation;

import javassist.NotFoundException;
import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.Value;
import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.webserver.rule.data.BusinessRuleRepository;
import nl.hu.tosad.webserver.rule.service.RuleServiceInterface;
import nl.hu.tosad.webserver.ruletype.data.BusinessRuleTypeRepository;
import nl.hu.tosad.webserver.ruletype.data.TemplateRepository;
import nl.hu.tosad.webserver.target_database.presentation.ChosenDatabaseDTO;
import nl.hu.tosad.webserver.target_database.service.TargetDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RuleController {
    @Autowired
    RuleServiceInterface businessRuleService;

    @Autowired
    TargetDatabaseService targetDatabaseService;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private BusinessRuleTypeRepository businessRuleTypeRepository;

    @Autowired
    private BusinessRuleRepository businessRuleRepository;



    @GetMapping("/allRules")
    public String ruleList(@ModelAttribute ChosenDatabaseDTO dbn, Model model) {

        return "ruleList";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("templates", templateRepository.findAll());
        model.addAttribute("chosenRule", new ChosenRuleTypeDTO());
        return "add";
    }

    @GetMapping("/businessrule/{id}")
    public String businessrule(Model model,@PathVariable Long id) {
        model.addAttribute("businessRule", businessRuleService.getBusinessRuleById(id));
        BusinessRule businessRule = businessRuleService.getBusinessRuleById(id);
        Map<String, Object> map = new HashMap<>();
        for (Value value: businessRule.getValues()){
            map.put(value.getPosition(),value.getValue());
        }
        Template template = businessRule.getBusinessRuleType().getTemplate(businessRule.getDatabase().getDialect());
        model.addAttribute("map",map);
        model.addAttribute("type", businessRule.getBusinessRuleType());
        model.addAttribute("templateByType", template.getAttributes());
        model.addAttribute("template", template);
        model.addAttribute("tables", targetDatabaseService.getAllTables());
        return "businessrule";
    }

//    @DeleteMapping("/deleteBusinessrule/{id}")
//    public void deleteRule(@PathVariable Long id) throws Exception {
//         BusinessRule br = businessRuleService.deleteBusinessRule(id);
//        if (br ==null)
//            throw new Exception();
//    }


    @GetMapping("/delete/{id}")
    public String deleteBR(@PathVariable("id") long id, Model model) {
        BusinessRule br = businessRuleService.getBusinessRuleById(id);
        businessRuleRepository.delete(br);
        return "RuleList";
    }

    @PostMapping("/addType")
    public String addType(@ModelAttribute ChosenRuleTypeDTO brtc, Model model) {
        Dialect dialect = targetDatabaseService.getDialectById((long) 1);
        BusinessRuleType type = businessRuleTypeRepository.findBusinessRuleTypeByCode(brtc.getBusinessRuleTypeCode());
        Template template = businessRuleTypeRepository.findBusinessRuleTypeByCode(brtc.getBusinessRuleTypeCode()).getTemplate(dialect);
        model.addAttribute("templateByType", template.getAttributes());
        model.addAttribute("type", type);
        model.addAttribute("template", template.toString());
        model.addAttribute("tables", targetDatabaseService.getAllTables());
        return "fillRule";
    }


}
