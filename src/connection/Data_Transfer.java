package connection;

import data.Data;
import player.Player;

import java.io.*;

public class Data_Transfer implements Runnable { //Übergibt Spieldaten an den Server und empfängt Daten vom Server
    private Connection con;
    private Data data;
    private Player player;
    private InputStreamReader input;
    private OutputStreamWriter output;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean running = true;
    private final int timeout = 20; //in ms

    public Data_Transfer(Connection connect, Data data, Player player) {
        this.con = connect;
        this.data = data;
        this.player = player;
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
        for (int i = 0; i < player.getBuildings().size(); i++){
            try {
                writer.write("//buildings");
                writer.write("+++"+player.getBuildings().get(i).getOwner()+
                                "+++"+player.getBuildings().get(i).getType()+
                                "+++"+player.getBuildings().get(i).getHp()+
                                "+++"+player.getBuildings().get(i).getX()+
                                "+++"+player.getBuildings().get(i).getY());
                writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < player.getCharacters().size(); i++){
            try {
                writer.write("//characters");
                writer.write("+++"+player.getCharacters().get(i).getOwner()+
                                "+++"+player.getCharacters().get(i).getType()+
                                "+++"+player.getCharacters().get(i).getHp()+
                                "+++"+player.getCharacters().get(i).getName()+
                                "+++"+player.getCharacters().get(i).getX()+
                                "+++"+player.getCharacters().get(i).getY());
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
        //Baut die Nachricht ausseinander und macht Objekte daraus
        //Danach werden die Objekte per Data.addBuilding und Data.addCharacter in die Daten eingegliedert

        //message an Data weitergeben
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
