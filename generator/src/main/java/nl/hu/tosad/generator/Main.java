package nl.hu.tosad.generator;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.Value;
import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Category;
import nl.hu.tosad.domain.ruletype.Operator;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;
import nl.hu.tosad.domain.target_database.Dialect;
import nl.hu.tosad.generator.rule.BusinessRuleRepository;
import nl.hu.tosad.generator.rule.BusinessRuleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        ST stringTemplate = new ST("<kek> <lol; null=\"asd\">");
//        stringTemplate.add("lol", "asdasd");
//        System.out.println(stringTemplate.getAttributes().values());
//        System.out.println(stringTemplate.getAttributes());
//        String template = "asdasd <value-value> asd <asd-asd>";

//        Pattern wholePattern = Pattern.compile("")
//
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("attributes_lov", Arrays.asList("asd", "dsa"));
//        attributes.put("column_column", "");
//
//        Template template = new Template("SELECT <attributes_lov; separator=\",\"> FROM <column_column>");
//
//        ST stringTemplate = new ST(template.getText());
//        for (String key : template.getKeys()) {
//            stringTemplate.add(key, attributes.get(key));
//        }
//        System.out.println(stringTemplate.render());
//
//        Category category = new Category("CONS", "Static ");
//
//        Database database = new Database("asdasd", "", "", 1, "", "");
//        DbTable table = new DbTable("PRODUCT", database);
//        DbColumn column = new DbColumn("id", "int", table);
//
//        BusinessRuleType businessRuleType = new BusinessRuleType("ARNG", "Attribute Range Rule");
//        businessRuleType.setCategory(category);
//        Dialect dialect = new Dialect("Oracle");
//        Template template1 = new Template("<name> asdasd <column_column> asd <attribute_value>");
//        template1.setDialect(dialect);
//        businessRuleType.setTemplates(Collections.singletonList(template1));
//
//        List<Value> values = Arrays.asList(new Value("age", "column", "column_column"),
//                new Value("123", "int", "attribute_value"));
//
//        BusinessRule businessRule = new BusinessRule("", "", "", businessRuleType);
//        businessRule.setValues(values);
//        businessRule.setColumns(Collections.singletonList(column));
//
//        System.out.println(template1.getKeys());
//        System.out.println(template1.getAttributes());
//
//        Template template2 = businessRuleType.getTemplate(dialect);
//        ST stringTemplate1 = new ST(template2.getText());
//        stringTemplate1.add("name", businessRule.getTriggerName());
//        for (Value value : businessRule.getValues()) {
//            stringTemplate1.add(value.getPosition(), value);
//        }
//        System.out.println(stringTemplate1.render());
//        System.out.println(businessRule.getTriggerName());



        Database database = new Database("Ondora", "jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC18", "", 1, "doraexplorer", "Boots");
        DbTable dbTable = new DbTable("SEASON", database);
        DbColumn id = new DbColumn("ID", "int", dbTable);
        DbColumn name = new DbColumn("NAME:", "STRING", dbTable);

        Dialect dialect = new Dialect("Oracle");
        database.setDialect(dialect);

        Template template = new Template("CREATE OR REPLACE TRIGGER <name>\n" +
                "  before INSERT OR UPDATE ON <table>\n" +
                "  FOR EACH ROW\n" +
                "declare\n" +
                "  e_exception exception;\n" +
                "begin\n" +
                "  if (:NEW.<column> <operator> <rngstart_int> AND <rngend_int>) THEN\n" +
                "    raise e_exception;\n" +
                "  END IF;\n" +
                "\n" +
                "  exception\n" +
                "  when e_exception then\n" +
                "    raise_application_error(-20000, '<error>');\n" +
                "end;");
        template.setDialect(dialect);

        Category category = new Category("STC", "Static");
        BusinessRuleType businessRuleType = new BusinessRuleType("ARNG", "Attribute Range Rule");

        businessRuleType.setCategory(category);
        businessRuleType.setTemplates(Collections.singletonList(template));

        List<Value> values = new ArrayList<>();
        Value value1 = new Value("12", "int", "rngstart_int");
        Value value2 = new Value("123", "int", "rngend_int");
        values.add(value1);
        values.add(value2);

        Operator operator = new Operator(1L, "BETWEEN");

        BusinessRule businessRule = new BusinessRule("haha", "kech", "oepsie woepsie", businessRuleType);
        businessRule.setColumns(Collections.singletonList(id));
        businessRule.setTables(Collections.singletonList(dbTable));
        businessRule.setValues(values);
        businessRule.setOperator(operator);
        businessRule.setId(1L);

        BusinessRuleRepository repository = new BusinessRuleRepository() {
            @Override
            public List<BusinessRule> getBusinessRuleByList(List<Long> ids) {
                return Collections.singletonList(businessRule);
            }

            @Override
            public BusinessRule getBusinessRuleById(Long id) {
                return null;
            }
        };
        BusinessRuleService businessRuleService = new BusinessRuleService(repository);
        System.out.println(businessRuleService.convertBusinessRulesDry(new ArrayList<>()));
    }
}
