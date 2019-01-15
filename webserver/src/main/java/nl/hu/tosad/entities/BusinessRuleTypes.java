package nl.hu.tosad.entities;

public enum BusinessRuleTypes {
    ATTRIBUTE_RANGE_RULE(Values.ATTRIBUTE_RANGE_RULE),
    ATTRIBUTE_COMPARE_RULE(Values.ATTRIBUTE_COMPARE_RULE),
    ATTRIBUTE_LIST_RULE(Values.ATTRIBUTE_LIST_RULE),
    ATTRIBUTE_OTHER_RULE(Values.ATTRIBUTE_OTHER_RULE),
    TUPLE_COMPARE_RULE(Values.TUPLE_COMPARE_RULE),
    TUPLE_OTHER_RULE(Values.TUPLE_OTHER_RULE),
    INTER_ENTITY_COMPARE_RULE(Values.INTER_ENTITY_COMPARE_RULE),
    ENTITY_OTHER_RULE(Values.ENTITY_OTHER_RULE),
    MODIFY_RULE(Values.MODIFY_RULE);

    private String value;

    BusinessRuleTypes(String value) {
        this.value = value;
    }

    public static class Values {
        public static final String ATTRIBUTE_RANGE_RULE = "ARNG";
        public static final String ATTRIBUTE_COMPARE_RULE = "ACMP";
        public static final String ATTRIBUTE_LIST_RULE = "ALIS";
        public static final String ATTRIBUTE_OTHER_RULE = "AOTH";
        public static final String TUPLE_COMPARE_RULE = "TCMP";
        public static final String TUPLE_OTHER_RULE = "TOTH";
        public static final String INTER_ENTITY_COMPARE_RULE = "ICMP";
        public static final String ENTITY_OTHER_RULE = "EOTH";
        public static final String MODIFY_RULE = "MODI";
    }
}
