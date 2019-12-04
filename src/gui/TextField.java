package gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class TextField extends Interface {
    private String text;
    private boolean isSelected, isHover;

    public TextField(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.isSelected = false;
        this.isHover = false;
        this.text = "";
    }

    public TextField(double x, double y, double width, double height, String text) {
        super(x, y, width, height);
        this.text = text;
        this.isSelected = false;
        this.isHover = false;
    }

    public void draw (GraphicsContext g){
        g.setStroke(Color.GREEN);
        g.strokeText(text, x, y+64, width);
        if (isHover){
            g.setStroke(Color.AQUA);
        }
        if (isSelected){
            g.setStroke(Color.YELLOW);
        }
        g.strokeLine(x ,y, x+width, y);
        g.strokeLine(x+width, y, x+width, y+height);
        g.strokeLine(x+width, y+height, x, y+height);
        g.strokeLine(x, y+height, x, y);
    }

    public void checkHover (double mx, double my){
        if (mx > x && mx <= x+width && my > y && my <= y+height){
            isHover = true;
        } else {
            isHover = false;
        }
    }

    public void checkClick (double mx, double my){
        if (mx > x && mx <= x+width && my > y && my <= y+height){
            isSelected = true;
        } else {
            isSelected = false;
        }
    }

    public void checkKeyEvent(KeyEvent event){
        if (isSelected){
            if (event.getCode() == KeyCode.BACK_SPACE){
                if (text.length() > 0){
                    text = text.substring(0, text.length()-1);
                }
            } else if (event.getCode() == KeyCode.ESCAPE){
                isSelected = false;
            } else if (event.getCode() == KeyCode.PERIOD){
                text = text+".";
            } else {
                try {
                    boolean keepLooping = true;
                    String code = "A";
                    int count = 65;

                    for (int i = 0; i < 10; i++){
                        if (event.getCode() == KeyCode.valueOf("DIGIT"+i) || event.getCode() == KeyCode.valueOf("NUMPAD"+i)){
                            text = text+i;
                            keepLooping = false;
                        }
                    }
                    while (count <= 90 && keepLooping){
                        if (code.startsWith("[")){
                            keepLooping = false;
                            System.exit(-1);
                        } else {
                            if (event.getCode() == KeyCode.valueOf(code)) {
                                if (event.isShiftDown()){
                                    text = text+code.toUpperCase();
                                } else {
                                    text = text+code.toLowerCase();
                                }
                                keepLooping = false;
                            }
                            if (keepLooping){
                                byte[] b = code.getBytes();
                                for (int i = 0; i < b.length; i++){
                                    count = b[i];
                                    count++;
                                    count = count;
                                    b[i] = (byte)count;
                                }
                                code = new String(b);
                            }
                        }
                    }
                } catch (IllegalArgumentException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isHover() {
        return isHover;
    }

    public void setHover(boolean hover) {
        isHover = hover;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
