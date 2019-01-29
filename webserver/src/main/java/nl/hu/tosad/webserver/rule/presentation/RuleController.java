package nl.hu.tosad.webserver.rule.presentation;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.BusinessRuleBuilderInterface;
import nl.hu.tosad.domain.rule.Value;
import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.DbTable;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.webserver.rule.service.RuleServiceInterface;
import nl.hu.tosad.webserver.ruletype.presentation.RuleTypeHolder;
import nl.hu.tosad.webserver.ruletype.service.RuleTypeServiceInterface;
import nl.hu.tosad.webserver.target_database.presentation.DatabaseHolder;
import nl.hu.tosad.webserver.target_database.presentation.DatabaseHolderInterface;
import nl.hu.tosad.webserver.target_database.service.TargetDatabaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

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

    @GetMapping("/")
    public RedirectView homePage(RedirectAttributes attributes) {
        return new RedirectView("/database");
    }

    @GetMapping("/rules")
    public String ruleList(
            @ModelAttribute("database") DatabaseHolder databaseHolder,
            @RequestParam(value = "search", required = false) String query,
            Model model) {
        Long databaseId = databaseHolder.getDatabase().getId();
        if (query != null && !query.equals("")) {
            model.addAttribute("rules", ruleService.searchBusinessRules(databaseId, query));
            model.addAttribute("query", query);
        } else {
            model.addAttribute("rules", ruleService.getAllBusinessRulesByDatabaseId(databaseId));
        }
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

        ruleService.saveBusinessRule(toBusinessRule(ruleDTO, ruleTypeHolder.getTable(), ruleTypeHolder.getBusinessRuleType(), template));

        attributes.addFlashAttribute("database", databaseHolder);
        return new RedirectView("/rules");
    }

    @GetMapping("/rules/{id}/edit")
    public String rule(Model model,
                       @ModelAttribute("database") DatabaseHolderInterface databaseHolder,
                       @PathVariable Long id) {
        BusinessRule businessRule = ruleService.getBusinessRuleById(id);
        Template template = businessRule.getBusinessRuleType().getTemplate(businessRule.getDatabase().getDialect());
        BusinessRule rule = ruleService.getBusinessRuleById(id);

        businessRule.getValues().forEach(value -> {
            System.out.println(value.getValue());
        });

        model.addAttribute("businessRule", rule);
        model.addAttribute("bRuleType", businessRule.getBusinessRuleType());
        model.addAttribute("columns", businessRule.getTables().get(0).getColumns());
        model.addAttribute("rule", toRuleDTO(rule));
        model.addAttribute("typeAttributes", template.getAttributes());
        return "rule/detail-rule";
    }

    @PostMapping("/rules/{id}/edit")
    public RedirectView updateRule(Model model,
                                   @PathVariable Long id,
                                   @ModelAttribute RuleDTO ruleDTO,
                                   @ModelAttribute("database") DatabaseHolderInterface databaseHolder,
                                   RedirectAttributes attributes) {
        BusinessRule businessRule = ruleService.getBusinessRuleById(id);
        BusinessRuleType ruleType = businessRule.getBusinessRuleType();
        Template template = ruleType.getTemplate(databaseHolder.getDatabase().getDialect());

        BusinessRule rule = toBusinessRule(ruleDTO, businessRule.getTables().get(0), ruleType, template);
        businessRule.setOperator(rule.getOperator());
        businessRule.setErrorMessage(rule.getErrorMessage());
        for (Value value : businessRule.getValues()) {
            if (value.getType().equalsIgnoreCase("COLUMN")) {
                value.setColumn(rule.getValue(value.getPosition()).getColumn());
            } else {
                value.setValue(rule.getValue(value.getPosition()).getValue());
            }
        }
        businessRule.setDescription(rule.getDescription());
        ruleService.saveBusinessRule(businessRule);

        attributes.addFlashAttribute("database", databaseHolder);
        return new RedirectView("/rules");
    }

    @GetMapping("/rules/generate")
    public String generate(Model model,
                           @ModelAttribute("database") DatabaseHolder databaseHolder) {
        Long databaseId = databaseHolder.getDatabase().getId();

        model.addAttribute("selectedRules", new GenerateRuleDTO());
        model.addAttribute("businessRules", ruleService.getAllBusinessRulesByDatabaseId(databaseId));
        return "rule/generate-rule";
    }

    @PostMapping(value = "/rules/generate", params = "action=test")
    public String testGeneration(Model model,
                                 @ModelAttribute GenerateRuleDTO ruleId) {
        List<Long> ruleIds = Arrays.asList(ruleId.getRuleIds());
        List<String> queries = targetDatabaseService.generateQueries(ruleIds, false);

        model.addAttribute("test", true);
        model.addAttribute("queries", queries);
        model.addAttribute("selectedRules", ruleId);
        return "rule/generated-code";
    }

    @PostMapping(value = "/rules/generate", params = "action=generate")
    public String generated(Model model,
                            @ModelAttribute GenerateRuleDTO ruleId) {
        List<Long> ruleIds = Arrays.asList(ruleId.getRuleIds());
        List<String> queries = targetDatabaseService.generateQueries(ruleIds, true);

        model.addAttribute("test", false);
        model.addAttribute("queries", queries);
        model.addAttribute("selectedRules", ruleId);
        return "rule/generated-code";
    }

    @GetMapping("/rules/{id}/delete")
    public String deleteRule(@PathVariable("id") Long id,
                             @ModelAttribute("database") DatabaseHolder databaseHolder,
                             Model model) {
        BusinessRule br = ruleService.getBusinessRuleById(id);
        ruleService.deleteBusinessRule(br);
        model.addAttribute("rule", br);
        return "rule/delete-rule";
    }

    private RuleDTO toRuleDTO(BusinessRule rule) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", rule.getName());
        properties.put("error", rule.getErrorMessage());
        properties.put("description", rule.getDescription());
        properties.put("list", new ArrayList<>());
        if (rule.getOperator() != null) {
            properties.put("operator", rule.getOperator().getId());
        }
        for (Value value : rule.getValues()) {
            if (value.getType().equalsIgnoreCase("COLUMN")) {
                properties.put(value.getLabel(), value.getColumn().getId());
            } else {
                properties.put(value.getLabel(), value);
            }
        }
        RuleDTO ruleDTO = new RuleDTO();
        ruleDTO.setProperties(properties);
        return ruleDTO;
    }

    private BusinessRule toBusinessRule(RuleDTO ruleDTO, DbTable table, BusinessRuleType ruleType, Template template) {
        Map<String, Object> ruleComponents = ruleDTO.getProperties();

        BusinessRuleBuilderInterface businessRuleBuilder = BusinessRule.getBuilder()
                .setName((String) ruleComponents.get("name"))
                .setErrorMessage((String) ruleComponents.get("error"))
                .addTable(table)
                .setType(ruleType);

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
                    } else if (!e.getValue().equals("operator")) {
                        businessRuleBuilder.addValue(new Value((String) ruleComponents.get(e.getKey()), e.getValue(), String.format("%s_%s", e.getKey(), e.getValue())));
                    }
                });
        return businessRuleBuilder.build();
    }

}
