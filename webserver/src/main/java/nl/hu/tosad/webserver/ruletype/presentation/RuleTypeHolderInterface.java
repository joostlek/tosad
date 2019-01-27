package nl.hu.tosad.webserver.ruletype.presentation;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.target_database.DbTable;

public interface RuleTypeHolderInterface {
    public BusinessRuleType getBusinessRuleType();

    void setBusinessRuleType(BusinessRuleType businessRuleType);

    DbTable getTable();

    void setTable(DbTable table);
}
