package nl.hu.tosad.webserver.repositories;

import nl.hu.tosad.domain.entities.domain.database.Database;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepository extends CrudRepository<Database, Long> {

}
