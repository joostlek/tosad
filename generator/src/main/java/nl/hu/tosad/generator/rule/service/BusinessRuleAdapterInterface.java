package nl.hu.tosad.generator.rule.service;

import java.io.IOException;

public interface BusinessRuleAdapterInterface {

    String runGenerator(String json, boolean wet) throws IOException;
}
