package draw;

import controls.Camera;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Game_Gui;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw_Main {
    public void draw(GraphicsContext g, Game_Gui gui){
        try {
            //g.drawImage(ImageLoader.image, 0, 0, 1000, 1000);
            //TODO alles
            //Menu
            if (Gamestate.state == Gamestate_e.menu){

            } else {
                //Content
                g.drawImage(ImageLoader.image, Camera.getCamX(), Camera.getCamY(), gui.getWidth(), gui.getHeight(), 0, 0, gui.getWidth(), gui.getHeight());
                //Pause
                if (Gamestate.state == Gamestate_e.pause) {

                }
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}