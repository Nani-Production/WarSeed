package draw;

import gamestate.Gamestate;
import gamestate.Gamestate_e;
import javafx.scene.canvas.GraphicsContext;

public class Draw_Main {
    public void draw(GraphicsContext g){
        //g.drawImage(ImageLoader.image, 0, 0, 1000, 1000);


        //Menu
        if (Gamestate.state == Gamestate_e.menu){

        } else {
            //Content

            //Pause
            if (Gamestate.state == Gamestate_e.pause) {

            }
        }
    }
}