package nl.hu.tosad.generator.rule.communication;

import nl.hu.tosad.generator.Main;
import nl.hu.tosad.generator.rule.service.RuleAdapter;
import nl.hu.tosad.generator.rule.service.RuleAdapterInterface;
import nl.hu.tosad.generator.rule.service.RuleService;
import nl.hu.tosad.generator.rule.service.RuleServiceInterface;

import java.io.IOException;

public class ConnectionController implements ConnectionControllerInterface {

    private RuleServiceInterface businessRuleService = new RuleService();
    private RuleAdapterInterface businessRuleAdapter = new RuleAdapter(businessRuleService);

    @Override
    public void runController() throws IOException {
        new ConnectionReceiver(Main.PORT_WET, new RuleCommand(true, businessRuleAdapter)).start();
        new ConnectionReceiver(Main.PORT_DRY, new RuleCommand(false, businessRuleAdapter)).start();
    }

}
