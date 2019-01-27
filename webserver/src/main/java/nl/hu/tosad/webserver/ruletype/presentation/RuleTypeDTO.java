package nl.hu.tosad.webserver.ruletype.presentation;

public class RuleTypeDTO {

    private String typeCode;

    private Long tableId;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "RuleTypeDTO{" +
                "typeCode=" + typeCode +
                '}';
    }
}