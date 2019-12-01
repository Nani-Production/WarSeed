package gui;

import controls.*;
import draw.Draw_Main;
import draw.ImageLoader;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Game_Gui {

    private final double width = 1280, height = 720;
    private double mapWidth = width*3, mapHeight = height*3;
    private static Draw_Main dm;
    private static GraphicsContext gc_main;
    private Canvas canvas_main;
    private Scene scene;
    private StackPane root;
    private Stage stage;
    //public Font myFont = Font.loadFont(getClass().getResourceAsStream("/resources/Guardians.ttf"), 24);

    private Minimap minimap;
    private Unitinfo unitinfo;

    public void init (Stage stage) {
        dm = new Draw_Main();
        this.stage = stage;

        if (ImageLoader.map.getWidth() > width && ImageLoader.map.getHeight() > height){
            mapWidth = ImageLoader.map.getWidth();
            mapHeight = ImageLoader.map.getHeight();
        } else {
            mapWidth = width*2;
            mapHeight = height*2;
        }
        System.out.println("lol: "+ImageLoader.map.getWidth()+"    "+ImageLoader.map.getHeight());

        Camera.setMapSize(mapWidth, mapHeight);
        Camera.setStartCoordinates(0, 0);
    }

    public void create (){
        minimap = new Minimap(width-300, height-300, 300, 300);
        unitinfo = new Unitinfo(0, height-350, 250, 350);

        canvas_main = new Canvas(width, height);
        root = new StackPane();
        gc_main = canvas_main.getGraphicsContext2D();
        dm.draw(gc_main, this);

        root.getChildren().add(canvas_main);
        scene = new Scene(root, width, height);

        scene.setOnKeyPressed(new KeyPressed());
        scene.setOnKeyReleased(new KeyReleased());
        scene.setOnMouseMoved(new MouseMoved(this));
        scene.setOnMousePressed(new MousePressed(this));
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

    public Minimap getMinimap() {
        return minimap;
    }

    public void setMinimap(Minimap minimap) {
        this.minimap = minimap;
    }

    public Unitinfo getUnitinfo() {
        return unitinfo;
    }

    public void setUnitinfo(Unitinfo unitinfo) {
        this.unitinfo = unitinfo;
    }

    public static Draw_Main getDm() {
        return dm;
    }

    public static void setDm(Draw_Main dm) {
        Game_Gui.dm = dm;
    }

    public static GraphicsContext getGc_main() {
        return gc_main;
    }

    public static void setGc_main(GraphicsContext gc_main) {
        Game_Gui.gc_main = gc_main;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getMapWidth() {
        return mapWidth;
    }

    public double getMapHeight() {
        return mapHeight;
    }

    public void close(){
        Platform.exit();
    }
}