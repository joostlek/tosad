package nl.hu.tosad.webserver.ruletype.presentation;

public class RuleTypeDTO {

    private String typeCode;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public String toString() {
        return "RuleTypeDTO{" +
                "typeCode=" + typeCode +
                '}';
    }
}