package loop;

import controls.Camera;
import gamestate.Gamestate;
import gamestate.Gamestate_e;

public class GameLoop implements Runnable{

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double UPS = 20.0; //updates per second
        double ns = 1000000000 / UPS;
        double deltaTime = 0;

        while (true) {

            long currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / ns;
            lastTime = currentTime;

            if (deltaTime >= 1) {
                update();
                deltaTime = 0;
            }
        }
    }

    private void update(){
        if (Gamestate.state == Gamestate_e.ingame){
            //Moving the Camera
            Camera.moveCam();


        } else if (Gamestate.state == Gamestate_e.pause){

        }
    }
}