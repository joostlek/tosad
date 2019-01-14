package nl.hu.tosad.controllers;

import nl.hu.tosad.services.BusinessRuleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusinessRuleController {
    @Autowired
    BusinessRuleServiceInterface businessRuleService;

    @GetMapping("/")
    public String test(Model model) {
        model.addAttribute("businessRule", businessRuleService.getBusinessRuleById(2L));
        return "test";
    }
}
