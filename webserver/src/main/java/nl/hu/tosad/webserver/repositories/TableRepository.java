package nl.hu.tosad.webserver.repositories;

import nl.hu.tosad.domain.entities.domain.database.DbTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends CrudRepository<DbTable, Long> {
}
