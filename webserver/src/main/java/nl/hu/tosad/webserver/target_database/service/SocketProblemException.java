package nl.hu.tosad.webserver.target_database.service;

public class SocketProblemException extends RuntimeException {
    public SocketProblemException() {
        super("There is a problem with the socket!");
    }
}
