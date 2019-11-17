package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Unitinfo extends Interface {

    private Image unitImg;

    public Unitinfo(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public void draw (GraphicsContext g){
        g.setLineWidth(3);
        g.setStroke(Color.AQUA);
        g.drawImage(unitImg, x, y, width, height/2);
        g.strokeLine(x ,y, x+width, y);
        g.strokeLine(x+width, y, x+width, y+height);
        g.strokeLine(x+width, y+height, x, y+height);
        g.strokeLine(x, y+height, x, y);
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Image getUnitImg() {
        return unitImg;
    }

    public void setUnitImg(Image unitImg) {
        this.unitImg = unitImg;
    }
}
