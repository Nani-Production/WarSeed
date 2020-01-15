package connection;

import data.Data;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import player.Player;
import sample.Main;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.DoubleAccumulator;

public class Data_Transfer implements Runnable { //Übergibt Spieldaten an den Server und empfängt Daten vom Server
    private Connection con;
    private Tube tube;
    private Thread tubeThread;
    private OutputStreamWriter output;
    private BufferedWriter writer;
    private boolean running = true, ping = false;
    private SimpleDateFormat format = new SimpleDateFormat();

    public Data_Transfer(Connection connect) {
        this.con = connect;
        tube = new Tube(con, this);
    }

    @Override
    public void run() {
        //test
        try {
            FileWriter fileWriter = new FileWriter("log.txt");
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //test
        try {
            System.out.println("Data_Transfer running");
            final int timeout = 5; //in ms
            while (!con.isConnected()) {
                System.out.println("no Connection found");
            }
            output = new OutputStreamWriter(con.getSocket().getOutputStream());
            writer = new BufferedWriter(output);

            tubeThread = new Thread(tube);
            tubeThread.start();

            while (running) {
                //send and receive game data
                if (con.isConnected()) {
                    sendData();
                    try {
                        Main.getInfo().sleep(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    receiveData();
                    if (con.getSocket() == null || !con.isConnected()) {
                        running = false;
                    }
                    if (Player.isReady() && !Player.isGameRunning()) {
                        if (!Player.isMessageSent()) {
                            writer.write("//Ready//");
                            Player.setMessageSent(true);
                            System.out.println("sent ready");
                            writer.newLine();
                            writer.flush();
                        }
                    } else if (!Player.isReady() && !Player.isGameRunning()) {
                        if (!Player.isMessageSent()) {
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

    private void sendData() {
        //send Data of own Position
        if (ping) {
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
        if (Player.isGameRunning()) {
            actualisePosition();
            //test
            //System.out.println("transfer "+Player.getSelectedUnit().get(7)+"   "+Player.getSelectedUnit().get(8));
            //test

            try { //Die eigene Einheiten wird geschickt
                StringBuilder line = new StringBuilder();
                line.append("//data");
                line.append(Long.toString(System.currentTimeMillis()));
                line.append("//buildings" + Player.getBuildings().size() + "#");
                for (int i = 0; i < Player.getBuildings().size(); i++) {
                    line.append("//" + Player.getBuildings().get(i).get(1) +
                            "+++" + Player.getBuildings().get(i).get(2) +
                            "+++" + Player.getBuildings().get(i).get(3) +
                            "+++" + Player.getBuildings().get(i).get(4) +
                            "+++" + Player.getBuildings().get(i).get(5) +
                            "+++" + Player.getBuildings().get(i).get(6) + "*");
                }
                line.append("//characters" + Player.getCharacters().size() + "#");
                for (int i = 0; i < Player.getCharacters().size(); i++) {
                    line.append("+++" + Player.getCharacters().get(i).get(1) +
                            "+++" + Player.getCharacters().get(i).get(2) +
                            "+++" + Player.getCharacters().get(i).get(3) +
                            "+++" + Player.getCharacters().get(i).get(4) +
                            "+++" + Player.getCharacters().get(i).get(5) +
                            "+++" + Player.getCharacters().get(i).get(6) +
                            "+++" + Player.getCharacters().get(i).get(7) +
                            "+++" + Player.getCharacters().get(i).get(8) +
                            "+++" + Player.getCharacters().get(i).get(9) +
                            "+++" + Player.getCharacters().get(i).get(10) + "*");
                }

                //projectiles
                System.out.println("size in data Transfer "+Data.getProjectiles().size());
                line.append("//projectiles" + Data.getProjectiles().size() + "#");
                for (int i = 0; i < Data.getProjectiles().size(); i++){
                    line.append("+++"+Data.getProjectiles().get(i).get(0)+
                            "+++"+Data.getProjectiles().get(i).get(1)+
                            "+++"+Data.getProjectiles().get(i).get(2)+
                            "+++"+Data.getProjectiles().get(i).get(3)+
                            "+++"+Data.getProjectiles().get(i).get(4)+
                            "+++"+Data.getProjectiles().get(i).get(5)+
                            "+++"+Data.getProjectiles().get(i).get(6)+"*");
                    if (Data.getProjectiles().get(i).get(6).equals("true")){
                        Data.getProjectiles().remove(i);
                        i--;
                    }
                }

                //attacks
                line.append("//attacks");
                for (int i = 0; i < Player.getAttacks().size(); i++){
                    line.append(Player.getAttacks().get(i)[0]+"+++"+Player.getAttacks().get(i)[1]+"+++#");
                }
                Player.getAttacks().clear();

                line.append("//end");
                writeFile(line.toString());
                writer.write(line.toString());
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                handleIOException(e);
            }

            /*
            //!!!Testcode!!!
            StringBuilder line = new StringBuilder();
            line.append("//buildings" + Player.getBuildings().size() + "#");
            for (int i = 0; i < Player.getBuildings().size(); i++) {
                line.append("//" + Player.getBuildings().get(i).get(1) +
                        "+++" + Player.getBuildings().get(i).get(2) +
                        "+++" + Player.getBuildings().get(i).get(3) +
                        "+++" + Player.getBuildings().get(i).get(4) +
                        "+++" + Player.getBuildings().get(i).get(5) +
                        "+++" + Player.getBuildings().get(i).get(6) + "*");
            }
            line.append("//characters" + Player.getCharacters().size() + "#");
            for (int i = 0; i < Player.getCharacters().size(); i++) {
                line.append("+++" + Player.getCharacters().get(i).get(1) +
                        "+++" + Player.getCharacters().get(i).get(2) +
                        "+++" + Player.getCharacters().get(i).get(3) +
                        "+++" + Player.getCharacters().get(i).get(4) +
                        "+++" + Player.getCharacters().get(i).get(5) +
                        "+++" + Player.getCharacters().get(i).get(6) +
                        "+++" + Player.getCharacters().get(i).get(7) +
                        "+++" + Player.getCharacters().get(i).get(8) +
                        //"+++" + 800 +
                        //"+++" + 800 +
                        "+++" + Player.getCharacters().get(i).get(9) +
                        "+++" + Player.getCharacters().get(i).get(10) + "*");
            }
            //projectiles
            line.append("//projectiles" + Data.getProjectiles().size() + "#");

            //Player.getAttacks().clear();
            line.append("//end\n");
            writeFile(line.toString());
            //!!!Testcode!!!
             */
        }
    }

    private void receiveData() {
        int index = -1;
        long newestTime = 0;
        for (int i = 0; i < tube.getBuffer().size(); i++){
            String s = null;
            try {
                s = tube.getBuffer().get(i).substring("//data".length(), tube.getBuffer().get(i).indexOf("//buildings"));
            } catch (NullPointerException e){
                e.printStackTrace();
            }
            if (!s.equals(null)){
                if (Long.parseLong(s) > newestTime){
                    newestTime = Long.parseLong(s);
                    index = i;
                }
            }
        }
        if (index > -1){
            Data.addData(tube.getBuffer().get(index));
        }
        /*
        for (int i = 0; i < tube.getBuffer().size(); i++) {
            Data.addData(tube.getBuffer().get(i));
        }
         */
        tube.getBuffer().clear();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private void handleIOException(IOException e) {
        if (e.toString().startsWith("java.net.SocketException")) {
            running = false;

            if (Gamestate.state != Gamestate_e.reconnect) {
                Gamestate.lastState = Gamestate.state;
            }
            Gamestate.state = Gamestate_e.reconnect;

            //test
            System.exit(0);
            //test

            con.getGui().bConnectAction();
        } else {
            e.printStackTrace();
        }
    }

    public void setPing(boolean ping) {
        this.ping = ping;
    }

    private void writeFile(String output) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        String outputWithNewLine = output + System.getProperty("line.separator");

        try {
            fileWriter = new FileWriter("log.txt", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(outputWithNewLine);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.flush();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void actualisePosition() {
        for (int i = 0; i < Data.getListofLists().size(); i++){
            if (Data.getListofLists().get(i).get(0).equals("character")){
                for (int j = 0; j < Player.getCharacters().size(); j++){
                    if (Player.getCharacters().get(j).get(1).equals(Data.getListofLists().get(i).get(1)) && Player.getCharacters().get(j).get(2).equals(Data.getListofLists().get(i).get(2)) && Player.getCharacters().get(j).get(4).equals(Data.getListofLists().get(i).get(4))){
                        Player.getCharacters().get(j).set(5, Data.getListofLists().get(i).get(5));
                        Player.getCharacters().get(j).set(6, Data.getListofLists().get(i).get(6));
                    }
                }
            }
        }
    }
}