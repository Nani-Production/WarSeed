package loop;

import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Gui;

public class GameLoop implements Runnable{

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double FPS = 30.0;
        double ns = 1000000000 / FPS;
        double deltaTime = 0;

        long timer = System.currentTimeMillis();

        while (true) {

            long currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / ns;
            lastTime = currentTime;

            if (deltaTime >= 1) {
                update();
                deltaTime = 0;
                render();
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
    }

    private void update(){
        //receive game data

        //Update game content
        if (Gamestate.state == Gamestate_e.menu){

        } else if (Gamestate.state == Gamestate_e.ingame){

        } else if (Gamestate.state == Gamestate_e.pause){

        }

        //send new game data
    }
    private void render(){
        Gui.gc_main.clearRect(0, 0, Gui.width, Gui.height);
        Gui.dm.draw(Gui.gc_main);
    }
}