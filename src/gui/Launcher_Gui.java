package gui;

import draw.Draw_Launcher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import player.Player;
import sample.Main;

import java.util.Optional;

public class Launcher_Gui {
    public final static double width = 700, height = 400;
    private Draw_Launcher dm;
    private GraphicsContext gc_main;
    private Canvas canvas_main;
    private Scene scene;
    private StackPane root;
    private TextField ipTF, usernameTF;
    private Text text1, text2;
    private Stage stage;
    private Button bStart;
    private Button bConnect;
    //public Font myFont = Font.loadFont(getClass().getResourceAsStream("/resources/Guardians.ttf"), 24);

    public void init (Stage stage) {
        dm = new Draw_Launcher();
        this.stage = stage;
    }

    public void create () {

        bStart = new Button("start");
        bStart.setPrefSize(50,50);
        bStart.setVisible(true);
        bStart.setDisable(true);
        bStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage newWindow = new Stage();
                Main.startGame(newWindow);
            }
        });

        ipTF = new TextField("");
        ipTF.setMinSize(100, 50);
        ipTF.setMaxSize(150, 50);
        ipTF.setVisible(true);

        usernameTF = new TextField("UN");
        usernameTF.setMinSize(100, 50);
        usernameTF.setMaxSize(150, 50);
        System.out.println(usernameTF.getWidth());
        usernameTF.setVisible(true);

        text1 = new Text("Server-IP");
        text1.setVisible(true);

        text2 = new Text("Username");
        text2.setVisible(true);

        bConnect = new Button("connect to Server");
        bConnect.setMinSize(100, 50);
        bConnect.setMaxSize(150, 50);

        bConnect.setVisible(true);

        Button bStopSearch = new Button("stop Search/Connection");
        bStopSearch.setMinSize(100, 50);
        bStopSearch.setMaxSize(150, 50);
        bStopSearch.setVisible(true);
        bStopSearch.setTranslateX(-100);
        bStopSearch.setTranslateY(50);
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

        //SplitPane
        FlowPane up_fp, down_fp, ipLog, unLog, buttons;
        ipLog = new FlowPane(text1, ipTF);
        ipLog.setTranslateX((width/2)-ipLog.getWidth()*2);
        unLog = new FlowPane(text2, usernameTF);
        unLog.setTranslateX((width/2)-unLog.getWidth()/2);
        unLog.setTranslateY(50);
        buttons = new FlowPane(bConnect, bStart);
        buttons.setTranslateX((width/2)-buttons.getWidth());
        up_fp = new FlowPane(ipLog, unLog);
        down_fp = new FlowPane(buttons);
        SplitPane splitPane = new SplitPane(up_fp, down_fp);
        splitPane.setOrientation(Orientation.VERTICAL);

        canvas_main = new Canvas(width, height);
        root = new StackPane();
        gc_main = canvas_main.getGraphicsContext2D();
        dm.draw(gc_main);

        root.getChildren().add(canvas_main);
        root.getChildren().addAll(splitPane);
        scene = new Scene(root, width, height);

        stage.setTitle("WarSeed Launcher");
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

        //ip
        ip = ipTF.getText();
        while (ip.equals("") || ip == null){
            ip = ipDialogue();
        }
        while (!checkIPSyntax(ip)){
            ip = ipDialogue();
        }

        //username
        username = usernameTF.getText();
        while (username.equals("") || username == null){
            username = nameDialogue();
        }

        System.out.println(ip+"+"+username);
        Main.startConnection(ip, username);
        Player.setUsername(username);

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

    public void close(){
        Platform.exit();
    }

    private boolean checkIPSyntax(String ip){
        //Check if the String is a valid IP address
        return true;
    }

    public Button getbStart() {
        return bStart;
    }

    public Draw_Launcher getDm() {
        return dm;
    }

    public void setDm(Draw_Launcher dm) {
        this.dm = dm;
    }

    public GraphicsContext getGc_main() {
        return gc_main;
    }

    public void setGc_main(GraphicsContext gc_main) {
        this.gc_main = gc_main;
    }

    public Button getbConnect() {
        return bConnect;
    }

    public void setbConnect(Button bConnect) {
        this.bConnect = bConnect;
    }
}
