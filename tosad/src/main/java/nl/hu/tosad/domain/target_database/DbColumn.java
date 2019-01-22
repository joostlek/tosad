package nl.hu.tosad.domain.target_database;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.Value;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DB_COLUMN")
public class DbColumn {
    @Id
    @SequenceGenerator(name = "column_id_generator", sequenceName = "dbc_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "column_id_generator")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @ManyToOne
    @JoinColumn(name = "fk_table")
    private DbTable table;

    @ManyToMany(mappedBy = "columns")
    private List<BusinessRule> businessRules;

    @OneToMany(mappedBy = "column")
    private List<Value> values;

    public DbColumn() {
    }

    public DbColumn(String name, String type, DbTable table) {
        this.name = name;
        this.type = type;
        this.table = table;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DbTable getTable() {
        return table;
    }

    public void setTable(DbTable table) {
        this.table = table;
    }

    public List<BusinessRule> getBusinessRules() {
        return businessRules;
    }

    public void setBusinessRules(List<BusinessRule> businessRules) {
        this.businessRules = businessRules;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
