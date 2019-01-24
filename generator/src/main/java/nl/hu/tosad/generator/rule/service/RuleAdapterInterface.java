package nl.hu.tosad.generator.rule.service;

import java.io.IOException;

public interface RuleAdapterInterface {

    String runGenerator(String json, boolean wet) throws IOException;
}
