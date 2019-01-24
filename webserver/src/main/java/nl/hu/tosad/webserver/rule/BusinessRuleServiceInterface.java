package nl.hu.tosad.webserver.rule;

        import nl.hu.tosad.domain.rule.BusinessRule;

        import java.util.List;

public interface BusinessRuleServiceInterface {
    BusinessRule getBusinessRuleById(Long id);

    BusinessRule saveBusinessRule(BusinessRule businessRule);

    List<BusinessRule> getAllBusinessRulesByDb(Long dbName);

    List<BusinessRule> getAllBusinessRules();
}
