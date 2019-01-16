package nl.hu.tosad.webserver;

import nl.hu.tosad.domain.entities.domain.AttributeRangeRule;
import nl.hu.tosad.domain.entities.domain.BusinessRule;
import nl.hu.tosad.webserver.services.BusinessRuleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication()
@EntityScan("nl.hu.tosad.domain.entities")
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

