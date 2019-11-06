package connection;

import javafx.scene.control.Alert;
import sample.Main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Connection implements Runnable {
    private Socket socket;
    private final int port = 7777;
    private String ip;
    private boolean running = true, connected = false;
    private String name;
    private OutputStreamWriter output;
    private BufferedWriter writer;

    public Connection (String ip, String name){
        this.ip = ip;
    }
    @Override
    public void run() {
        try {
            while (running) {
                while (socket == null || !socket.isConnected()) {
                    connected = false;
                    System.out.println("Searching for Connection...");
                    try {
                        socket = new Socket(ip, port);
                        System.out.println("lol");
                    } catch (ConnectException e){
                        e.printStackTrace();
                    }
                    if (socket != null){
                        System.out.println("connected");
                        connectedDialogue();
                        connected = true;
                        output = new OutputStreamWriter(socket.getOutputStream());
                        writer = new BufferedWriter(output);
                        writer.write(name);
                        writer.flush();
                        writer.close();
                        Main.startDataTransfer();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void connectedDialogue(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You have a connection to the Server!");

        alert.showAndWait();
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
