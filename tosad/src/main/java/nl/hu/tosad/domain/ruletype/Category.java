package nl.hu.tosad.domain.ruletype;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @Column(nullable = false, name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<BusinessRuleType> businessRuleType;

    public Category() {
    }

    public Category(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BusinessRuleType> getBusinessRuleType() {
        return businessRuleType;
    }

    public void setBusinessRuleType(List<BusinessRuleType> businessRuleType) {
        this.businessRuleType = businessRuleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
