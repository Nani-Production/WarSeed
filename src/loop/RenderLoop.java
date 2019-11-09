package loop;

import gui.Launcher_Gui;

public class RenderLoop implements Runnable {
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
                deltaTime = 0;
                Launcher_Gui.gc_main.clearRect(0, 0, Launcher_Gui.width, Launcher_Gui.height);
                Launcher_Gui.dm.draw(Launcher_Gui.gc_main);
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
    }
}
