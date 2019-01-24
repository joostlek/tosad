package nl.hu.tosad.generator.rule.communication;

import nl.hu.tosad.generator.rule.service.RuleAdapterInterface;

import java.io.IOException;
import java.util.logging.Logger;

public class RuleCommand implements CommandInterface {
    private boolean wet;
    private RuleAdapterInterface adapter;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public RuleCommand(boolean wet, RuleAdapterInterface adapter) {
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
