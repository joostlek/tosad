package nl.hu.tosad.domain.entities.domain.database;

import javax.persistence.*;

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


}
