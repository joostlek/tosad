package nl.hu.tosad.entities.domain.database;

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
}
