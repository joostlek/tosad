package nl.hu.tosad.generator.rule.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

public class ConnectionReceiver extends Thread {
    private int port;
    private BusinessRuleCommand command;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public ConnectionReceiver(int port, BusinessRuleCommand command) {
        this.port = port;
        this.command = command;
    }

    @Override
    public void run() {
        logger.info("Started socket on port " + port);
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                new SocketProcessor(serverSocket.accept(), command).start();
                serverSocket.close();
            } catch (IOException ex) {
                logger.severe(ex.getMessage());
            }

        }
    }
}
