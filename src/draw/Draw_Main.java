package draw;

import controls.Camera;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Game_Gui;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import player.Player;

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
                //g.drawImage(ImageLoader.map, Camera.getCamX(), Camera.getCamY(), gui.getWidth(), gui.getHeight());
                System.out.println("sizes: "+Player.getCharacters().size()+"    "+Player.getBuildings().size());
                for (int i = 0; i < Player.getCharacters().size(); i++){
                    if (Player.getCharacters().get(i).getX() > Camera.getCamX() && Player.getCharacters().get(i).getX()+Player.getCharacters().get(i).getWidth() <= Camera.getCamX()+gui.getWidth() && Player.getCharacters().get(i).getY() > Camera.getCamY() && Player.getCharacters().get(i).getY()+Player.getCharacters().get(i).getHeight() <= Camera.getCamY()+gui.getHeight()){
                        System.out.println("unit drawn");
                        g.drawImage(ImageLoader.image, Player.getCharacters().get(i).getX()-Camera.getCamX(), Player.getCharacters().get(i).getY()-Camera.getCamY(), Player.getCharacters().get(i).getWidth(), Player.getCharacters().get(i).getHeight());
                    }
                }
                for (int i = 0; i < Player.getBuildings().size(); i++){
                    if (Player.getBuildings().get(i).getX() > Camera.getCamX() && Player.getBuildings().get(i).getX()+Player.getBuildings().get(i).getWidth() <= Camera.getCamX()+gui.getWidth() && Player.getBuildings().get(i).getY() > Camera.getCamY() && Player.getBuildings().get(i).getY()+Player.getBuildings().get(i).getHeight() <= Camera.getCamY()+gui.getHeight()){
                        System.out.println("building drawn");
                        g.drawImage(ImageLoader.image, Player.getBuildings().get(i).getX()-Camera.getCamX(), Player.getBuildings().get(i).getY()-Camera.getCamY(), Player.getBuildings().get(i).getWidth(), Player.getBuildings().get(i).getHeight());
                    }
                }
                System.out.println("selected: "+Player.hasSelected());
                if (Player.hasSelected()){
                    g.setStroke(Color.RED);
                    if (Player.getSelectedCharacter() != null){
                        g.strokeRect(Player.getSelectedCharacter().getX()-Camera.getCamX(), Player.getSelectedCharacter().getY()-Camera.getCamY(), Player.getSelectedCharacter().getWidth(), Player.getSelectedCharacter().getHeight());
                    } else {
                        g.strokeRect(Player.getSelectedBuilding().getX()-Camera.getCamX(), Player.getSelectedBuilding().getY()-Camera.getCamY(), Player.getSelectedBuilding().getWidth(), Player.getSelectedBuilding().getHeight());
                    }
                }

                //Interface
                gui.getMinimap().draw(g);
                gui.getUnitinfo().draw(g);
                //Pause
                if (Gamestate.state == Gamestate_e.pause) {

                }
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}