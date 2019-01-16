package nl.hu.tosad.domain.entities.domain.dto;

import nl.hu.tosad.domain.entities.domain.AttributeRangeRule;
import nl.hu.tosad.domain.entities.domain.Operator;

import java.util.List;

public class AttributeRangeRuleDTO {
    private List<Operator> operators = AttributeRangeRule.POSSIBLE_OPERATORS;

    private String name;

    private Operator operator;

    private int rangeStart;

    private int rangeEnd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public List<Operator> getPossibleOperators() {
        return this.operators;
    }
}
