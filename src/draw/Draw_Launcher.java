package draw;

import javafx.scene.canvas.GraphicsContext;

public class Draw_Launcher {
    public void draw(GraphicsContext g){
        g.drawImage(ImageLoader.image, 0, 0, 1000, 1000);
    }
}
