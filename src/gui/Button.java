package gui;

import draw.ImageLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Button extends Interface {
    private String text;
    private Font font;
    private boolean isHover, isDisabled;
    private Game_Gui gui;

    public Button(double x, double y, double width, double height, Game_Gui gui) {
        super(x, y, width, height);
        this.text = "";
        this.font = Font.getDefault();
        this.isDisabled = false;
    }

    public Button(double x, double y, double width, double height, String text, Game_Gui gui) {
        super(x, y, width, height);
        this.text = text;
        this.font = Font.getDefault();
        this.isDisabled = false;
        this.gui = gui;
    }

    public Button(double x, double y, double width, double height, String text, Font font, Game_Gui gui) {
        super(x, y, width, height);
        this.text = text;
        if (font == null){
            this.font = new Font(64);
        } else {
            this.font = font;
        }
        this.gui = gui;
    }

    public void draw (GraphicsContext g){
        g.setFont(font);
        if (isHover){
            g.setFill(Color.DARKBLUE);
            g.setStroke(Color.BLUE);
            g.setLineWidth(5);
            g.strokeRect(x, y, width, height);
        } else {
            g.setFill(Color.GREEN);
            g.setStroke(Color.DARKGREEN);
        }
        g.drawImage(ImageLoader.button, x, y, width, height);
        g.setLineWidth(1);
        g.fillText(text, x+(width*(1./10.)), y+64, width-(2*(width*(1./10.))));
        g.strokeText(text, x+(width*(1./10.)), y+64, width-(2*(width*(1./10.))));
        if (isDisabled){
            g.setFill(new Color(0, 0, 0, 0.5));
            g.fillRect(x, y, width, height);
        }
    }

    public void checkHover (double mx, double my){
        if (!isDisabled){
            if (mx > x && mx <= x+width && my > y && my <= y+height){
                isHover = true;
            } else {
                isHover = false;
            }
        }
    }

    public void checkClick (double mx, double my){
        if (!isDisabled){
            if (mx > x && mx <= x+width && my > y && my <= y+height){
                gui.buttonAction(this);
            } else {
                isHover = false;
            }
        }
    }

    public boolean isHover() {
        return isHover;
    }

    public void setHover(boolean hover) {
        isHover = hover;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
        if (disabled){
            isHover = false;
        }
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
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

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}