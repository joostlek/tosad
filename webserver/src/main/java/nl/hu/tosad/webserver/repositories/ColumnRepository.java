package nl.hu.tosad.webserver.repositories;

import nl.hu.tosad.domain.entities.domain.database.DbColumn;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends CrudRepository<DbColumn, Long> {

}
