package nl.hu.tosad.webserver.rule;

import nl.hu.tosad.webserver.ruletype.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addType")
    public String addType(Model model) {
        model.addAttribute("type", new ChosenRuleTypeDTO());
        return "fillRule";
    }


}
