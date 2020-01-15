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
    private int counter = 0;
    private final double FPS = 30.0;

    @Override
    public void run() {
        long lastTime = System.nanoTime();
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

            if (counter < FPS){
                counter++;
            } else if (counter >= FPS){
                counter = 0;
                //update ressources
                for (int i = 0; i < Player.getBuildings().size(); i++){
                    if (Player.getBuildings().get(i).get(0).equals("building")){
                        try {
                            gui.getResInfo().setRessource1(gui.getResInfo().getRessource1()+Long.parseLong(Player.getBuildings().get(i).get(7)));
                            gui.getResInfo().setRessource2(gui.getResInfo().getRessource2()+Long.parseLong(Player.getBuildings().get(i).get(8)));
                            gui.getResInfo().setRessource3(gui.getResInfo().getRessource3()+Long.parseLong(Player.getBuildings().get(i).get(9)));
                        } catch (IndexOutOfBoundsException e){
                            e.printStackTrace();
                            System.out.println("type "+Player.getBuildings().get(i).get(0)+" size "+Player.getBuildings().get(i).size());
                        }
                    }
                }
            }
        }
    }
}