package gui;

import draw.ImageLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Ressourceinfo extends Interface {
    private float ressource1 = 0, ressource2 = 0, ressource3 = 0;
    private Font font;

    public Ressourceinfo(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Ressourceinfo(double x, double y, double width, double height, Font font) {
        super(x, y, width, height);
        this.font = font;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setStroke(Color.GREEN);
        g.strokeRect(width*(1./5.), 0, width*(3./5.), height);
        g.drawImage(ImageLoader.image, width*(1./5.), 0, 20, 20);
        g.drawImage(ImageLoader.image, width*(2./5.), 0, 20, 20);
        g.drawImage(ImageLoader.image, width*(3./5.), 0, 20, 20);
        g.setFill(Color.GREEN);
        g.setStroke(Color.DARKGREEN);
        g.setFont(font);
        g.fillText(Float.toString(ressource1), width*(1./5.), 0, (width*(1./5.))-20);
        g.fillText(Float.toString(ressource2), width*(2./5.), 0, (width*(1./5.))-20);
        g.fillText(Float.toString(ressource3), width*(3./5.), 0, (width*(1./5.))-20);
    }

    public float getRessource1() {
        return ressource1;
    }

    public void setRessource1(float ressource1) {
        this.ressource1 = ressource1;
    }

    public float getRessource2() {
        return ressource2;
    }

    public void setRessource2(float ressource2) {
        this.ressource2 = ressource2;
    }

    public float getRessource3() {
        return ressource3;
    }

    public void setRessource3(float ressource3) {
        this.ressource3 = ressource3;
    }
}
