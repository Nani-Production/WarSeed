package connection;

import gui.Launcher_Gui;
import javafx.scene.control.Alert;
import sample.Main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Connection implements Runnable { //Baut die Verbindung zwischen Client und Server auf und übergibt den Username (Danach übernimmt Data_Transfer den Datenustausch)
    private Socket socket;
    private final int port = 7777;
    private String ip;
    private boolean running = true, connected = false;
    private String name;
    private OutputStreamWriter output;
    private BufferedWriter writer;
    private Launcher_Gui launcher;

    public Connection (String ip, String name, Launcher_Gui launcher){
        this.ip = ip;
        this.name = name;
        this.launcher = launcher;
    }
    @Override
    public void run() {
        try {
            running = true;
            while (running) {
                while (socket == null || !socket.isConnected()) {
                    connected = false;
                    System.out.println("Searching for Connection...");
                    try {
                        socket = new Socket(ip, port);
                    } catch (ConnectException e){
                        e.printStackTrace();
                    }
                    if (socket != null){
                        System.out.println("connected");
                        connected = true;
                        output = new OutputStreamWriter(socket.getOutputStream());
                        writer = new BufferedWriter(output);
                        writer.write(name);
                        writer.newLine();
                        writer.flush();
                        launcher.connectedDialogue();
                        launcher.getButton().setDisable(false);
                        try {
                            Main.startDataTransfer();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        //running = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
