package gui;

import draw.ImageLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Ressourceinfo extends Interface {
    private long ressource1 = 0, ressource2 = 0, ressource3 = 0;
    private Font font;

    public Ressourceinfo(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Ressourceinfo(double x, double y, double width, double height, Font font) {
        super(x, y, width, height);
        this.font = font;
    }

    public Ressourceinfo(double x, double y, double width, double height, long ressource1, long ressource2, long ressource3, Font font) {
        super(x, y, width, height);
        this.ressource1 = ressource1;
        this.ressource2 = ressource2;
        this.ressource3 = ressource3;
        this.font = font;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setLineWidth(3);
        g.setStroke(Color.AQUA);
        g.strokeRect(width*(1./5.), 0, width*(3./5.), height);
        g.drawImage(ImageLoader.image, width*(1./5.), 0, height, height);
        g.drawImage(ImageLoader.image, width*(2./5.), 0, height, height);
        g.drawImage(ImageLoader.image, width*(3./5.), 0, height, height);
        g.setFill(Color.GREEN);
        g.setStroke(Color.DARKGREEN);
        g.setFont(font);
        g.fillText(Long.toString(ressource1), width*(1./5.)+height, 24, (width*(1./5.))-height);
        g.fillText(Long.toString(ressource2), width*(2./5.)+height, 24, (width*(1./5.))-height);
        g.fillText(Long.toString(ressource3), width*(3./5.)+height, 24, (width*(1./5.))-height);
        g.setLineWidth(1);
    }

    public long getRessource1() {
        return ressource1;
    }

    public void setRessource1(long ressource1) {
        this.ressource1 = ressource1;
    }

    public long getRessource2() {
        return ressource2;
    }

    public void setRessource2(long ressource2) {
        this.ressource2 = ressource2;
    }

    public long getRessource3() {
        return ressource3;
    }

    public void setRessource3(long ressource3) {
        this.ressource3 = ressource3;
    }
}
