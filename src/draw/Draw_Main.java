package draw;

import gamestate.Gamestate;
import gamestate.Gamestate_e;
import javafx.scene.canvas.GraphicsContext;

public class Draw_Main {
    public void draw(GraphicsContext g){
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
