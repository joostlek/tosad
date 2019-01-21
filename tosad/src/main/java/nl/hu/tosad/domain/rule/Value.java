package nl.hu.tosad.domain.rule;


import nl.hu.tosad.domain.target_database.DbColumn;

import javax.persistence.*;
import java.util.List;

@Entity
public class Value {
    @Id
    @SequenceGenerator(name = "value_id_generator", sequenceName = "value_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "value_id_generator")
    private Long id;

    private String value;

    private String type;

    private String position;

    @ManyToOne
    @JoinColumn(name = "fk_business_rule")
    private BusinessRule businessRule;

    @OneToMany(mappedBy = "value")
    private List<DbColumn> dbColumns;


    public Value(String value, String type, String position) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessRule getBusinessRule() {
        return businessRule;
    }

    public void setBusinessRule(BusinessRule businessRule) {
        this.businessRule = businessRule;
    }
}
