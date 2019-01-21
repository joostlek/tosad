package nl.hu.tosad.webserver;

//import nl.hu.tosad.webserver.rule.BusinessRuleServiceInterface;
import nl.hu.tosad.webserver.target_database.ColumnRepository;
import nl.hu.tosad.webserver.target_database.DatabaseRepository;
import nl.hu.tosad.webserver.target_database.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication()
@EntityScan("nl.hu.tosad.domain")
public class TosadApplication implements CommandLineRunner {

//    @Autowired
//    private BusinessRuleServiceInterface businessRuleService;

    @Autowired
    private DatabaseRepository databaseRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private TableRepository tableRepository;


    public static void main(String[] args) {
        SpringApplication.run(TosadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}

