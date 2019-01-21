package nl.hu.tosad.domain.ruletype;

import nl.hu.tosad.domain.rule.BusinessRule;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    public List<Template> getTemplate() {
        return template;
    }

    public void setTemplate(List<Template> template) {
        this.template = template;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Operator> getOperator() {
        return operator;
    }

    public void setOperator(List<Operator> operator) {
        this.operator = operator;
    }

    public List<BusinessRule> getBusinessrule() {
        return businessrule;
    }

    public void setBusinessrule(List<BusinessRule> businessrule) {
        this.businessrule = businessrule;
    }
}
