package connection;

import gui.Game_Gui;
import sample.Main;

import java.io.*;
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
    private InputStreamReader input;
    private BufferedReader reader;
    private Game_Gui gui;

    public Connection (String ip, String name, Game_Gui gui){
        this.ip = ip;
        this.name = name;
        this.gui = gui;
    }
    @Override
    public void run() {
        try {
            int timeout = 300;
            running = true;
            while (true){
                while (running) {
                    while (socket == null || !socket.isConnected()) {
                        connected = false;
                        System.out.println("Searching for Connection...");
                        try {
                            socket = new Socket(ip, port);
                            //TODO braucht man socket.setKeepAlive(true); ???
                        } catch (ConnectException e){
                            e.printStackTrace();
                        }

                        if (socket != null){
                            System.out.println("connected");
                            connected = true;
                            input = new InputStreamReader(socket.getInputStream());
                            reader = new BufferedReader(input);
                            output = new OutputStreamWriter(socket.getOutputStream());
                            writer = new BufferedWriter(output);
                            writer.write(name);
                            writer.newLine();
                            writer.flush();
                            gui.connectedDialogue();
                            gui.getConnect().setDisabled(true);
                            gui.getReady().setDisabled(false);
                            try {
                                Main.startDataTransfer();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            running = false;
                        }
                    }
                    try {
                        Main.getInfo().sleep(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getReader() {
        return reader;
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

    public void disconnect(){
        //gui.getbStart().setDisable(true);
        //gui.getbConnect().setDisable(false);
        Main.closeGame();
    }
}