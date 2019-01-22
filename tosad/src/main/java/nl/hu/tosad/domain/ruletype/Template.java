package nl.hu.tosad.domain.ruletype;

import nl.hu.tosad.domain.target_database.Dialect;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Template {
    @Id
    @SequenceGenerator(name = "template_id_generator", sequenceName = "temp_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "template_id_generator")
    private Long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "fk_template_dialect")
    private Dialect dialect;

    @ManyToOne
    @JoinColumn(name = "fk_template_type")
    private BusinessRuleType businessRuleType;

    public Template() {
    }

    public Template(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getAttributes() {
        Pattern pattern = Pattern.compile("(<((.*?)_(.*?))(;.*?)?>)");
        Matcher matcher = pattern.matcher(text);
        Map<String, String> templateAttributes = new HashMap<>();
        while (matcher.find()) {
            templateAttributes.put(matcher.group(3), matcher.group(4));
        }
        return templateAttributes;
    }

    public List<String> getKeys() {
        Pattern pattern = Pattern.compile("(<((.*?)_(.*?))(;.*?)?>)");
        Matcher matcher = pattern.matcher(text);
        List<String> keys = new ArrayList<>();
        while (matcher.find()) {
            keys.add(matcher.group(2));
        }
        return keys;
    }

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public BusinessRuleType getBusinessRuleType() {
        return businessRuleType;
    }

    public void setBusinessRuleType(BusinessRuleType businessRuleType) {
        this.businessRuleType = businessRuleType;
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dialect=" + dialect +
                ", businessRuleType=" + businessRuleType +
                '}';
    }
}
