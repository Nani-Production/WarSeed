package connection;

import data.Data;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import player.Player;
import sample.Main;

import java.io.*;
import java.util.ArrayList;

public class Data_Transfer implements Runnable { //Übergibt Spieldaten an den Server und empfängt Daten vom Server
    private Connection con;
    private Tube tube;
    private Thread tubeThread;
    private OutputStreamWriter output;
    private BufferedWriter writer;
    private boolean running = true, ping = false;

    public Data_Transfer(Connection connect) {
        this.con = connect;
        tube = new Tube(con, this);
    }

    @Override
    public void run() {
        try {
            System.out.println("Data_Transfer running");
            final int timeout = 200; //in ms
            while (!con.isConnected()){
                System.out.println("no Connection found");
            }
            output = new OutputStreamWriter(con.getSocket().getOutputStream());
            writer = new BufferedWriter(output);

            tubeThread = new Thread(tube);
            tubeThread.start();

            while (running){
                //send and receive game data
                if (con.isConnected()){
                    sendData();
                    try {
                        Main.getInfo().sleep(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    receiveData();
                    if (con.getSocket() == null || !con.isConnected()){
                        running = false;
                    }
                    if (Player.isReady() && !Player.isGameRunning()){
                        if (!Player.isMessageSent()) {
                            writer.write("//Ready//");
                            Player.setMessageSent(true);
                            System.out.println("sent ready");
                            writer.newLine();
                            writer.flush();
                        }
                    } else if (!Player.isReady() && !Player.isGameRunning()){
                        if (!Player.isMessageSent()){
                            writer.write("//notReady//");
                            Player.setMessageSent(true);
                            System.out.println("sent not ready");
                            writer.newLine();
                            writer.flush();
                        }
                    }
                }
            }
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    private void sendData(){
        //send Data of own Position
        if (ping){
            try {
                writer.write("pong");
                ping = false;
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                handleIOException(e);
                e.printStackTrace();
            }
        }
        if (Player.isGameRunning()){
            try{ //Die eigene Einheiten wird geschickt
                writer.write("//buildings"+Player.getBuildings().size()+"#");
                for (int i = 0; i < Player.getBuildings().size(); i++){
                    writer.write("//"+Player.getBuildings().get(i).get(1)+
                            "+++"+Player.getBuildings().get(i).get(2)+
                            "+++"+Player.getBuildings().get(i).get(3)+
                            "+++"+Player.getBuildings().get(i).get(4)+
                            "+++"+Player.getBuildings().get(i).get(5)+
                            "+++"+Player.getBuildings().get(i).get(6)+"*");
                }
                writer.write("//characters"+Player.getCharacters().size()+"#");
                for (int i = 0; i < Player.getCharacters().size(); i++){
                    writer.write("+++"+Player.getCharacters().get(i).get(1)+
                            "+++"+Player.getCharacters().get(i).get(2)+
                            "+++"+Player.getCharacters().get(i).get(3)+
                            "+++"+Player.getCharacters().get(i).get(4)+
                            "+++"+Player.getCharacters().get(i).get(5)+
                            "+++"+Player.getCharacters().get(i).get(6)+
                            "+++"+Player.getCharacters().get(i).get(7)+
                            "+++"+Player.getCharacters().get(i).get(8)+
                            "+++"+Player.getCharacters().get(i).get(9)+
                            "+++"+Player.getCharacters().get(i).get(10)+"*");
                }
                writer.write("//end");
                writer.newLine();
                writer.flush();
            } catch (IOException e){
                handleIOException(e);
            }
        }
    }

    private void receiveData(){
        for (int i = 0; i < tube.getBuffer().size(); i++){
            Data.addData(tube.getBuffer().get(i));
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private void handleIOException (IOException e) {
        if (e.toString().startsWith("java.net.SocketException")) {
            running = false;

            if (Gamestate.state != Gamestate_e.reconnect){
                Gamestate.lastState = Gamestate.state;
            }
            Gamestate.state = Gamestate_e.reconnect;

            con.getGui().bConnectAction();
        } else {
            e.printStackTrace();
        }
    }

    public void setPing(boolean ping) {
        this.ping = ping;
    }

    public BufferedWriter getWriter() {
        return writer;
    }
}