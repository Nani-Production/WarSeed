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

public class Main extends Application {

    private static Launcher_Gui launcher = new Launcher_Gui();
    private static Game_Gui g = new Game_Gui();
    private static Thread connect, info, loop = new Thread(new GameLoop()), render = new Thread(new RenderLoop());
    private static Data data = new Data();
    private static Connection c;
    private static Data_Transfer dt;

    @Override
    public void start(Stage primaryStage) throws Exception {
        launcher.init();
        g.init(primaryStage);
        launcher.create(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void startGame(){
        g.create();
        launcher.close();
        loop.start();
        render.start();
    }

    public static void closeGame(){

    }

    public static void startConnection(String ip, String name){
        c = new Connection(ip, name);
        connect = new Thread(c);
        connect.start();
    }

    public static void startDataTransfer(){
        dt = new Data_Transfer(c, data);
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


