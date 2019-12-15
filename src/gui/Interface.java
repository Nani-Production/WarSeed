package gui;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonBase;
import javafx.scene.paint.Color;

public class Interface {
    public double x, y, width, height;

    public void draw(GraphicsContext g) {
        g.setStroke(Color.GREEN);
        g.strokeRect(x, y, width, height);
    }

    public Interface(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}