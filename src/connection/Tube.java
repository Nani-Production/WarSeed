package connection;

import data.Data;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import player.Player;
import sample.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Tube implements Runnable{
    private Connection con;
    private Data_Transfer dt;
    private ArrayList<String> buffer = new ArrayList<>();

    public Tube(Connection con, Data_Transfer dt) {
        this.con = con;
        this.dt = dt;
    }

    @Override
    public void run() {
        while (true){
            try {
                String line = con.getReader().readLine();
                if (line != null){
                    if (line.startsWith("//buildings")){ //Datensatz vom Client
                        if (Player.isGameRunning()){
                            buffer.add(line);
                        }
                    } else if (line.startsWith("//command")) { //Command for Server

                    } else if (line.startsWith("//message")){ //Messsage for the chat

                    } else if (line.startsWith("//GameStarting")) {
                        Data.addMap(line);
                        Main.startGame();
                    } else if (line.startsWith("ping")) {
                        dt.setPing(true);
                        //System.out.println("ping");
                    } else {
                        System.out.println("Error line of client has no use");
                    }
                }
            } catch (IOException e) {
                handleIOException(e);
            }
        }
    }

    private void handleIOException (IOException e){
        if (e.toString().startsWith("java.net.SocketException")) {
            dt.setRunning(false);

            if (Gamestate.state != Gamestate_e.reconnect){
                Gamestate.lastState = Gamestate.state;
            }
            Gamestate.state = Gamestate_e.reconnect;

            con.getGui().bConnectAction();
        } else {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getBuffer() {
        return buffer;
    }

    public void setBuffer(ArrayList<String> buffer) {
        this.buffer = buffer;
    }
}