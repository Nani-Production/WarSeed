package loop;

import gui.Gui;

public class GameLoop implements Runnable{
    private static long systemtime = 0;

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


        //Systemtime
        if (systemtime >= 9000000000000000000L) {
            systemtime = 0;
        }
    }
    private void render(){
        Gui.gc_main.clearRect(0, 0, Gui.width, Gui.height);
        Gui.dm.draw(Gui.gc_main);
    }
    public static long getSystemtime() {
        return systemtime;
    }

    public static void setSystemtime(long systemtime) {
        GameLoop.systemtime = systemtime;
    }

}
