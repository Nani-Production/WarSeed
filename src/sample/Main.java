package sample;

import connection.Connection;
import connection.Data_Transfer;
import data.Data;
import gui.Game_Gui;
import gui.Launcher_Gui;
import javafx.application.Application;
import javafx.stage.Stage;
import loop.GameLoop;
import loop.RenderLoop;
import player.Player;
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
        startGame(primaryStage);
        //launcher.init(primaryStage);
        //launcher.create();
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
        Player.getCharacters().add(new Character(0, 0, 64, 64, 30, "tank", Player.getUsername(), "peter", 13, 10, 5, 7));
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
}