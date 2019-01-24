package nl.hu.tosad.generator.rule.service;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class BusinessRuleIdAdapter implements BusinessRuleAdapterInterface {

    private BusinessRuleServiceInterface businessRuleServiceInterface;

    public BusinessRuleIdAdapter(BusinessRuleServiceInterface businessRuleServiceInterface) {
        this.businessRuleServiceInterface = businessRuleServiceInterface;
    }

    @Override
    public String runGenerator(String json, boolean wet) {
        Gson gson = new Gson();
        Long[] longArray = gson.fromJson(json, Long[].class);
        List<Long> businessRuleIds = Arrays.asList(longArray);

        if (wet) {
            return gson.toJson(businessRuleServiceInterface.convertBusinessRulesDry(businessRuleIds));
        } else {
            return gson.toJson(businessRuleServiceInterface.convertBusinessRulesWet(businessRuleIds));
        }
    }
}
