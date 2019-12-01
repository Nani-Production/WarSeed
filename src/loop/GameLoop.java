package loop;

import controls.Camera;
import gamestate.Gamestate;
import gamestate.Gamestate_e;

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
                /*
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                 */
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
            //Moving the Camera
            Camera.moveCam();


        } else if (Gamestate.state == Gamestate_e.pause){

        }

        //send new game data
    }
}