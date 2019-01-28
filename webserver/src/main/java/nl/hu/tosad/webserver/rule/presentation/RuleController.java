package nl.hu.tosad.webserver.rule.presentation;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.BusinessRuleBuilderInterface;
import nl.hu.tosad.domain.rule.Value;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.webserver.rule.service.RuleServiceInterface;
import nl.hu.tosad.webserver.ruletype.presentation.RuleTypeDTO;
import nl.hu.tosad.webserver.ruletype.presentation.RuleTypeHolder;
import nl.hu.tosad.webserver.ruletype.service.RuleTypeServiceInterface;
import nl.hu.tosad.webserver.target_database.presentation.DatabaseHolder;
import nl.hu.tosad.webserver.target_database.service.TargetDatabaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
@SessionAttributes({"database", "ruleType"})
public class RuleController {
    private final RuleServiceInterface ruleService;

    private final TargetDatabaseServiceInterface targetDatabaseService;

    private final RuleTypeServiceInterface ruleTypeService;

    @Autowired
    public RuleController(RuleServiceInterface ruleService, TargetDatabaseServiceInterface targetDatabaseService, RuleTypeServiceInterface ruleTypeService) {
        this.ruleService = ruleService;
        this.targetDatabaseService = targetDatabaseService;
        this.ruleTypeService = ruleTypeService;
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
    public String addRule(Model model,
                          @ModelAttribute("database") DatabaseHolder databaseHolder,
                          @ModelAttribute("ruleType") RuleTypeHolder ruleTypeHolder) {
        Template template = ruleTypeHolder.getBusinessRuleType().getTemplate(databaseHolder.getDatabase().getDialect());

        Long tableId = ruleTypeHolder.getTable().getId();

        model.addAttribute("rule", new RuleDTO());
        model.addAttribute("columns", targetDatabaseService.getColumnsByTableId(tableId));
        model.addAttribute("type", ruleTypeHolder.getBusinessRuleType());
        model.addAttribute("typeAttributes", template.getAttributes());
        return "rule/create-rule";
    }

    @PostMapping("/rules/add")
    public RedirectView createRule(Model model,
                                   @ModelAttribute("database") DatabaseHolder databaseHolder,
                                   @ModelAttribute("ruleType") RuleTypeHolder ruleTypeHolder,
                                   @ModelAttribute RuleDTO ruleDTO,
                                   RedirectAttributes attributes) {
        Dialect dialect = databaseHolder.getDatabase().getDialect();
        Template template = ruleTypeHolder.getBusinessRuleType().getTemplate(dialect);

        Map<String, Object> ruleComponents = ruleDTO.getProperties();

        template.getAttributes();
        BusinessRuleBuilderInterface businessRuleBuilder = BusinessRule.getBuilder()
                .setName((String) ruleComponents.get("name"))
                .setErrorMessage((String) ruleComponents.get("error"))
                .addTable(ruleTypeHolder.getTable())
                .setType(ruleTypeHolder.getBusinessRuleType());

        if (ruleComponents.containsKey("operator")) {
            businessRuleBuilder.setOperator(ruleTypeService.getOperator(Long.parseLong((String) ruleComponents.get("operator"))));
        }

        template.getAttributes()
                .entrySet()
                .stream()
                .parallel()
                .forEach(e -> {
                    if (e.getValue().equals("column")) {
                        businessRuleBuilder.addValue(new Value(String.format("%s_%s", e.getKey(), e.getValue()), targetDatabaseService.getColumnById(Long.parseLong((String) ruleComponents.get(e.getKey())))));
                    } else {
                        businessRuleBuilder.addValue(new Value((String) ruleComponents.get(e.getKey()), e.getValue(), String.format("%s_%s", e.getKey(), e.getValue())));
                    }
                });

        ruleService.saveBusinessRule(businessRuleBuilder.build());

        attributes.addFlashAttribute("database", databaseHolder);
        attributes.addFlashAttribute("ruleType", ruleTypeHolder);
        return new RedirectView("/rules");
    }

    @GetMapping("/businessrule/{id}")
    public String businessrule(Model model, @PathVariable Long id) {
//        model.addAttribute("businessRule", businessRuleService.getBusinessRuleById(id));
//        BusinessRule businessRule = businessRuleService.getBusinessRuleById(id);
//        Map<String, Object> map = new HashMap<>();d
//        for (Value value : businessRule.getValues()) {
//            map.put(value.getPosition(), value.getValue());
//        }
//        Template template = businessRule.getBusinessRuleType().getTemplate(businessRule.getDatabase().getDialect());
//        model.addAttribute("map", map);
//        model.addAttribute("type", businessRule.getBusinessRuleType());
//        model.addAttribute("templateByType", template.getAttributes());
//        model.addAttribute("template", template);
//        model.addAttribute("tables", targetDatabaseService.getAllTables());
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
        BusinessRule br = ruleService.getBusinessRuleById(id);
        ruleService.deleteBusinessRule(br);
        model.addAttribute("rule", br);
        return "deleteRuleSucceed";
    }

    @PostMapping("/addType")
    public String addType(@ModelAttribute RuleTypeDTO brtc, Model model) {
//        Dialect dialect = targetDatabaseService.getDialectById((long) 1);
//        BusinessRuleType type = businessRuleTypeRepository.findBusinessRuleTypeByCode(brtc.getTypeCode());
//        Template template = businessRuleTypeRepository.findBusinessRuleTypeByCode(brtc.getTypeCode()).getTemplate(dialect);
//        model.addAttribute("templateByType", template.getAttributes());
//        model.addAttribute("type", type);
//        model.addAttribute("template", template.toString());
//        model.addAttribute("tables", targetDatabaseService.getAllTables());
        return "fillRule";
    }


}
