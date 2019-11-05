package connection;

import java.io.IOException;
import java.net.Socket;

public class Connection implements Runnable {
    private Socket socket;
    private final int port = 7777;
    private String ip;
    private boolean running = true;

    public Connection (String ip){
        this.ip = ip;
    }
    @Override
    public void run() {
        try {
            while (running) {
                while (!socket.isConnected()) {
                    System.out.println("Searching for Connection...");
                    socket = new Socket(ip, port);
                    if (socket.isConnected()){
                        System.out.println("connected");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
