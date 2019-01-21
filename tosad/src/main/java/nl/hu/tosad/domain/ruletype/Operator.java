package nl.hu.tosad.domain.ruletype;

import nl.hu.tosad.domain.rule.BusinessRule;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Operator {

    private int id;
    private String sign;

    @ManyToMany
    private List<BusinessRuleType> businesRuleTypes;

    @ManyToMany
    private List<BusinessRule> businessRules;

    public Operator(int id, String sign) {
        this.id = id;
        this.sign = sign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<BusinessRuleType> getBusinesRuleTypes() {
        return businesRuleTypes;
    }

    public void setBusinesRuleTypes(List<BusinessRuleType> businesRuleTypes) {
        this.businesRuleTypes = businesRuleTypes;
    }

    public List<BusinessRule> getBusinessRules() {
        return businessRules;
    }

    public void setBusinessRules(List<BusinessRule> businessRules) {
        this.businessRules = businessRules;
    }
}
