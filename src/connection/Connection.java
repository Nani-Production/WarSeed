package connection;

import controls.Camera;
import gui.Launcher_Gui;
import player.Player;
import sample.Main;
import units.Building;
import units.Character;

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
                            launcher.connectedDialogue();
                            Player.getBuildings().add(new Building(Camera.getCamX()+200, Camera.getCamY()+150, 64, 64, 50, "nexus1", Player.getUsername()));
                            Player.getBuildings().add(new Building(Camera.getCamX()+200, Camera.getCamY()+250, 64, 64, 50, "nexus2", Player.getUsername()));
                            Player.getCharacters().add(new Character(Camera.getCamX()+100, Camera.getCamY()+200, 64, 64, 30, "tank", Player.getUsername(), 13, 10, 5, 7));
                            Player.getCharacters().add(new Character(Camera.getCamX()+300, Camera.getCamY()+300, 64, 64, 35, "plane", Player.getUsername(), 15, 12, 6, 8));
                            launcher.getbStart().setDisable(false);
                            try {
                                Main.startDataTransfer();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            running = false;
                        }
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
        launcher.getbStart().setDisable(true);
        launcher.getbConnect().setDisable(false);
        Main.closeGame();
    }
}