package nl.hu.tosad.domain.ruletype;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    private String code;

    private String name;

    @ManyToOne
    private List<BusinessRuleType> businessRuleType;

    public Category(String code, String name) {
        this.code = code;
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public List<BusinessRuleType> getBusinessRuleType() {
        return businessRuleType;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBusinessRuleType(List<BusinessRuleType> businessRuleType) {
        this.businessRuleType = businessRuleType;
    }
}
