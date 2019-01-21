package nl.hu.tosad.domain.ruletype;

import nl.hu.tosad.domain.entities.domain.BusinessRule;

import javax.persistence.*;
import java.util.List;

@Entity
public class BusinessRuleType {
    private String code;

    private String name;

    @OneToMany
    private List<Template> template;

    @OneToMany
    private List<Category> category;

    @ManyToMany
    private List<Operator> operator;

    @ManyToOne
    private List<BusinessRule> businessrule;

    public BusinessRuleType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
