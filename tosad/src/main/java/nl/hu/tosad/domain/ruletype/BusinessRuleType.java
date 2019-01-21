package nl.hu.tosad.domain.ruletype;

import nl.hu.tosad.domain.rule.BusinessRule;

import javax.persistence.*;
import java.util.List;

@Entity
public class BusinessRuleType {

    @Id
    @Column(nullable = false)
    private String code;

    private String name;

    @OneToMany(mappedBy = "businessRuleType")
    private List<Template> template;

    @ManyToOne
    @JoinColumn(name = "fk_brt_category")
    private Category category;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "BRT_Operator",
            joinColumns = {@JoinColumn(name = "operator_id")},
            inverseJoinColumns = {@JoinColumn(name = "business_rule_type_id")}
    )
    private List<Operator> availableOperators;

    @OneToMany(mappedBy = "businessRuleType")
    private List<BusinessRule> businessRules;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Operator> getAvailableOperators() {
        return availableOperators;
    }

    public void setAvailableOperators(List<Operator> availableOperators) {
        this.availableOperators = availableOperators;
    }

    public List<BusinessRule> getBusinessRules() {
        return businessRules;
    }

    public void setBusinessRules(List<BusinessRule> businessRules) {
        this.businessRules = businessRules;
    }
}
