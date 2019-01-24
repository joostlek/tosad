package nl.hu.tosad.generator.target_database.data;

import nl.hu.tosad.domain.rule.BusinessRule;
import nl.hu.tosad.domain.target_database.Database;

public interface TargetDatabaseRepositoryInterface {
    Database findDatabaseByBusinessRule(BusinessRule businessRule);
}
