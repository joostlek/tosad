package nl.hu.tosad.webserver.rule.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleDTO {
    private Map<String, Object> properties;
    private List<String> list;

    public RuleDTO() {
        this.properties = new HashMap<>();
        this.list = new ArrayList<>();
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
