package nl.hu.tosad.domain.target_database;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DIALECT")
public class Dialect {
    @Id
    @SequenceGenerator(name = "dialect_id_generator", sequenceName = "db_dialect_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dialect_id_generator")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "dialect")
    private List<Database> databases;

    public Dialect() {
    }

    public Dialect(String name) {
        this.name = name;
        this.databases = new ArrayList<>();
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

    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Database> databases) {
        this.databases = databases;
    }
}
