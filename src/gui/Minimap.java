package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Minimap extends Interface {
    private double cameraX, cameraY;
    private Image map;

    public Minimap(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.map = screenshotMap();
    }

    public Minimap(double x, double y, double width, double height, double cameraX, double cameraY) {
        super(x, y, width, height);
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        this.map = screenshotMap();
    }

    public void draw (GraphicsContext g){
        g.setLineWidth(3);
        g.setStroke(Color.AQUA);
        g.drawImage(map, x, y, width, height);
        g.strokeLine(x ,y, x+width, y);
        g.strokeLine(x+width, y, x+width, y+height);
        g.strokeLine(x+width, y+height, x, y+height);
        g.strokeLine(x, y+height, x, y);
        g.setLineWidth(1);
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

    public double getCameraX() {
        return cameraX;
    }

    public void setCameraX(double cameraX) {
        this.cameraX = cameraX;
    }

    public double getCameraY() {
        return cameraY;
    }

    public void setCameraY(double cameraY) {
        this.cameraY = cameraY;
    }

    public Image getMap() {
        return map;
    }

    public void setMap(Image map) {
        this.map = map;
    }
}
