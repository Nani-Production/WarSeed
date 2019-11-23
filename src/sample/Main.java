package sample;

import connection.Connection;
import connection.Data_Transfer;
import controls.Camera;
import data.Data;
import gui.Game_Gui;
import gui.Launcher_Gui;
import javafx.application.Application;
import javafx.stage.Stage;
import loop.GameLoop;
import loop.RenderLoop;
import player.Player;
import units.Building;
import units.Character;

public class Main extends Application {

    private static Launcher_Gui launcher = new Launcher_Gui();
    private static Game_Gui gui = new Game_Gui();
    private static Thread connect, info, loop = new Thread(new GameLoop()), render = new Thread(new RenderLoop(gui));
    private static Data data = new Data();
    private static Connection c;
    private static Data_Transfer dt;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //startGame(primaryStage);
        launcher.init(primaryStage);
        launcher.create();
        Player.getBuildings().add(new Building(Camera.getCamX()+200, Camera.getCamY()+150, 64, 64, 50, "nexus1", Player.getUsername()));
        Player.getBuildings().add(new Building(Camera.getCamX()+200, Camera.getCamY()+250, 64, 64, 50, "nexus2", Player.getUsername()));
        Player.getCharacters().add(new Character(Camera.getCamX()+100, Camera.getCamY()+200, 64, 64, 30, "tank", Player.getUsername(), "peter", 13, 10, 5, 7));
        Player.getCharacters().add(new Character(Camera.getCamX()+300, Camera.getCamY()+300, 64, 64, 35, "plane", Player.getUsername(), "hans", 15, 12, 6, 8));
        System.out.println(Player.getCharacters().size());
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