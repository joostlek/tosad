package nl.hu.tosad.webserver.target_database.service;

public class DefinitionChangedException extends RuntimeException {
    public DefinitionChangedException() {
        super("Definition changed");
    }
}
