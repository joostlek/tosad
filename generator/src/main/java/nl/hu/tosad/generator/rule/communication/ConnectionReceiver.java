package nl.hu.tosad.generator.rule.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionReceiver extends Thread {
    private int port;
    private CommandInterface command;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public ConnectionReceiver(int port, CommandInterface command) {
        this.port = port;
        this.command = command;
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Started socket on port {0}", port);
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                new SocketProcessor(serverSocket.accept(), command).start();
            } catch (IOException ex) {
                logger.severe(ex.getMessage());
            }

        }
    }
}
