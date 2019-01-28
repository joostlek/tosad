package nl.hu.tosad.webserver.target_database.presentation;

import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.webserver.target_database.service.TargetDatabaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("database")
public class TargetDatabaseController {
    private final TargetDatabaseServiceInterface targetDatabaseService;

    @Autowired
    public TargetDatabaseController(TargetDatabaseServiceInterface targetDatabaseService) {
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

    @GetMapping("/database/create")
    public String dialectList(
            @RequestParam(value = "error", required = false) String errorMessage,
            Model model){
        model.addAttribute("newDatabase", new NewDatabaseDTO());
        model.addAttribute("dialects", targetDatabaseService.getAllDialects());
        model.addAttribute("error", errorMessage);
        return "target-database/create-db";
    }

    @PostMapping("/database/create")
    public RedirectView newDb(
            @ModelAttribute NewDatabaseDTO newDatabase) {
        Long dialectId = newDatabase.getDialectId();
        Dialect dialect = targetDatabaseService.getDialectById(dialectId);
        Database db = new Database(newDatabase.getName(), newDatabase.getJdbcUrl(), newDatabase.getUsername(), newDatabase.getPassword());
        db.setDialect(dialect);
        targetDatabaseService.saveDatabase(db);
        return new RedirectView("/database");
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
