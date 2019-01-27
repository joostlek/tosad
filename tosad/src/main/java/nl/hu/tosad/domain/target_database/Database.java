package nl.hu.tosad.domain.target_database;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "db_database")
public class Database {

    @Id
    @SequenceGenerator(name = "database_id_generator", sequenceName = "db_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "database_id_generator")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "JDBC_URL")
    private String jdbcUrl;

    @Column(name = "DATABASE_NAME")
    private String databaseName;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "database")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DbTable> tables;

    @ManyToOne
    @JoinColumn(name = "fk_db_dialect")
    private Dialect dialect;

    public Database() {
    }

    public Database(String name, String jdbcUrl, String databaseName, String username, String password) {
        this.name = name;
        this.jdbcUrl = jdbcUrl;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
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

    public String getTriggerName() {
        return this.name.substring(0, 4);
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DbTable> getTables() {
        return tables;
    }

    public void setTables(List<DbTable> tables) {
        this.tables = tables;
    }

    public boolean hasTable(String tableName) {
        for (DbTable table : this.tables) {
            if (table.getName().equals(tableName))
                return true;
        }
        return false;
    }

    public void addTable(DbTable table) {
        this.tables.add(table);
    }

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }
}
