package gui;

import data.UnitDatabank;
import draw.ImageLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import player.Player;

public class Unitinfo extends Interface {
    private Font font;

    public Unitinfo(double x, double y, double width, double height, Font font) {
        super(x, y, width, height);
        this.font = font;
    }

    public void draw (GraphicsContext g){
        g.setLineWidth(3);
        g.setStroke(Color.AQUA);
        g.drawImage(ImageLoader.unitInfobg, x, y, width, height);
        g.setFill(Color.DARKGRAY);
        g.fillRect(x+10, y+10, (width/2), (width/2));
        g.drawImage(Player.getSelecetUnitImg(), x+10, y+10, (width/2), (width/2));
        //g.strokeRect(x+10, y+10, width-20, width-20);
        if (Player.unitIsSelected()){
            g.setFill(Color.GREEN);
            g.fillText("name:      "+ Player.getSelectedUnit().get(4), x, y+30+(width/2), width-20);
            if (Player.getSelectedUnit().get(0).equals("character")){
                g.fillText("attack:    "+ UnitDatabank.getUnitInfo(Integer.parseInt(Player.getSelectedUnit().get(2)), "attack"), x, y+60+(width/2), width-20);
                g.fillText("speed:     "+ UnitDatabank.getUnitInfo(Integer.parseInt(Player.getSelectedUnit().get(2)), "speed"), x, y+90+(width/2), width-20);
                g.fillText("range:     "+ UnitDatabank.getUnitInfo(Integer.parseInt(Player.getSelectedUnit().get(2)), "range"), x, y+120+(width/2), width-20);
                g.fillText("health:    "+Player.getSelectedUnit().get(3)+"/"+UnitDatabank.getUnitInfo(Integer.parseInt(Player.getSelectedUnit().get(2)), "maxHealth"), x, y+150+(width/2), width-20);
            } else if (Player.getSelectedUnit().get(0).equals("building")){
                g.fillText("health:    "+Player.getSelectedUnit().get(3)+"/"+UnitDatabank.getUnitInfo(Integer.parseInt(Player.getSelectedUnit().get(2)), "maxHealth"), x, y+60+(width/2), width-20);

                g.drawImage(ImageLoader.ressource1, x+(width/2)+15, y+6, 25, 25);
                g.drawImage(ImageLoader.ressource2, x+(width/2)+15, y+36, 25, 25);
                g.drawImage(ImageLoader.ressource3, x+(width/2)+15, y+66, 25, 25);

                g.fillText("+"+Player.getSelectedUnit().get(7)+"/s",x+(width/2)+40, y+30, width-20);
                g.fillText("+"+Player.getSelectedUnit().get(8)+"/s",x+(width/2)+40, y+60, width-20);
                g.fillText("+"+Player.getSelectedUnit().get(9)+"/s",x+(width/2)+40, y+90, width-20);
            }
        }
        //g.strokeRect(x, y, width, height);
        g.setLineWidth(1);
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

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
