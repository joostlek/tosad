package nl.hu.tosad.domain.rule;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Operator;
import nl.hu.tosad.domain.target_database.Database;
import nl.hu.tosad.domain.target_database.DbColumn;
import nl.hu.tosad.domain.target_database.DbTable;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BUSINESS_RULE")
public class BusinessRule {

    @Id
    @SequenceGenerator(name = "business_rule_id_generator", sequenceName = "br_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_rule_id_generator")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;

    @ManyToOne
    @JoinColumn(name = "fk_brt_bt")
    private BusinessRuleType businessRuleType;

    @ManyToOne
    @JoinColumn(name = "fk_operator_br")
    private Operator operator;

    @OneToMany(mappedBy = "businessRule", cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Value> values;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Column_Rule",
            joinColumns = {@JoinColumn(name = "column_id")},
            inverseJoinColumns = {@JoinColumn(name = "business_rule_2_id")}
    )
    private List<DbColumn> columns;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "Table_Rule",
            joinColumns = {@JoinColumn(name = "table_id")},
            inverseJoinColumns = {@JoinColumn(name = "business_rule_1_id")}
    )
    private List<DbTable> tables;

    public static BusinessRuleBuilderInterface getBuilder() {
        return new BusinessRuleBuilder();
    }

    public BusinessRule() {
        this.values = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.columns = new ArrayList<>();
    }

    public BusinessRule(String name, String description, String errorMessage, BusinessRuleType businessRuleType) {
        this.name = name;
        this.description = description;
        this.errorMessage = errorMessage;
        this.businessRuleType = businessRuleType;
        this.columns = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTriggerName() {
        String res = "BRG_" + this.getDatabase().getTriggerName() + "_" + this.businessRuleType.getTriggerName() + "_" + id;
        return res.toUpperCase();
    }

    public Database getDatabase() {
        Database database = null;
        if (this.getColumns() != null && this.getColumns().size() != 0) {
            database = this.getColumns().get(0).getTable().getDatabase();
        } else if (this.getTables() != null && this.getTables().size() != 0) {
            database = this.getTables().get(0).getDatabase();
        }
        return database;
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

    @Override
    public String toString() {
        return "BusinessRule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", businessRuleType=" + businessRuleType +
                ", operator=" + operator +
                ", values=" + values +
                ", columns=" + columns +
                ", tables=" + tables +
                '}';
    }
}
