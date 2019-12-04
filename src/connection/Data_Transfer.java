package connection;

import data.Data;
import player.Player;
import sample.Main;

import java.io.*;
import java.util.ArrayList;

public class Data_Transfer implements Runnable { //Übergibt Spieldaten an den Server und empfängt Daten vom Server
    private Connection con;
    private Data data;
    private InputStreamReader input;
    private OutputStreamWriter output;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean running = true, ping = false;

    public Data_Transfer(Connection connect) {
        this.con = connect;
    }

    @Override
    public void run() {
        try {
            final int timeout = 200; //in ms
            while (!con.isConnected()){
                System.out.println("no Connection found");
            }
            input = new InputStreamReader(con.getSocket().getInputStream());
            output = new OutputStreamWriter(con.getSocket().getOutputStream());
            reader = new BufferedReader(input);
            writer = new BufferedWriter(output);

            while (running){
                //send and receive game data
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
                        }
                    } else if (!Player.isReady() && !Player.isGameRunning()){
                        if (!Player.isMessageSent()){
                            writer.write("//notReady//");
                            Player.setMessageSent(true);
                            System.out.println("sent not ready");
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
            } catch (IOException e) {
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
                            "+++"+Player.getCharacters().get(i).get(8)+"*");
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
        try {
            String line = con.getReader().readLine();
            System.out.println("line: "+line);
            if (line != null){
                if (line.startsWith("//buildings")){ //Datensatz vom Client
                    Data.addData(line);
                } else if (line.startsWith("//command")) { //Command for Server

                } else if (line.startsWith("//message")){ //Messsage for the chat

                } else if (line.startsWith("//GameStarting//")) {
                    Main.startGame();
                } else if (line.startsWith("ping")) {
                    ping = true;
                } else {
                    System.out.println("Error line of client has no use");
                }
            }
            System.out.println("Datasize: "+Data.getListofLists().size());
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private void handleIOException (IOException e){
        if (e.toString().startsWith("java.net.SocketException: Connection reset by peer")){
            con.setRunning(true);
            //con.disconnect();
        } else {
            e.printStackTrace();
        }
    }

    public BufferedReader getReader() {
        return reader;
    }

    public BufferedWriter getWriter() {
        return writer;
    }
}