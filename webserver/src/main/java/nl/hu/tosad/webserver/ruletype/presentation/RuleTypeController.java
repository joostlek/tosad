package nl.hu.tosad.webserver.ruletype.presentation;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.webserver.ruletype.service.RuleTypeServiceInterface;
import nl.hu.tosad.webserver.target_database.presentation.DatabaseHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes({"database", "ruleType"})
public class RuleTypeController {

    private final RuleTypeServiceInterface ruleTypeService;

    @Autowired
    public RuleTypeController(RuleTypeServiceInterface ruleTypeService) {
        this.ruleTypeService = ruleTypeService;
    }

    @ModelAttribute("ruleType")
    public RuleTypeHolderInterface ruleTypeHolder() {
        return new RuleTypeHolder();
    }

    @GetMapping("/rules/type")
    public String chooseType(Model model,
                             @ModelAttribute("database") DatabaseHolder databaseHolder) {
        Dialect dialect = databaseHolder.getDatabase().getDialect();
        model.addAttribute("newRuleType", new RuleTypeDTO());
        model.addAttribute("templates", ruleTypeService.getTemplatesByDialect(dialect));
        return "rule-type/choose-type";
    }

    @PostMapping("/rules/type")
    public RedirectView setType(
            @ModelAttribute RuleTypeDTO ruleTypeDTO,
            @ModelAttribute("database") DatabaseHolder databaseHolder,
            @ModelAttribute("ruleType") RuleTypeHolder ruleTypeHolder,
            RedirectAttributes attributes) {
        String ruleTypeCode = ruleTypeDTO.getTypeCode();
        BusinessRuleType businessRuleType = ruleTypeService.getBusinessRuleTypeByCode(ruleTypeCode);
        ruleTypeHolder.setBusinessRuleType(businessRuleType);

        attributes.addFlashAttribute("ruleType", ruleTypeHolder);
        attributes.addFlashAttribute("database", databaseHolder);
        return new RedirectView("/rules/add");
    }
}
