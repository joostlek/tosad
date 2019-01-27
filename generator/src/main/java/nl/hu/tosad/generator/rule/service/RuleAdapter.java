package nl.hu.tosad.generator.rule.service;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class RuleAdapter implements RuleAdapterInterface {

    private RuleServiceInterface ruleServiceInterface;

    public RuleAdapter(RuleServiceInterface ruleServiceInterface) {
        this.ruleServiceInterface = ruleServiceInterface;
    }

    @Override
    public String runGenerator(String json, boolean wet) {
        Gson gson = new Gson();
        Long[] longArray = gson.fromJson(json, Long[].class);
        List<Long> businessRuleIds = Arrays.asList(longArray);

        if (wet) {
            return gson.toJson(ruleServiceInterface.convertBusinessRulesWet(businessRuleIds));
        } else {
            return gson.toJson(ruleServiceInterface.convertBusinessRulesDry(businessRuleIds));
        }
    }
}
