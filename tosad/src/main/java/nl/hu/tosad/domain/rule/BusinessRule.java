package nl.hu.tosad.domain.rule;



import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;

import java.util.List;

public class BusinessRule {
    private int code;
    private String name;
    private String explanation;
    private String errorMessage;
    private List<Value> values;
    private List<DbColumn> dbColumns;
    private List<DbTable> dbTables;



    public BusinessRule(int code, String name, String explanation, String errorMessage) {
        this.code = code;
        this.name = name;
        this.explanation = explanation;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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
        this.values.add(value);
    }

    public List<DbColumn> getDbColumns() {
        return dbColumns;
    }

    public void setDbColumns(List<DbColumn> dbColumns) {
        this.dbColumns = dbColumns;
    }

    public void addDbColumn(DbColumn dbcolumn) {
        this.dbColumns.add(dbcolumn);
    }

    public List<DbTable> getDbTables() {
        return dbTables;
    }

    public void setDbTables(List<DbTable> dbTables) {
        this.dbTables = dbTables;
    }

    public void addDbTable(DbTable dbtable) {
        this.dbTables.add(dbtable);
    }




}
