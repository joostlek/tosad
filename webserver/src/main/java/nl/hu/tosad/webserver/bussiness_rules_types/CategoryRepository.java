package nl.hu.tosad.webserver.bussiness_rules_types;

import nl.hu.tosad.domain.ruletype.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
