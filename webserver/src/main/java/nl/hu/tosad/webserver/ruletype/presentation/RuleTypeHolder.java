package nl.hu.tosad.webserver.ruletype.presentation;

import nl.hu.tosad.domain.ruletype.BusinessRuleType;
import nl.hu.tosad.domain.target_database.DbTable;

public class RuleTypeHolder implements RuleTypeHolderInterface {
    private BusinessRuleType businessRuleType;

    private DbTable table;

    @Override
    public BusinessRuleType getBusinessRuleType() {
        return businessRuleType;
    }

    @Override
    public void setBusinessRuleType(BusinessRuleType businessRuleType) {
        this.businessRuleType = businessRuleType;
    }

    @Override
    public DbTable getTable() {
        return table;
    }

    @Override
    public void setTable(DbTable table) {
        this.table = table;
    }
}
