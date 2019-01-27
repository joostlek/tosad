package nl.hu.tosad.webserver.target_database.presentation;

import nl.hu.tosad.webserver.target_database.service.TargetDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("database")
public class TargetDatabaseController {
    private final TargetDatabaseService targetDatabaseService;

    @Autowired
    public TargetDatabaseController(TargetDatabaseService targetDatabaseService) {
        this.targetDatabaseService = targetDatabaseService;
    }

    @ModelAttribute("database")
    public DatabaseHolderInterface databaseHolder() {
        return new DatabaseHolder();
    }

    @GetMapping("/database")
    public String databaseList(
            @RequestParam(value = "error", required = false) String errorMessage,
            Model model) {
        model.addAttribute("databases", targetDatabaseService.getAllDatabases());
        model.addAttribute("chosenDatabase", new ChosenDatabaseDTO());
        model.addAttribute("error", errorMessage);
        return "target-database/db-list";
    }

    @PostMapping("/database")
    public RedirectView chooseDb(
            @ModelAttribute ChosenDatabaseDTO chosenDatabase,
            @ModelAttribute("database") DatabaseHolder database,
            RedirectAttributes attributes) {
        Long databaseId = chosenDatabase.getDatabaseId();
        if (!targetDatabaseService.databaseExists(databaseId)) {
            return new RedirectView("/database?error=Database+not+found");
        }
        database.setDatabase(targetDatabaseService.getDatabaseById(databaseId));
        attributes.addFlashAttribute("database", database);
        return new RedirectView("/rules");
    }


}
