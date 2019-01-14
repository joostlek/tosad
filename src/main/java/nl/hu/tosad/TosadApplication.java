package nl.hu.tosad;

import nl.hu.tosad.entities.AttributeRangeRule;
import nl.hu.tosad.entities.BusinessRule;
import nl.hu.tosad.services.BusinessRuleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class TosadApplication implements CommandLineRunner {

    @Autowired
    private BusinessRuleServiceInterface businessRuleService;

    public static void main(String[] args) {
        SpringApplication.run(TosadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BusinessRule businessRule = new AttributeRangeRule();
        businessRule.setName("asd");
        businessRuleService.saveBusinessRule(businessRule);
    }
}

