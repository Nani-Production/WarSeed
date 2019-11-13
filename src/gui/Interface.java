package gui;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Interface {
    public double x, y, width, height;

    public void draw (GraphicsContext g){
        g.setStroke(Color.BROWN);
        g.strokeLine(x ,y, x+width, y);
        g.strokeLine(x+width, y, x+width, y+height);
        g.strokeLine(x+width, y+height, x, y+height);
        g.strokeLine(x, y+height, x, y);
    }

    public Interface(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}