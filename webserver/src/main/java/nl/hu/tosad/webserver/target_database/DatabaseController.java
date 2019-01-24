package nl.hu.tosad.webserver.target_database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class DatabaseController {
    @Autowired
    DatabaseService databaseService;

    @GetMapping("/databases")
    public String databaseList(Model model) {
        model.addAttribute("databases", databaseService.getAllDatabases());
        return "dbList";
    }

    @GetMapping("/AddDB")
    public String saveDbForm(Model model){
//        model.addAttribute("database", databaseService.());
        return"addDb";
    }

//    @PostMapping("/AddDatabase")
//    public String  saveDb(Model model){
//
//    }


}
