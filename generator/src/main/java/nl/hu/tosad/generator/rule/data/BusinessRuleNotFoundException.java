package nl.hu.tosad.generator.rule.data;

public class BusinessRuleNotFoundException extends RuntimeException {
    public BusinessRuleNotFoundException(Long id) {
        super(String.format("Business rule with id %d not found", id));
    }
}
