package nl.hu.tosad.webserver.target_database.presentation;

import nl.hu.tosad.webserver.rule.service.RuleService;
import nl.hu.tosad.webserver.target_database.service.TargetDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TargetDatabaseController {
    @Autowired
    TargetDatabaseService targetDatabaseService;
    @Autowired
    RuleService businessRuleService;

    @GetMapping("/")
    public String databaseList(Model model) {
        model.addAttribute("databases", targetDatabaseService.getAllDatabases());
        model.addAttribute("chosenDatabase", new ChosenDatabaseDTO());
        return "dbList";
    }

    @GetMapping("/addDb")
    public String addDb(@RequestParam(value = "db", required = false) Long dbn, Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute("chosenDatabase", dbn);
        redirectAttributes.addFlashAttribute("chosenDB", dbn);
        model.addAttribute("businessRules", businessRuleService.getAllBusinessRulesByDb(dbn));
        return "ruleList";
    }


}
