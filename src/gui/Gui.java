package gui;

import draw.Draw_Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Gui {
    public static double width = 1280, height = 720;
    public static Draw_Main dm;
    public static GraphicsContext gc_main;
    private Canvas canvas_main;
    private Scene scene;
    private StackPane root;
    public Font myFont = Font.loadFont(getClass().getResourceAsStream("/resources/Guardians.ttf"), 24);

    public void init () {
        dm = new Draw_Main();
    }

    public void create (Stage stage) {
        canvas_main = new Canvas(width, height);
        root = new StackPane();
        gc_main = canvas_main.getGraphicsContext2D();
        dm.draw(gc_main);

        root.getChildren().add(canvas_main);
        scene = new Scene(root, width, height);

        stage.setTitle("WarSeed");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        //GameLoop.start = System.currentTimeMillis();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}
