package gui;

import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Button extends Interface{
    private String text;
    private Font font;
    private boolean isHover;
    private ActionEvent hover;

    public Button(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.text = "";
        this.font = Font.getDefault();
    }

    public Button(double x, double y, double width, double height, String text) {
        super(x, y, width, height);
        this.text = text;
        this.font = Font.getDefault();
    }

    public Button(double x, double y, double width, double height, String text, Font font) {
        super(x, y, width, height);
        this.text = text;
        this.font = font;
    }

    public void draw (GraphicsContext g){
        g.setStroke(Color.GREEN);
        if (isHover){
            g.setStroke(Color.AQUA);
        }
        g.strokeLine(x ,y, x+width, y);
        g.strokeLine(x+width, y, x+width, y+height);
        g.strokeLine(x+width, y+height, x, y+height);
        g.strokeLine(x, y+height, x, y);
        g.strokeText(text, x, y+64, width);
    }

    public void checkHover (double mx, double my){
        if (mx > x && mx <= x+width && my > y && my <= y+height){
            isHover = true;
        } else {
            isHover = false;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
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

    public boolean isHover() {
        return isHover;
    }

    public void setHover(boolean hover) {
        isHover = hover;
    }

    public ActionEvent getHover() {
        return hover;
    }

    public void setHover(ActionEvent hover) {
        this.hover = hover;
    }
}