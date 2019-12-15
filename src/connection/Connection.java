package connection;

import gamestate.Gamestate;
import gamestate.Gamestate_e;
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

    public Connection() {}

    public Connection(String ip, String name, Game_Gui gui) {
        this.ip = ip;
        this.name = name;
        this.gui = gui;
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
                    } catch (ConnectException e) {
                        e.printStackTrace();
                    }

                    if (socket != null) {
                        System.out.println("connected");
                        connected = true;
                        input = new InputStreamReader(socket.getInputStream());
                        reader = new BufferedReader(input);
                        output = new OutputStreamWriter(socket.getOutputStream());
                        writer = new BufferedWriter(output);
                        writer.write(name);
                        writer.newLine();
                        writer.flush();
                        if (Gamestate.state == Gamestate_e.reconnect){
                            Gamestate.state = Gamestate.lastState;
                            Gamestate.lastState = Gamestate_e.reconnect;
                        }
                        gui.connectedDialogue();
                        gui.getConnect().setDisabled(true);
                        gui.getReady().setDisabled(false);
                        try {
                            Main.startDataTransfer();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        running = false;
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

    public Game_Gui getGui() {
        return gui;
    }

    public void setGui(Game_Gui gui) {
        this.gui = gui;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}