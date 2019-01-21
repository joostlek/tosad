package nl.hu.tosad.generator;

import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.DbColumn;
import org.stringtemplate.v4.ST;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        attributes.put("column_column", new DbColumn());

        Template template = new Template("SELECT <attributes_lov; separator=\",\"> FROM <column_column>");

        ST stringTemplate = new ST(template.getText());
        stringTemplate.add("attributes_lov", "asd");
        stringTemplate.add("attributes_lov", "asd");
        stringTemplate.add("attributes_lov", "asd");
        for (String key : template.getKeys()) {
            stringTemplate.add(key, attributes.get(key));
        }
        System.out.println(stringTemplate.render());
    }
}
