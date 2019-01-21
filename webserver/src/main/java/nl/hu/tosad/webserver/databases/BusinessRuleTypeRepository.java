package nl.hu.tosad.webserver.databases;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRuleTypeRepository extends CrudRepository<BusinessRuleType, Long> {
}
