package nl.hu.tosad.webserver.rule;

public class ChosenRuleTypeDTO {

    private Long businessRuleTypeCode;

    public Long getBusinessRuleTypeCode() {
        return businessRuleTypeCode;
    }

    public void setBusinessRuleTypeCode(Long businessRuleTypeCode) {
        this.businessRuleTypeCode = businessRuleTypeCode;
    }

    @Override
    public String toString() {
        return "ChosenRuleTypeDTO{" +
                "businessRuleTypeCode=" + businessRuleTypeCode +
                '}';
    }
}