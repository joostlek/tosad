package nl.hu.tosad.entities.domain;

import nl.hu.tosad.entities.BusinessRuleTypes;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = BusinessRuleTypes.Values.ATTRIBUTE_RANGE_RULE)
public class AttributeRangeRule extends BusinessRule {
    @Column(name = "range")
    private String range;

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }


}
