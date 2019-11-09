package connection;

import data.Data;
import player.Player;

import java.io.*;
import java.util.ArrayList;

public class Data_Transfer implements Runnable { //Übergibt Spieldaten an den Server und empfängt Daten vom Server
    private Connection con;
    private Data data;
    private InputStreamReader input;
    private OutputStreamWriter output;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean running = true;
    private final int timeout = 20; //in ms

    public Data_Transfer(Connection connect) {
        this.con = connect;
    }

    @Override
    public void run() {
        try {
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
                    super.wait(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                receiveData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendData(){
        //send Data of own Position

        try {
            writer.write("//buildings");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < Player.getBuildings().size(); i++){
            try {
                writer.write("+++"+Player.getBuildings().get(i).getOwner()+
                                "+++"+Player.getBuildings().get(i).getType()+
                                "+++"+Player.getBuildings().get(i).getHp()+
                                "+++"+Player.getBuildings().get(i).getX()+
                                "+++"+Player.getBuildings().get(i).getY());
                writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.write("//characters");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < Player.getCharacters().size(); i++){
            try {
                writer.write("+++"+Player.getCharacters().get(i).getOwner()+
                                "+++"+Player.getCharacters().get(i).getType()+
                                "+++"+Player.getCharacters().get(i).getHp()+
                                "+++"+Player.getCharacters().get(i).getName()+
                                "+++"+Player.getCharacters().get(i).getX()+
                                "+++"+Player.getCharacters().get(i).getY());
                writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void receiveData(){
        //receive all position Data
        String message = "empty";
        try {
            message = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(message);
        //TODO Baut die Nachricht ausseinander und macht Objekte daraus
        //Danach werden die Objekte per Data.addBuilding und Data.addCharacter in die Daten eingegliedert
        //ArrayList <ArrayList<String>> datalist = new ArrayList <ArrayList<String>>();
        //message an Data weitergeben
        //Data.setListofLists(datalist);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
