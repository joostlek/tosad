package nl.hu.tosad.generator;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.Value;
import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Category;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;
import nl.hu.tosad.domain.target_database.Dialect;
import org.stringtemplate.v4.ST;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        ST stringTemplate = new ST("<kek> <lol; null=\"asd\">");
//        stringTemplate.add("lol", "asdasd");
//        System.out.println(stringTemplate.getAttributes().values());
//        System.out.println(stringTemplate.getAttributes());
//        String template = "asdasd <value-value> asd <asd-asd>";

//        Pattern wholePattern = Pattern.compile("")

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("attributes_lov", Arrays.asList("asd", "dsa"));
        attributes.put("column_column", "");

        Template template = new Template("SELECT <attributes_lov; separator=\",\"> FROM <column_column>");

        ST stringTemplate = new ST(template.getText());
        for (String key : template.getKeys()) {
            stringTemplate.add(key, attributes.get(key));
        }
        System.out.println(stringTemplate.render());

        Category category = new Category("CONS", "Static ");

        Database database = new Database("asdasd", "", "", 1, "", "");
        DbTable table = new DbTable("PRODUCT", database);
        DbColumn column = new DbColumn("id", "int", table);

        BusinessRuleType businessRuleType = new BusinessRuleType("ARNG", "Attribute Range Rule");
        businessRuleType.setCategory(category);
        Dialect dialect = new Dialect("Oracle");
        Template template1 = new Template("<name> asdasd <column_column> asd <attribute_value>");
        template1.setDialect(dialect);
        businessRuleType.setTemplates(Collections.singletonList(template1));

        List<Value> values = Arrays.asList(new Value("age", "column", "column_column"),
                new Value("123", "int", "attribute_value"));

        BusinessRule businessRule = new BusinessRule("", "", "", businessRuleType);
        businessRule.setValues(values);
        businessRule.setColumns(Collections.singletonList(column));

        System.out.println(template1.getKeys());
        System.out.println(template1.getAttributes());

        Template template2 = businessRuleType.getTemplate(dialect);
        ST stringTemplate1 = new ST(template2.getText());
        stringTemplate1.add("name", businessRule.getTriggerName());
        for (Value value : businessRule.getValues()) {
            stringTemplate1.add(value.getPosition(), value);
        }
        System.out.println(stringTemplate1.render());
        System.out.println(businessRule.getTriggerName());
    }
}
