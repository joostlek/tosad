package nl.hu.tosad.repositories;

import nl.hu.tosad.entities.BusinessRule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRuleRepository extends CrudRepository<BusinessRule, Long> {
    Optional<BusinessRule> findById(Long id);
}
