package sample;

import connection.Connection;
import connection.Data_Transfer;
import controls.Camera;
import data.Data;
import data.UnitDatabank;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Game_Gui;
import javafx.application.Application;
import javafx.stage.Stage;
import loop.RenderLoop;
import player.Player;

import java.util.ArrayList;

public class Main extends Application {

    private static Game_Gui gui = new Game_Gui();
    private static Connection c = new Connection();
    private static Data_Transfer dt = new Data_Transfer(c);
    private static Thread connect = new Thread(c), info = new Thread(dt), render = new Thread(new RenderLoop(gui));


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Gamestate.state = Gamestate_e.ingame;
        gui.init(primaryStage);
        startWindow();
        if (Gamestate.state == Gamestate_e.ingame){
            addsomeFigures();
            startGame();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void startWindow(){
        gui.create();
        render.start();
    }

    public static void startGame(){
        System.out.println("start Game");
        Player.setGameRunning(true);
        Player.setReady(false);
        Gamestate.state = Gamestate_e.ingame;
    }

    public static void closeGame(){
        Player.setGameRunning(false);
        render = null;
        gui.close();
    }

    public static void searchConnection(String ip, String name) {
        //c = new Connection(ip, name, gui);
        c.setRunning(true);
        c.setConnected(false);
        c.setIp(ip);
        c.setName(name);
        c.setGui(gui);
        connect = new Thread(c);
        connect.start();
    }

    public static void startDataTransfer(){
        addsomeFigures();
        info = new Thread(dt);
        info.start();
    }

    public static void stopDT(){
        info = null;
    }

    public static void stopCon(){
        connect = null;
    }

    private static void addsomeFigures() { //Einheiten werden hinzugefügt
        Player.setUsername("Username");
        ArrayList<String> list = new ArrayList<>();
        list.add("building");
        list.add("Username");
        list.add(String.valueOf(UnitDatabank.NEXUS));
        list.add("50");
        list.add("nexus1");
        list.add(Double.toString(Camera.getCamX()+400));
        list.add(Double.toString(Camera.getCamY()+150));
        Player.getBuildings().add(list);
        list = new ArrayList<>();

        list.add("building");
        list.add("Username");
        list.add(String.valueOf(UnitDatabank.VILLAGE));
        list.add("50");
        list.add("nexus2");
        list.add(Double.toString(Camera.getCamX()+200));
        list.add(Double.toString(Camera.getCamY()+250));
        Player.getBuildings().add(list);
        list = new ArrayList<>();

        list.add("character");
        list.add("Username");
        list.add(String.valueOf(UnitDatabank.DAMAGEDEALER));
        list.add("30");
        list.add("tank1");
        list.add(Double.toString(Camera.getCamX()+100));
        list.add(Double.toString(Camera.getCamY()+200));
        list.add("null");
        list.add("null");
        list.add("50");
        list.add("true");
        Player.getCharacters().add(list);
        list = new ArrayList<>();

        Player.selectUnit(list);//test

        list.add("character");
        list.add("Username");
        list.add(String.valueOf(UnitDatabank.SPEEDER));
        list.add("30");
        list.add("tank2");
        list.add(Double.toString(Camera.getCamX()+300));
        list.add(Double.toString(Camera.getCamY()+300));
        list.add("null");
        list.add("null");
        list.add("170");
        list.add("true");
        Player.getCharacters().add(list);
        list = new ArrayList<>();

        for (int i = 0; i < Player.getBuildings().size(); i++){ //chas und builsings werden der Datenbank hinzugefügt (übernimmt eigentlich die Server-Client Kommunikation)
            Data.getListofLists().add(Player.getBuildings().get(i));
        }
        for (int i = 0; i < Player.getCharacters().size(); i++){
            Data.getListofLists().add(Player.getCharacters().get(i));
        }

        list.add("character");
        list.add("Enemy");
        list.add(String.valueOf(UnitDatabank.TANK));
        list.add("30");
        list.add("tank3");
        list.add(Double.toString(Camera.getCamX()+400));
        list.add(Double.toString(Camera.getCamY()+400));
        list.add("null");
        list.add("null");
        list.add("255");
        list.add("true");
        Data.getListofLists().add(list);
        list = new ArrayList<>();

        String s [] = {"tank1", "tank3"};
        Player.getAttacks().add(s);
    }


    public static Thread getInfo() {
        return info;
    }

    public static void setInfo(Thread info) {
        Main.info = info;
    }
}