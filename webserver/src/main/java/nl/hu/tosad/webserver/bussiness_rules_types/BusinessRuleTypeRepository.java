package nl.hu.tosad.webserver.bussiness_rules_types;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRuleTypeRepository extends CrudRepository<BusinessRuleType, Long> {
}
