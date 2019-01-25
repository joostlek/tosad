package nl.hu.tosad.domain.ruletype;

import nl.hu.tosad.domain.target_database.Dialect;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BUSINESS_RULE_TYPE")
public class BusinessRuleType {

    @Id
    @Column(nullable = false, name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "businessRuleType")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Template> templates;

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

    public BusinessRuleType() {
    }

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

    public String getTriggerName() {
        return category.getCode() + "_" + code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
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

    public Template getTemplate(Dialect dialect) {
        for (Template template : this.templates) {
            if (template.getDialect() == dialect) {
                return template;
            }
        }
        return null;
    }
}
