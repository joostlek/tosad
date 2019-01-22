package nl.hu.tosad.webserver;

//import nl.hu.tosad.webserver.rule.BusinessRuleServiceInterface;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.webserver.rule.BusinessRuleServiceInterface;
import nl.hu.tosad.webserver.ruletype.TemplateRepository;
import nl.hu.tosad.webserver.target_database.ColumnRepository;
import nl.hu.tosad.webserver.target_database.DatabaseRepository;
import nl.hu.tosad.webserver.target_database.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication()
@EntityScan("nl.hu.tosad.domain")
public class TosadApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TosadApplication.class);

    @Autowired
    private BusinessRuleServiceInterface businessRuleService;

    @Autowired
    private DatabaseRepository databaseRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TemplateRepository templateRepository;

    public static void main(String[] args) {
        SpringApplication.run(TosadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Alle templates in db:");
        log.info("----------------------");
        for (Template t : templateRepository.findAll()) {
            log.info(t.toString());
            log.info("Alle waardes in template:");
            log.info("----------------------");
            log.info(String.valueOf(t.getAttributes()));
        }
    }
}

