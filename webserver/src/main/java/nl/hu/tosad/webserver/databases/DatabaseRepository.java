package nl.hu.tosad.webserver.databases;

import nl.hu.tosad.domain.target_database.Database;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepository extends CrudRepository<Database, Long> {

}
