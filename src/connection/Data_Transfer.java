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
    private PrintWriter writer;
    private boolean running = true;

    public Data_Transfer(Connection connect) {
        this.con = connect;
    }

    @Override
    public void run() {
        try {
            final int timeout = 20; //in ms
            while (!con.isConnected()){
                System.out.println("no Connection found");
            }
            input = new InputStreamReader(con.getSocket().getInputStream());
            output = new OutputStreamWriter(con.getSocket().getOutputStream());
            reader = new BufferedReader(input);
            writer = new PrintWriter(output);

            while (running){
                //send and receive game data
                sendData();
                try {
                    Main.getInfo().sleep(timeout*10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //receiveData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendData(){
        //send Data of own Position

        writer.write("//buildings");
        for (int i = 0; i < Player.getBuildings().size(); i++){
            writer.write("+++"+Player.getBuildings().get(i).getOwner()+
                    "+++"+Player.getBuildings().get(i).getType()+
                    "+++"+Player.getBuildings().get(i).getHp()+
                    "+++"+Player.getBuildings().get(i).getX()+
                    "+++"+Player.getBuildings().get(i).getY());
        }
        writer.write("//characters");
        for (int i = 0; i < Player.getCharacters().size(); i++){
            writer.write("+++"+Player.getCharacters().get(i).getOwner()+
                    "+++"+Player.getCharacters().get(i).getType()+
                    "+++"+Player.getCharacters().get(i).getHp()+
                    "+++"+Player.getCharacters().get(i).getName()+
                    "+++"+Player.getCharacters().get(i).getX()+
                    "+++"+Player.getCharacters().get(i).getY());
            System.out.println("+++"+Player.getCharacters().get(i).getOwner()+
                    "+++"+Player.getCharacters().get(i).getType()+
                    "+++"+Player.getCharacters().get(i).getHp()+
                    "+++"+Player.getCharacters().get(i).getName()+
                    "+++"+Player.getCharacters().get(i).getX()+
                    "+++"+Player.getCharacters().get(i).getY());
            System.out.println("sent character");
        }
        /*
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    private void receiveData(){
        //receive all position Data
        String message = "empty";
        try {
            if (reader.lines().count() > 0){
                message = reader.readLine();
            }
        } catch (IOException | UncheckedIOException e) {
            e.printStackTrace();
        }
        if (!message.equals("empty")){
            System.out.println(message);
        }
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
