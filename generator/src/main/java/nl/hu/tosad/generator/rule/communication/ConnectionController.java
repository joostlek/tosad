package nl.hu.tosad.generator.rule.communication;

import nl.hu.tosad.generator.rule.service.BusinessRuleAdapterInterface;
import nl.hu.tosad.generator.rule.service.BusinessRuleIdAdapter;
import nl.hu.tosad.generator.rule.service.BusinessRuleService;
import nl.hu.tosad.generator.rule.service.BusinessRuleServiceInterface;

import java.io.IOException;

public class ConnectionController implements ConnectionControllerInterface {

    private BusinessRuleServiceInterface businessRuleService = new BusinessRuleService();
    private BusinessRuleAdapterInterface businessRuleAdapter = new BusinessRuleIdAdapter(businessRuleService);

    @Override
    public void runController() throws IOException {
        new ConnectionReceiver(8084, new BusinessRuleCommand(true, businessRuleAdapter)).start();
        new ConnectionReceiver(8085, new BusinessRuleCommand(false, businessRuleAdapter)).start();
    }

}
