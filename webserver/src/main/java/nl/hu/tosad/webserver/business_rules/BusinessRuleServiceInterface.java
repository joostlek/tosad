package nl.hu.tosad.webserver.business_rules;

        import nl.hu.tosad.domain.rule.BusinessRule;

        import java.util.List;

public interface BusinessRuleServiceInterface {
    BusinessRule getBusinessRuleById(Long id);

    BusinessRule saveBusinessRule(BusinessRule businessRule);

    List<BusinessRule> getAllBusinessRules();
}
