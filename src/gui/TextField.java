package gui;

import draw.ImageLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextField extends Interface {
    private String text;
    private boolean isSelected, isHover;
    private Font font;

    public TextField(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.isSelected = false;
        this.isHover = false;
        this.text = "";
    }

    public TextField(double x, double y, double width, double height, Font font, String text) {
        super(x, y, width, height);
        this.text = text;
        this.isSelected = false;
        if (font == null){
            this.font = new Font(64);
        } else {
            this.font = font;
        }
        this.isHover = false;
    }

    public void draw (GraphicsContext g){
        g.setFont(font);
        g.setFill(Color.GREEN);
        g.setStroke(Color.DARKGREEN);
        if (isSelected){
            g.setFill(Color.YELLOW);
            g.setStroke(Color.ORANGE);
            g.setLineWidth(5);
            g.strokeRect(x, y, width, height);
        }
        g.drawImage(ImageLoader.textfield, x, y, width, height);
        g.setLineWidth(1);
        g.fillText(text, x+(width*(1./10.)), y+font.getSize(), width-(width*(2*(1./10.))));
        g.strokeText(text, x+(width*(1./10.)), y+font.getSize(), width-(width*(2*(1./10.))));
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
                                if (text.length() < 21){
                                    if (event.isShiftDown()){
                                        text = text+code.toUpperCase();
                                    } else {
                                        text = text+code.toLowerCase();
                                    }
                                    keepLooping = false;
                                }
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
