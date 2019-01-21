package nl.hu.tosad.webserver.business_ruletype;

import nl.hu.tosad.domain.ruletype.Template;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Long> {
}
