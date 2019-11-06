package sample;

import connection.Connection;
import connection.Data_Transfer;
import data.Data;
import gui.Gui;
import javafx.application.Application;
import javafx.stage.Stage;
import loop.GameLoop;

public class Main extends Application {

    Gui g = new Gui();
    private static Thread connect, info;
    private static Data data = new Data();
    private static Connection c;
    private static Data_Transfer dt;

    @Override
    public void start(Stage primaryStage) throws Exception {
        g.init();
        g.create(primaryStage);

        Thread loop = new Thread(new GameLoop());
        loop.start();
    }


    public static void main(String[] args) {
        launch(args);
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
        System.out.println("step1");
        c.setSocket(null);
        System.out.println("step2");
        connect = null;
        System.out.println("step3");
        c = null;
        System.out.println("successfully stopped connection");
    }
}


