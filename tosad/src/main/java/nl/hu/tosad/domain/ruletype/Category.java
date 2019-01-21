package nl.hu.tosad.domain.ruletype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Category {
    @Id
    @Column(nullable = false)
    private String code;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<BusinessRuleType> businessRuleType;

    public Category(String code, String name) {
        this.code = code;
        this.name = name;
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
