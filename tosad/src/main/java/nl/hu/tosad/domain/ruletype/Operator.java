package nl.hu.tosad.domain.ruletype;

import nl.hu.tosad.domain.rule.BusinessRule;

import javax.persistence.*;
import java.util.List;

@Entity
public class Operator {

    public int id;
    public String sign;

    @ManyToMany
    public List<BusinessRuleType> businesRuleTypes;

    @ManyToMany
    public List<BusinessRule> businessRules;

    public Operator(int id, String sign) {
        this.id = id;
        this.sign = sign;
    }

    public int getId() {
        return id;
    }

    public String getSign() {
        return sign;
    }

    public List<BusinessRuleType> getBusinesRuleTypes() {
        return businesRuleTypes;
    }

    public List<BusinessRule> getBusinessRules() {
        return businessRules;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setBusinesRuleTypes(List<BusinessRuleType> businesRuleTypes) {
        this.businesRuleTypes = businesRuleTypes;
    }

    public void setBusinessRules(List<BusinessRule> businessRules) {
        this.businessRules = businessRules;
    }
}
