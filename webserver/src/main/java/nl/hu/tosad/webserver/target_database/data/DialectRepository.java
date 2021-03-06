package nl.hu.tosad.webserver.target_database.data;

import nl.hu.tosad.domain.target_database.Dialect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialectRepository extends CrudRepository<Dialect, Long> {
    List<Dialect> findAll();
}
