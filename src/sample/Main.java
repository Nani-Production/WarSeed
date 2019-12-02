package sample;

import connection.Connection;
import connection.Data_Transfer;
import controls.Camera;
import data.Data;
import gui.Game_Gui;
import gui.Launcher_Gui;
import javafx.application.Application;
import javafx.print.PageLayout;
import javafx.stage.Stage;
import loop.GameLoop;
import loop.RenderLoop;
import player.Player;
import units.Building;
import units.Character;

import java.util.ArrayList;

public class Main extends Application {

    private static Launcher_Gui launcher = new Launcher_Gui();
    private static Game_Gui gui = new Game_Gui();
    private static Thread connect, info, loop = new Thread(new GameLoop()), render = new Thread(new RenderLoop(gui));
    private static Data data = new Data();
    private static Connection c;
    private static Data_Transfer dt;

    @Override
    public void start(Stage primaryStage) throws Exception {
        startGame(primaryStage);
        addsomeFigures();
        //launcher.init(primaryStage);
        //launcher.create();
    }

    public static void addsomeFigures() { //Einheiten werden hinzugefügt
        Player.setUsername("UN");
        ArrayList<String> list = new ArrayList<>();
        list.add("building");
        list.add("UN");
        list.add("nexus");
        list.add("50");
        list.add("nexus1");
        list.add(Double.toString(Camera.getCamX()+200));
        list.add(Double.toString(Camera.getCamY()+150));
        Player.getBuildings().add(list);
        list = new ArrayList<>();

        list.add("building");
        list.add("UN");
        list.add("nexus");
        list.add("50");
        list.add("nexus2");
        list.add(Double.toString(Camera.getCamX()+200));
        list.add(Double.toString(Camera.getCamY()+250));
        Player.getBuildings().add(list);
        list = new ArrayList<>();

        list.add("character");
        list.add("UN");
        list.add("tank");
        list.add("30");
        list.add("tank1");
        list.add(Double.toString(Camera.getCamX()+100));
        list.add(Double.toString(Camera.getCamY()+200));
        list.add(null);
        list.add(null);
        Player.getCharacters().add(list);
        list = new ArrayList<>();

        list.add("character");
        list.add("UN");
        list.add("plane");
        list.add("30");
        list.add("plane1");
        list.add(Double.toString(Camera.getCamX()+300));
        list.add(Double.toString(Camera.getCamY()+300));
        list.add(null);
        list.add(null);
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
        list.add("tank");
        list.add("30");
        list.add("tank2");
        list.add(Double.toString(Camera.getCamX()+400));
        list.add(Double.toString(Camera.getCamY()+400));
        list.add(null);
        list.add(null);
        Data.getListofLists().add(list);
        list = new ArrayList<>();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void startGame(Stage stage){
        //launcher.close();
        gui.init(stage);
        gui.create();
        loop.start();
        render.start();
        try {
            render.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void closeGame(){
        try {
            loop.sleep(1000);
            render.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loop = null;
        render = null;
        gui.close();
    }

    public static void startConnection(String ip, String name) {
        c = new Connection(ip, name, launcher);
        connect = new Thread(c);
        connect.start();
    }

    public static void startDataTransfer(){
        dt = new Data_Transfer(c);
        info = new Thread(dt);
        info.start();
    }

    public static void stopConnection() throws InterruptedException {
        if (info != null){
            dt.setRunning(false);
            info.join();
            info = null;
        }
        c.setRunning(false);
        c.setSocket(null);
        connect = null;
        c = null;
        System.out.println("successfully stopped connection");
    }

    public static Thread getInfo() {
        return info;
    }

    public static void setInfo(Thread info) {
        Main.info = info;
    }
}