package nl.hu.tosad.webserver.rule.presentation;

public class ChosenRuleTypeDTO {

    private String businessRuleTypeCode;

    public String getBusinessRuleTypeCode() {
        return businessRuleTypeCode;
    }

    public void setBusinessRuleTypeCode(String businessRuleTypeCode) {
        this.businessRuleTypeCode = businessRuleTypeCode;
    }

    @Override
    public String toString() {
        return "ChosenRuleTypeDTO{" +
                "businessRuleTypeCode=" + businessRuleTypeCode +
                '}';
    }
}