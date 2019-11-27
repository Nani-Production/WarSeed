package draw;

import gui.Launcher_Gui;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Launcher {
    public void draw(GraphicsContext g){
        //g.drawImage(ImageLoader.image, 0, 0, Launcher_Gui.width, Launcher_Gui.height);
        //TODO Das Hintergrundbild und ev die Buttons in einheitlichem Design machen
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 100, 100);
    }
}
