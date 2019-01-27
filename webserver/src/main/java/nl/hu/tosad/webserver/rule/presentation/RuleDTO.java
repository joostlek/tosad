package nl.hu.tosad.webserver.rule.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RuleDTO {
    private Map<String, Object> properties;

    public RuleDTO() {
        this.properties = new HashMap<>();
        this.properties.put("list", new ArrayList<>());
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
