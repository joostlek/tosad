package nl.hu.tosad.webserver.rule.presentation;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.Value;
import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.webserver.rule.data.BusinessRuleRepository;
import nl.hu.tosad.webserver.rule.service.RuleServiceInterface;
import nl.hu.tosad.webserver.ruletype.data.BusinessRuleTypeRepository;
import nl.hu.tosad.webserver.ruletype.data.TemplateRepository;
import nl.hu.tosad.webserver.ruletype.presentation.RuleTypeDTO;
import nl.hu.tosad.webserver.ruletype.presentation.RuleTypeHolder;
import nl.hu.tosad.webserver.target_database.presentation.DatabaseHolder;
import nl.hu.tosad.webserver.target_database.service.TargetDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes({"database", "ruleType"})
public class RuleController {
    private final RuleServiceInterface ruleService;

    private final TargetDatabaseService targetDatabaseService;

    private final TemplateRepository templateRepository;

    private final BusinessRuleTypeRepository businessRuleTypeRepository;

    private final BusinessRuleRepository businessRuleRepository;

    @Autowired
    public RuleController(RuleServiceInterface ruleService, TargetDatabaseService targetDatabaseService, TemplateRepository templateRepository, BusinessRuleTypeRepository businessRuleTypeRepository, BusinessRuleRepository businessRuleRepository) {
        this.ruleService = ruleService;
        this.targetDatabaseService = targetDatabaseService;
        this.templateRepository = templateRepository;
        this.businessRuleTypeRepository = businessRuleTypeRepository;
        this.businessRuleRepository = businessRuleRepository;
    }

    @GetMapping("/rules")
    public String ruleList(
            @ModelAttribute("database") DatabaseHolder databaseHolder,
            Model model) {
        Long databaseId = databaseHolder.getDatabase().getId();
        model.addAttribute("rules", ruleService.getAllBusinessRulesByDatabaseId(databaseId));
        return "rule/rule-list";
    }

    @GetMapping("/rules/add")
    public String add(Model model,
                      @ModelAttribute("database") DatabaseHolder databaseHolder,
                      @ModelAttribute("ruleType") RuleTypeHolder ruleTypeHolder) {
        Long databaseId = databaseHolder.getDatabase().getId();
        Template template = ruleTypeHolder.getBusinessRuleType().getTemplate(databaseHolder.getDatabase().getDialect());
        model.addAttribute("tables", targetDatabaseService.getTablesByDatabaseId(databaseId));
        model.addAttribute("type", ruleTypeHolder.getBusinessRuleType());
        model.addAttribute("typeAttributes", template.getAttributes());
        return "create-rule";
    }

    @GetMapping("/businessrule/{id}")
    public String businessrule(Model model, @PathVariable Long id) {
        model.addAttribute("businessRule", ruleService.getBusinessRuleById(id));
        BusinessRule businessRule = ruleService.getBusinessRuleById(id);
        Map<String, Object> map = new HashMap<>();
        for (Value value : businessRule.getValues()) {
            map.put(value.getPosition(), value.getValue());
        }

        Map<String, Object> rulevalues = new HashMap<>();
        rulevalues.put("rngstart", 1);
        rulevalues.put("rngend", 5);


        Template template = businessRule.getBusinessRuleType().getTemplate(businessRule.getDatabase().getDialect());
        model.addAttribute("mappie",rulevalues);
        model.addAttribute("map", map);
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
//        BusinessRule br = businessRuleService.getBusinessRuleById(id);
//        businessRuleRepository.delete(br);
        return "RuleList";
    }

    @PostMapping("/addType")
    public String addType(@ModelAttribute RuleTypeDTO brtc, Model model) {
        Dialect dialect = targetDatabaseService.getDialectById((long) 1);
        BusinessRuleType type = businessRuleTypeRepository.findBusinessRuleTypeByCode(brtc.getTypeCode());
        Template template = businessRuleTypeRepository.findBusinessRuleTypeByCode(brtc.getTypeCode()).getTemplate(dialect);
        model.addAttribute("templateByType", template.getAttributes());
        model.addAttribute("type", type);
        model.addAttribute("template", template.toString());
        model.addAttribute("tables", targetDatabaseService.getAllTables());
        return "fillRule";
    }




}
