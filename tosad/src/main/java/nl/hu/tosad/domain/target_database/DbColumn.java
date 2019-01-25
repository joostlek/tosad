package nl.hu.tosad.domain.target_database;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

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
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "fk_table")
    private DbTable table;

    public DbColumn() {
    }

    public DbColumn(String name, String type) {
        this.name = name;
        this.type = type;
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

    @Override
    public String toString() {
        return this.name;
    }
}
