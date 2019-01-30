package nl.hu.tosad.webserver.rule.presentation;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.BusinessRuleBuilderInterface;
import nl.hu.tosad.domain.rule.Value;
import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.Database;
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

    private static final String ERROR_FIELD = "error";
    private static final String SELECTED_RULES_FIELD = "selectedRules";
    private static final String OPERATOR_FIELD = "operator";
    private static final String TABLE_FIELD = "table";

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
    public String ruleList(@ModelAttribute("database") DatabaseHolder databaseHolder,
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
                          @RequestParam(value = ERROR_FIELD, required = false) String error,
                          @ModelAttribute("database") DatabaseHolder databaseHolder,
                          @ModelAttribute("ruleType") RuleTypeHolder ruleTypeHolder) {
        Database database = databaseHolder.getDatabase();
        Template template = ruleTypeHolder.getBusinessRuleType().getTemplate(database.getDialect());

        Long tableId = ruleTypeHolder.getTable().getId();

        if (error != null) {
            model.addAttribute(ERROR_FIELD, error);
        }

        model.addAttribute("rule", new RuleDTO());
        model.addAttribute(TABLE_FIELD, database.getTables());
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
        attributes.addFlashAttribute("database", databaseHolder);

        try {
            ruleService.saveBusinessRule(toBusinessRule(ruleDTO, ruleTypeHolder.getTable(), ruleTypeHolder.getBusinessRuleType(), template));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return new RedirectView("/rules/add?error=" + ex.getMessage());
        }


        return new RedirectView("/rules");
    }

    @GetMapping("/rules/{id}/edit")
    public String rule(Model model,
                       @ModelAttribute("database") DatabaseHolderInterface databaseHolder,
                       @RequestParam(value = ERROR_FIELD, required = false) String error,
                       @PathVariable Long id) {
        BusinessRule businessRule = ruleService.getBusinessRuleById(id);
        Template template = businessRule.getBusinessRuleType().getTemplate(businessRule.getDatabase().getDialect());
        BusinessRule rule = ruleService.getBusinessRuleById(id);

        if (error != null) {
            model.addAttribute(ERROR_FIELD, error);
        }

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
        businessRule.setName(rule.getName());
        businessRule.setOperator(rule.getOperator());
        businessRule.setErrorMessage(rule.getErrorMessage());
        for (Value value : businessRule.getValues()) {
            if (value.getType().equalsIgnoreCase("COLUMN")) {
                value.setColumn(rule.getValue(value.getPosition()).getColumn());
            } else {
                value.setField(rule.getValue(value.getPosition()).getField());
            }
        }
        businessRule.setDescription(rule.getDescription());
        attributes.addFlashAttribute("database", databaseHolder);
        try {
            ruleService.saveBusinessRule(businessRule);
        } catch (RuntimeException ex) {
            return new RedirectView("/rules/" + id + "/edit?error=" + ex.getMessage());
        }

        return new RedirectView("/rules");
    }

    @GetMapping("/rules/generate")
    public String generate(Model model,
                           @ModelAttribute("database") DatabaseHolder databaseHolder) {
        Long databaseId = databaseHolder.getDatabase().getId();

        model.addAttribute(SELECTED_RULES_FIELD, new GenerateRuleDTO());
        model.addAttribute("businessRules", ruleService.getAllBusinessRulesByDatabaseId(databaseId));
        return "rule/generate-rule";
    }

    @PostMapping(value = "/rules/generate", params = "action=test")
    public String testGeneration(Model model,
                                 @ModelAttribute GenerateRuleDTO ruleId) {
        List<Long> ruleIds = Arrays.asList(ruleId.getRuleIds());
        List<String> queries = targetDatabaseService.generateQueries(ruleIds, false);

        model.addAttribute(ERROR_FIELD, ruleIds.size() != queries.size());
        model.addAttribute("test", true);
        model.addAttribute("queries", queries);
        model.addAttribute(SELECTED_RULES_FIELD, ruleId);
        return "rule/generated-code";
    }

    @PostMapping(value = "/rules/generate", params = "action=generate")
    public String generated(Model model,
                            @ModelAttribute GenerateRuleDTO ruleId) {
        List<Long> ruleIds = Arrays.asList(ruleId.getRuleIds());
        List<String> queries = targetDatabaseService.generateQueries(ruleIds, true);

        model.addAttribute(ERROR_FIELD, ruleIds.size() != queries.size());
        model.addAttribute("test", false);
        model.addAttribute("queries", queries);
        model.addAttribute(SELECTED_RULES_FIELD, ruleId);
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
        List<String> strings = new ArrayList<>();
        properties.put("name", rule.getName());
        properties.put(ERROR_FIELD, rule.getErrorMessage());
        properties.put("description", rule.getDescription());
        if (rule.getOperator() != null) {
            properties.put(OPERATOR_FIELD, rule.getOperator().getId());
        }
        for (Value value : rule.getValues()) {
            if (value.getType().equalsIgnoreCase("COLUMN")) {
                properties.put(value.getLabel(), value.getColumn().getId());
            } else if (value.getPosition().endsWith("_list")) {
                strings.add(value.getField());
            } else {
                properties.put(value.getLabel(), value);
            }
        }
        RuleDTO ruleDTO = new RuleDTO();
        ruleDTO.setProperties(properties);
        ruleDTO.setList(strings);
        return ruleDTO;
    }

    private BusinessRule toBusinessRule(RuleDTO ruleDTO, DbTable table, BusinessRuleType ruleType, Template template) {
        Map<String, Object> ruleComponents = ruleDTO.getProperties();

        BusinessRuleBuilderInterface businessRuleBuilder = BusinessRule.getBuilder()
                .setName((String) ruleComponents.get("name"))
                .setErrorMessage((String) ruleComponents.get(ERROR_FIELD))
                .addTable(table)
                .setType(ruleType)
                .setDescription((String) ruleComponents.get("description"));

        if (ruleComponents.containsKey(OPERATOR_FIELD)) {
            businessRuleBuilder.setOperator(ruleTypeService.getOperator(Long.parseLong((String) ruleComponents.get(OPERATOR_FIELD))));
        }

        if (ruleComponents.containsKey(TABLE_FIELD)) {
            businessRuleBuilder.addTable(targetDatabaseService.getTableById(Long.parseLong((String) ruleComponents.get(TABLE_FIELD))));
        }

        template.getAttributes()
                .entrySet()
                .stream()
                .parallel()
                .forEach(e -> {
                    if (e.getValue().equals("column")) {
                        businessRuleBuilder.addValue(new Value(getPosition(e.getKey(), e.getValue()), targetDatabaseService.getColumnById(Long.parseLong((String) ruleComponents.get(e.getKey())))));
                    } else if (e.getValue().equals("columno")) {
                        businessRuleBuilder.addValue(new Value(getPosition(e.getKey(), e.getValue()), targetDatabaseService.getTableById(Long.parseLong((String) ruleComponents.get("table"))).getColumn((String) ruleComponents.get(e.getKey()))));
                    } else if (e.getValue().equals("list")) {
                        List<String> strings = ruleDTO.getList();
                        for (String listItem : strings) {
                            businessRuleBuilder.addValue(new Value(listItem, "list", getPosition(e.getKey(), e.getValue())));
                        }
                    } else if (!e.getValue().equals(OPERATOR_FIELD) && !e.getValue().equals(TABLE_FIELD)) {
                        businessRuleBuilder.addValue(new Value((String) ruleComponents.get(e.getKey()), e.getValue(), getPosition(e.getKey(), e.getValue())));
                    }
                });
        return businessRuleBuilder.build();
    }

    private String getPosition(String label, String type) {
        return String.format("%s_%s", label, type);
    }

}
