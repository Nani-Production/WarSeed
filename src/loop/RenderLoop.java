package loop;

import controls.Camera;
import data.Data;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Game_Gui;
import player.Player;

public class RenderLoop implements Runnable {

    private Game_Gui gui;

    public RenderLoop(Game_Gui gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double FPS = 30.0;
        double ns = 1000000000 / FPS;
        double deltaTime = 0;


        while (true) {
            long currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / ns;
            lastTime = currentTime;

            if (deltaTime >= 1) {
                deltaTime = 0;
                try {
                    gui.getGc_main().clearRect(0, 0, gui.getWidth(), gui.getHeight());
                    gui.getDm().draw(gui.getGc_main(), gui);
                    update();
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void update(){
        if (Gamestate.state == Gamestate_e.ingame) {
            Camera.moveCam();
        }
    }
}