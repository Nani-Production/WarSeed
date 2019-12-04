package loop;

import gui.Game_Gui;

public class RenderLoop implements Runnable {

    private Game_Gui gui;

    public RenderLoop(Game_Gui gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double FPS = 20.0;
        double ns = 1000000000 / FPS;
        double deltaTime = 0;


        while (true) {
            long currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / ns;
            lastTime = currentTime;

            if (deltaTime >= 1) {
                deltaTime = 0;
                gui.getGc_main().clearRect(0, 0, gui.getWidth(), gui.getHeight());
                gui.getDm().draw(gui.getGc_main(), gui);
            }
        }
    }
}