package nl.hu.tosad.webserver.target_database.data;

import nl.hu.tosad.domain.target_database.Database;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseRepository extends CrudRepository<Database, Long> {
    List<Database> findAll();
}
