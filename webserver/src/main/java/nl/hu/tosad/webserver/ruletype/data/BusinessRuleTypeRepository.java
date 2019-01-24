package nl.hu.tosad.webserver.ruletype.data;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRuleTypeRepository extends CrudRepository<BusinessRuleType, Long> {

    List<BusinessRuleType> findAll();

    BusinessRuleType findBusinessRuleTypeByCode(String code);
}
