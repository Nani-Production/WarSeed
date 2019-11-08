package gui;

import controls.*;
import draw.Draw_Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Game_Gui {

    private final static double width = 1280, height = 720;
    private static Draw_Main dm;
    private static GraphicsContext gc_main;
    private Canvas canvas_main;
    private Scene scene;
    private StackPane root;
    private Stage stage;
    //public Font myFont = Font.loadFont(getClass().getResourceAsStream("/resources/Guardians.ttf"), 24);

    public void init (Stage stage) {
        dm = new Draw_Main();
        this.stage = stage;
    }

    public void create (){
        canvas_main = new Canvas(width, height);
        root = new StackPane();
        gc_main = canvas_main.getGraphicsContext2D();
        dm.draw(gc_main);

        root.getChildren().add(canvas_main);
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
}