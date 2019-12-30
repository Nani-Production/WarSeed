package gui;

import controls.Camera;
import data.Data;
import data.UnitDatabank;
import draw.ImageLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import player.Player;

import java.util.concurrent.atomic.DoubleAccumulator;

public class Minimap extends Interface {
    private Image map;
    private Game_Gui gui;

    public Minimap(double x, double y, double width, double height, Game_Gui gui) {
        super(x, y, width, height);
        this.map = screenshotMap();
        this.gui = gui;
    }

    public void draw (GraphicsContext g){
        g.drawImage(ImageLoader.minimapbg, x, y, width, height);
        g.drawImage(ImageLoader.map, x+10, y+10, width-20, height-20);
        g.setStroke(Color.WHITE);
        g.setFill(Color.WHITE);
        if (Player.isDeveloperMode()){
            g.setStroke(Color.GRAY);
            g.fillRect(x+10, y+10, width-20, height-20);
        }
        double wRatio = 0, hRation = 0, xRatio = 0, yRatio = 0;
        wRatio = (width-20)/gui.getWidth();
        hRation = ((height/2)-20)/gui.getHeight();
        xRatio = Camera.getCamX()/ImageLoader.map.getWidth();
        yRatio = Camera.getCamY()/ImageLoader.map.getHeight();

        g.strokeRect(x+10+xRatio*(width-20), y+10+yRatio*(height-20), wRatio*(width-20), hRation*(height-20));
        for (int i = 0; i < Data.getListofLists().size(); i++){
            if (Data.getListofLists().get(i).get(0).equals("character")){
                wRatio = (width-20)/64;
                hRation = ((height/2)-20)/64;
                xRatio = Double.parseDouble(Data.getListofLists().get(i).get(5))/ImageLoader.map.getWidth();
                yRatio = Double.parseDouble(Data.getListofLists().get(i).get(6))/ImageLoader.map.getHeight();
                if (!Data.getListofLists().get(i).get(1).equals(Player.getUsername())){
                    g.setFill(Color.RED);
                } else {
                    g.setFill(Color.BLUE);
                }
            } else if (Data.getListofLists().get(i).get(0).equals("building")){
                //TODO sizegrößen in die Datenbank schreiben
                double size = 0;
                if (Data.getListofLists().get(i).get(2).equals(Integer.toString(UnitDatabank.NEXUS))){
                    size = 200;
                } else if (Data.getListofLists().get(i).get(2).equals(Integer.toString(UnitDatabank.VILLAGE))){
                    size = 100;
                }
                wRatio = size/(width-20);
                xRatio = Double.parseDouble(Data.getListofLists().get(i).get(5))/ImageLoader.map.getWidth();
                yRatio = Double.parseDouble(Data.getListofLists().get(i).get(6))/ImageLoader.map.getHeight();
                if (!Data.getListofLists().get(i).get(1).equals(Player.getUsername())){
                    g.setFill(Color.RED);
                } else {
                    g.setFill(Color.GREEN);
                }
            }
            g.fillRect(x+10+xRatio*(width-20), y+10+yRatio*(height-20), wRatio, wRatio);

        }

    }

    public boolean clicked(double mx, double my){
        if (mx > x && mx <= x+width && my > y && my <= y+height){
            return true;
        }
        return false;
    }

    private Image screenshotMap(){  //not finished
        return null;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Image getMap() {
        return map;
    }

    public void setMap(Image map) {
        this.map = map;
    }
}
