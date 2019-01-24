package nl.hu.tosad.webserver.ruletype.data;

import nl.hu.tosad.domain.ruletype.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
