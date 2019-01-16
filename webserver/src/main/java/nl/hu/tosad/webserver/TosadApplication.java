package nl.hu.tosad.webserver;

import nl.hu.tosad.domain.entities.domain.AttributeRangeRule;
import nl.hu.tosad.domain.entities.domain.database.Database;
import nl.hu.tosad.domain.entities.domain.database.DbColumn;
import nl.hu.tosad.domain.entities.domain.database.DbTable;
import nl.hu.tosad.webserver.repositories.ColumnRepository;
import nl.hu.tosad.webserver.repositories.DatabaseRepository;
import nl.hu.tosad.webserver.repositories.TableRepository;
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
        AttributeRangeRule businessRule = new AttributeRangeRule();
        Database database = databaseRepository.save(new Database());
        DbTable dbTable = tableRepository.save(new DbTable("asd", database));
        DbColumn dbColumn = columnRepository.save(new DbColumn("asd", dbTable));
        businessRule.setColumn(dbColumn);
        businessRule.setName("asd");
        businessRuleService.saveBusinessRule(businessRule);
    }
}

