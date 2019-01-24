package nl.hu.tosad.generator.rule.communication;

import nl.hu.tosad.generator.rule.service.BusinessRuleAdapterInterface;

import java.io.IOException;
import java.util.logging.Logger;

public class BusinessRuleCommand implements CommandInterface {
    private boolean wet;
    private BusinessRuleAdapterInterface adapter;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public BusinessRuleCommand(boolean wet, BusinessRuleAdapterInterface adapter) {
        this.wet = wet;
        this.adapter = adapter;
    }

    public String execute(String json) {
        try {
            return adapter.runGenerator(json, wet);
        } catch (IOException e) {
            logger.severe(e.getMessage());
            return "";
        }
    }
}
