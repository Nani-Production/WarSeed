package draw;

import controls.Camera;
import data.Data;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Game_Gui;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import player.Player;

public class Draw_Main {
    public void draw(GraphicsContext g, Game_Gui gui){
        try {
            //g.drawImage(ImageLoader.image, 0, 0, 1000, 1000);
            //TODO alles
            //Menu
            if (Gamestate.state == Gamestate_e.menu){
                g.setFill(Color.BLACK);
                g.fillRect(0, 0, gui.getWidth(), gui.getHeight());
                gui.getConnect().draw(g);
                gui.getStart().draw(g);
                gui.getIptf().draw(g);
                gui.getNametf().draw(g);
                g.setStroke(Color.GREEN);
                g.setFont(new Font("Arial", 64));
                g.strokeText("GAMETITLE", (gui.getWidth()/3), 150);
            } else {
                //Content
                g.drawImage(ImageLoader.map, Camera.getCamX(), Camera.getCamY(), gui.getWidth(), gui.getHeight(), 0, 0, gui.getWidth(), gui.getHeight());
                for (int i = 0; i < Data.getListofLists().size(); i++){
                    if (Double.parseDouble(Data.getListofLists().get(i).get(5)) > Camera.getCamX() && Double.parseDouble(Data.getListofLists().get(i).get(5))+64 <= Camera.getCamX()+gui.getWidth() && Double.parseDouble(Data.getListofLists().get(i).get(6)) > Camera.getCamY() && Double.parseDouble(Data.getListofLists().get(i).get(6))+64 <= Camera.getCamY()+gui.getHeight()){
                        if (Data.getListofLists().get(i).get(0) == "building"){
                            g.drawImage(ImageLoader.image2, Double.parseDouble(Data.getListofLists().get(i).get(5))-Camera.getCamX(), Double.parseDouble(Data.getListofLists().get(i).get(6))-Camera.getCamY(), 64, 64);
                        } else if (Data.getListofLists().get(i).get(0) == "character"){
                            g.drawImage(ImageLoader.image, Double.parseDouble(Data.getListofLists().get(i).get(5))-Camera.getCamX(), Double.parseDouble(Data.getListofLists().get(i).get(6))-Camera.getCamY(), 64, 64);
                        }
                        g.setStroke(Color.RED);
                        if (Data.getListofLists().get(i).get(1) != Player.getUsername()){
                            g.strokeRect(Double.parseDouble(Data.getListofLists().get(i).get(5))-Camera.getCamX(), Double.parseDouble(Data.getListofLists().get(i).get(6))-Camera.getCamY(), 64, 64);
                        }
                    }
                }
                if (Player.unitIsSelected()){
                    g.setStroke(Color.YELLOW);
                    g.strokeRect(Double.parseDouble(Player.getSelectedUnit().get(5))-Camera.getCamX(), Double.parseDouble(Player.getSelectedUnit().get(6))-Camera.getCamY(), 64, 64);
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