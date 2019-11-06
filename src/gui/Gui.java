package gui;

import connection.Connection;
import controls.*;
import draw.Draw_Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Main;

import java.util.Optional;

public class Gui {
    public final static double width = 1280, height = 720;
    public static Draw_Main dm;
    public static GraphicsContext gc_main;
    private Canvas canvas_main;
    private Scene scene;
    private StackPane root;
    //public Font myFont = Font.loadFont(getClass().getResourceAsStream("/resources/Guardians.ttf"), 24);

    public void init () {
        dm = new Draw_Main();
    }

    public void create (Stage stage) {

        Button bConnect = new Button("connect to Server");
        bConnect.setMinSize(100, 50);
        bConnect.setMaxSize(150, 50);

        bConnect.setVisible(true);

        Button bStopSearch = new Button("stop Search for Server");
        bStopSearch.setMinSize(100, 50);
        bStopSearch.setMaxSize(150, 50);
        bStopSearch.setTranslateY(50);
        bStopSearch.setVisible(true);
        bStopSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Main.stopConnection();
                    bConnect.setDisable(false);
                    bStopSearch.setDisable(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        bConnect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bConnectAction();
                bConnect.setDisable(true);
                bStopSearch.setDisable(false);
            }});


        canvas_main = new Canvas(width, height);
        root = new StackPane();
        gc_main = canvas_main.getGraphicsContext2D();
        dm.draw(gc_main);

        root.getChildren().add(canvas_main);
        root.getChildren().addAll(bConnect, bStopSearch);
        scene = new Scene(root, width, height);

        scene.setOnKeyPressed(new KeyPressed());
        scene.setOnKeyReleased(new KeyReleased());
        scene.setOnMouseMoved(new MouseMoved());
        scene.setOnMousePressed(new MousePressed());
        scene.setOnMouseReleased(new MouseReleased());

        stage.setTitle("WarSeed");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    private void bConnectAction(){
        String ip = null, username = null;

        //Es kommen noch TextFelder rein, in die man die String eingibt.
        //Die Popups sollen dann nur kommen, wenn die Felder leer sind

        //ip
        ip = ipDialogue();
        while (!checkIPSyntax(ip)){
            ip = ipDialogue();
        }

        //username
        username = nameDialogue();

        Main.startConnection(ip, username);

        ip = null;
        username = null;
    }

    private String ipDialogue (){
        TextInputDialog dialog1 = new TextInputDialog("127.0.0.1");
        dialog1.setTitle("IP needed");
        dialog1.setHeaderText("To connect you need a target ip");
        dialog1.setContentText("Please enter the ip-address:");

        Optional<String> result1 = dialog1.showAndWait();
        if (result1.isPresent()){
            return result1.get();
        } else {
            return null;
        }
    }

    private String nameDialogue(){
        TextInputDialog dialog2 = new TextInputDialog("lol");
        dialog2.setTitle("username needed");
        dialog2.setHeaderText("To connect, you need a Username");
        dialog2.setContentText("Please enter your name:");

        Optional<String> result2 = dialog2.showAndWait();
        if (result2.isPresent()){
            return result2.get();
        } else {
            return null;
        }
    }

    private boolean checkIPSyntax(String ip){
        //Check if the String is a valid IP address
        return true;
    }


}
