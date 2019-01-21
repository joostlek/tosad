package nl.hu.tosad.domain.rule;


import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Operator;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;

import javax.persistence.*;
import java.util.List;

@Entity
public class BusinessRule {

    @Id
    @SequenceGenerator(name = "business_rule_id_generator", sequenceName = "br_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_rule_id_generator")
    private int code;

    private String name;

    private String description;

    private String errorMessage;

    private BusinessRuleType businessRuleType;

    private Operator operator;

    @OneToMany(mappedBy = "businessRule")
    private List<Value> values;

    private List<DbColumn> dbColumns;

    private List<DbTable> dbTables;

    public BusinessRule(String name, String description, String errorMessage, BusinessRuleType businessRuleType, Operator operator) {
        this.name = name;
        this.description = description;
        this.errorMessage = errorMessage;
        this.businessRuleType = businessRuleType;
        this.operator = operator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public void addValue(Value value) {
        values.add(value);
    }

    public List<DbColumn> getDbColumns() {
        return dbColumns;
    }

    public void setDbColumns(List<DbColumn> dbColumns) {
        this.dbColumns = dbColumns;
    }

    public void addDbColumn(DbColumn dbcolumn) {
        dbColumns.add(dbcolumn);
    }

    public List<DbTable> getDbTables() {
        return dbTables;
    }

    public void setDbTables(List<DbTable> dbTables) {
        this.dbTables = dbTables;
    }

    public void addDbTable(DbTable dbtable) {
        dbTables.add(dbtable);
    }

    public BusinessRuleType getBusinessRuleType() {
        return businessRuleType;
    }

    public void setBusinessRuleType(BusinessRuleType businessRuleType) {
        this.businessRuleType = businessRuleType;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }


}
