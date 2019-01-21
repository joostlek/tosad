package nl.hu.tosad.domain.ruletype;

import org.hibernate.dialect.Dialect;

import javax.persistence.*;
import java.util.List;

@Entity
public class Template {

    private String id;
    private String text;

    @ManyToOne
    private Dialect dialect;

    @ManyToOne
    private BusinessRuleType businessRuleType;

    public Template(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Dialect getDialect() {
        return dialect;
    }

    public BusinessRuleType getBusinessRuleType() {
        return businessRuleType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public void setBusinessRuleType(BusinessRuleType businessRuleType) {
        this.businessRuleType = businessRuleType;
    }
}
