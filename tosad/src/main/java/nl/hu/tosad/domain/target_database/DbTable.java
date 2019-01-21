package nl.hu.tosad.domain.target_database;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "db_table")
public class DbTable {
    @Id
    @SequenceGenerator(name = "table_id_generator", sequenceName = "dbt_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_id_generator")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_database")
    private Database database;

    @OneToMany(mappedBy = "table")
    private List<DbColumn> columns;

    public DbTable(String name, Database database) {
        this.name = name;
        this.database = database;
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

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
