package nl.hu.tosad.domain.rule;


import nl.hu.tosad.domain.target_database.DbColumn;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Value {
    private int id;
    private String value;
    private String type;
    private int position;
    private List<DbColumn> dbColumns;


    public Value(String value, String type, int position) {
        this.value = value;
        this.type = type;
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
