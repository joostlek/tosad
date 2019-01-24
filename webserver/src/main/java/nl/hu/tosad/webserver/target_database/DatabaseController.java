package nl.hu.tosad.webserver.target_database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DatabaseController {
    @Autowired
    DatabaseService databaseService;

    @GetMapping("/")
    public String databaseList(Model model) {
        model.addAttribute("databases", databaseService.getAllDatabases());
        model.addAttribute("chosenDatabase", new ChosenDatabaseDTO());
        return "dbList";
    }

    @PostMapping("/addDb")
    public String addDb(@ModelAttribute ChosenDatabaseDTO dbn, Model model){

        model.addAttribute("chosenDatabase", dbn);
        return"ruleList";
    }




}
