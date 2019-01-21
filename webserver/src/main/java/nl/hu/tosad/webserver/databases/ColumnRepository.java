package nl.hu.tosad.webserver.databases;

import nl.hu.tosad.domain.target_database.DbColumn;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends CrudRepository<DbColumn, Long> {

}
