package nl.hu.tosad.webserver.target_database;

import nl.hu.tosad.domain.target_database.DbColumn;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends CrudRepository<DbColumn, Long> {
}
