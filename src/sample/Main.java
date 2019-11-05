package sample;

import gui.Gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import loop.GameLoop;

public class Main extends Application {

    Gui g = new Gui();
    @Override
    public void start(Stage primaryStage) throws Exception{
        g.init();
        g.create(primaryStage);

        Thread loop = new Thread(new GameLoop());
        loop.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
