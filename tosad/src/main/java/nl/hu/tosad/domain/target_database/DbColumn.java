package nl.hu.tosad.domain.target_database;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.rule.Value;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "db_column")
public class DbColumn {
    @Id
    @SequenceGenerator(name = "column_id_generator", sequenceName = "dbc_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "column_id_generator")
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_table")
    private DbTable table;

    @ManyToMany(mappedBy = "columns")
    private List<BusinessRule> businessRules;

    @ManyToOne
    @JoinColumn(name = "fk_value")
    private Value value;

    public DbColumn() {
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
}
