package nl.hu.tosad.domain.entities.domain;

import nl.hu.tosad.domain.entities.BusinessRuleTypes;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.util.Arrays;
import java.util.List;

@Entity
@DiscriminatorValue(value = BusinessRuleTypes.Values.ATTRIBUTE_RANGE_RULE)
public class AttributeRangeRule extends BusinessRule {

    public static final List<Operator> POSSIBLE_OPERATORS = Arrays.asList(Operator.IN_BETWEEN, Operator.NOT_IN_BETWEEN);

    @Enumerated
    private Operator operator;

    @Column(name = "range_start")
    private int rangeStart;

    @Column(name = "range_end")
    private int rangeEnd;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public int getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(int rangeStart) {
        this.rangeStart = rangeStart;
    }

    public int getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(int rangeEnd) {
        this.rangeEnd = rangeEnd;
    }
}