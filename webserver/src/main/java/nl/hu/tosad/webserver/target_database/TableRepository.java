package nl.hu.tosad.webserver.target_database;

import nl.hu.tosad.domain.target_database.DbTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends CrudRepository<DbTable, Long> {
}