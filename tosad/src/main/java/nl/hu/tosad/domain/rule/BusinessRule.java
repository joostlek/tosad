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
    private Long id;

    private String name;

    private String description;

    private String errorMessage;

    @ManyToOne
    @JoinColumn(name = "fk_brt_bt")
    private BusinessRuleType businessRuleType;

    @ManyToOne
    @JoinColumn(name = "fk_operator_br")
    private Operator operator;

    @OneToMany(mappedBy = "businessRule")
    private List<Value> values;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Column_Rule",
            joinColumns = {@JoinColumn(name = "column_id")},
            inverseJoinColumns = {@JoinColumn(name = "business_rule_2_id")}
    )
    private List<DbColumn> columns;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Table_Rule",
            joinColumns = {@JoinColumn(name = "table_id")},
            inverseJoinColumns = {@JoinColumn(name = "business_rule_1_id")}
    )
    private List<DbTable> tables;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<DbColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DbColumn> columns) {
        this.columns = columns;
    }

    public void addDbColumn(DbColumn dbcolumn) {
        columns.add(dbcolumn);
    }

    public List<DbTable> getTables() {
        return tables;
    }

    public void setTables(List<DbTable> tables) {
        this.tables = tables;
    }

    public void addDbTable(DbTable dbtable) {
        tables.add(dbtable);
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
