package gui;

import controls.*;
import draw.Draw_Main;
import draw.ImageLoader;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.robot.Robot;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import player.Player;
import sample.Main;

import java.util.Optional;

public class Game_Gui {

    private final double width = 1280, height = 720;
    private double mapWidth = width*3, mapHeight = height*3;
    private static Draw_Main dm;
    private static GraphicsContext gc_main;
    private Canvas canvas_main;
    private Scene scene;
    private StackPane root;
    private Stage stage;
    private Minimap minimap;
    private Unitinfo unitinfo;
    private Button connect, ready;
    private TextField iptf, nametf;
    private Ressourceinfo resInfo;
    private Font fontBig, fontSmall;

    private Robot robot;

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

        Camera.setMapSize(mapWidth, mapHeight);
        Camera.setStartCoordinates(0, 0);

        try {
            fontBig = Font.loadFont(String.valueOf(ImageLoader.class.getResource("/rsc/font.ttf")), 64);
            fontSmall = Font.loadFont(String.valueOf(ImageLoader.class.getResource("/rsc/font.ttf")), 24);
        } catch (Exception e) {
            e.printStackTrace();
            fontBig = new Font(64);
        }

        robot = new Robot();
    }

    public void create (){
        resInfo =   new Ressourceinfo(0, 0, width, 30, fontSmall);
        minimap =   new Minimap(width-300, height-300, 300, 300, this);
        unitinfo =  new Unitinfo(0, height-350, 250, 350, fontSmall);
        connect =   new Button((width/2)-(width*(3./50.)), (height/2), width*(15./128.), height*(8./72.), "connect", fontBig, this);
        ready =     new Button((width/2)-(width*(3./50.)), (height/2)+(height*(10./72.)), width*(15./128.), height*(8./72.), "ready", fontBig, this);
        iptf =      new TextField(width*(1./12.), (height/2)-(height*(15./72.)), (width*(1./3.)), height*(8./72.), fontBig, "192.168.178.48");
        nametf =    new TextField(width*(7./12.), (height/2)-(height*(15./72.)), (width*(1./3.)), height*(8./72.), fontBig, "Username");
        connect.setDisabled(false);
        ready.setDisabled(true);

        canvas_main = new Canvas(width, height);
        root = new StackPane();
        gc_main = canvas_main.getGraphicsContext2D();
        dm.draw(gc_main, this);

        root.getChildren().add(canvas_main);
        scene = new Scene(root, width, height);
        scene.setOnKeyPressed(new KeyPressed(this));
        scene.setOnKeyReleased(new KeyReleased());
        scene.setOnMouseMoved(new MouseMoved(this, robot));
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

    public double[] getStageInfo (){
        double coord [] = null;
        if (stage != null){
            coord = new double[4];
            coord [0] = stage.getX();
            coord [1] = stage.getY();
            coord [2] = stage.getWidth();
            coord [3] = stage.getHeight();
        }
        return coord;
    }

    public void bConnectAction(){
        String ip = null, username = null;

        try {
            //ip
            ip = iptf.getText();
            while (ip == null || ip.equals("")){
                ip = ipDialogue();
            }
            while (!checkIPSyntax(ip)){
                ip = ipDialogue();
            }

            //username
            username = nametf.getText();
            while (username == null || username.equals("")){
                username = nameDialogue();
            }

            Main.searchConnection(ip, username);
            Player.setUsername(username);

        } catch (NullPointerException e){
            e.printStackTrace();
        }
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

    public void connectedDialogue(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("You have a connection to the Server!");

                alert.showAndWait();
            }
        });
    }

    private boolean checkIPSyntax(String ip){
        //Check if the String is a valid IP address
        return true;
    }

    public void buttonAction(Button b){
        if (b.getText() != null && b.getText() != ""){
            if (b.getText() == "connect"){
                bConnectAction();
            } else if (b.getText() == "ready"){
                Player.setReady(true);
                ready.setText("not ready");
            } else if (b.getText() == "not ready"){
                Player.setReady(false);
                ready.setText("ready");
            }
        }
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

    public Button getConnect() {
        return connect;
    }

    public void setConnect(Button connect) {
        this.connect = connect;
    }

    public Button getReady() {
        return ready;
    }

    public void setReady(Button ready) {
        this.ready = ready;
    }

    public TextField getIptf() {
        return iptf;
    }

    public void setIptf(TextField iptf) {
        this.iptf = iptf;
    }

    public TextField getNametf() {
        return nametf;
    }

    public void setNametf(TextField nametf) {
        this.nametf = nametf;
    }

    public Font getFontBig() {
        return fontBig;
    }

    public void setFontBig(Font fontBig) {
        this.fontBig = fontBig;
    }

    public Ressourceinfo getResInfo() {
        return resInfo;
    }

    public void setResInfo(Ressourceinfo resInfo) {
        this.resInfo = resInfo;
    }

    public void disconnect(){
        Gamestate.state = Gamestate_e.reconnect;
        //disconnect-fenster?
        System.out.println("disconnected on gui");
    }

    public void close(){
        Platform.exit();
        System.exit(0);
    }
}