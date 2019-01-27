package nl.hu.tosad.webserver.ruletype.data;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.ruletype.Template;
import nl.hu.tosad.domain.target_database.Dialect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Long> {

    List<Template> findAll();

    List<Template> findByBusinessRuleType(BusinessRuleType br);

    List<Template> findAllByDialect(Dialect dialect);
}
