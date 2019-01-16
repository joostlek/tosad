package nl.hu.tosad.domain.entities.domain;

public enum Operator {
    IS_EQUAL_TO(Operators.IS_EQUAL_TO),
    IS_GREATER_THAN(Operators.IS_GREATER_THAN),
    IS_GREATER_THAN_OR_EQUAL_TO(Operators.IS_GREATER_THAN_OR_EQUAL_TO),
    IS_SMALLER_THAN(Operators.IS_SMALLER_THAN),
    IS_SMALLER_THAN_OR_EQUAL_TO(Operators.IS_SMALLER_THAN_OR_EQUAL_TO),
    IN_BETWEEN(Operators.IN_BETWEEN),
    NOT_IN_BETWEEN(Operators.NOT_IN_BETWEEN);

    private String operator;

    Operator(String operator) {
        this.operator = operator;
    }

    public String getString() {
        return operator;
    }

    public static class Operators {
        public static final String IS_EQUAL_TO = "=";
        public static final String IS_GREATER_THAN = ">";
        public static final String IS_GREATER_THAN_OR_EQUAL_TO = ">=";
        public static final String IS_SMALLER_THAN = "<";
        public static final String IS_SMALLER_THAN_OR_EQUAL_TO = "<=";
        public static final String IN_BETWEEN = "BETWEEN";
        public static final String NOT_IN_BETWEEN = "NOT";
    }


}
