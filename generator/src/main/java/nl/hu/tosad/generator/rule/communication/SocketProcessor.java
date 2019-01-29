package nl.hu.tosad.generator.rule.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class SocketProcessor extends Thread {
    private Socket socket;
    private CommandInterface callback;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public SocketProcessor(Socket socket, CommandInterface callback) {
        this.socket = socket;
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            Scanner scanner = new Scanner(is);
            String json = "[]";
            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                if (temp.equals("")) {
                    break;
                }
                json = temp;
            }
            String sql = callback.execute(json);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(sql);
            printWriter.flush();
            printWriter.close();
            scanner.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                logger.severe(e1.getMessage());
            }
        }
    }
}
