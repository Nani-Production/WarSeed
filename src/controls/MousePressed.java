package controls;

import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Game_Gui;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import player.Player;

public class MousePressed implements EventHandler<MouseEvent> {

    Game_Gui gui;

    public MousePressed(Game_Gui gui){
        this.gui = gui;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (Gamestate.state == Gamestate_e.menu){
            if (mouseEvent.isPrimaryButtonDown()){
                double x = mouseEvent.getX(), y = mouseEvent.getY();
                gui.getConnect().checkClick(x, y);
                gui.getReady().checkClick(x, y);
                gui.getIptf().checkClick(x, y);
                gui.getNametf().checkClick(x, y);
            }
        }
        if (Gamestate.state == Gamestate_e.ingame){
            if (mouseEvent.isPrimaryButtonDown()){
                double x = mouseEvent.getX(), y = mouseEvent.getY();
                if (collisionRequest(new Rectangle(gui.getUnitinfo().getX(), gui.getUnitinfo().getY(), gui.getUnitinfo().getWidth(), gui.getUnitinfo().getHeight()), x, y)){
                    //TODO Die Funktionalität der Unitinfo machen
                } else if (collisionRequest(new Rectangle(gui.getMinimap().getX(), gui.getMinimap().getY(), gui.getMinimap().getWidth(), gui.getMinimap().getHeight()), x, y)){
                    //TODO Die Funktionalität der Minimap machen
                } else {
                    for (int i = 0; i < Player.getCharacters().size(); i++){
                        if (collisionRequest(new Rectangle(Double.parseDouble(Player.getCharacters().get(i).get(5))-Camera.getCamX(), Double.parseDouble(Player.getCharacters().get(i).get(6))-Camera.getCamY(), 64, 64), x, y)){
                            Player.selectUnit(Player.getCharacters().get(i));
                        }
                    }
                    for (int i = 0; i < Player.getBuildings().size(); i++){
                        if (collisionRequest(new Rectangle(Double.parseDouble(Player.getBuildings().get(i).get(5))-Camera.getCamX(), Double.parseDouble(Player.getBuildings().get(i).get(6))-Camera.getCamY(), 64, 64), x, y)){
                            Player.selectUnit(Player.getBuildings().get(i));
                        }
                    }
                }
            } else if (mouseEvent.isSecondaryButtonDown()){
                if (Player.unitIsSelected()){
                    boolean enemy = false;
                    //TODO Überprüfen, ob da eine gegnerische Einheit steht
                    //Dann angrifffunktion ausführen? bzw wenn nicht in Range, dann dorthin bewegen

                    if (!enemy && Player.getSelectedUnit() != null && Player.getSelectedUnit().get(4).equals("character")){
                        Player.getSelectedUnit().set(5, Double.toString(mouseEvent.getX()+Camera.getCamX()));
                        Player.getSelectedUnit().set(6, Double.toString(mouseEvent.getY()+Camera.getCamY()));
                    }
                }
            }
        }
    }

    private boolean collisionRequest(Rectangle rec, double x, double y){
        return x >= rec.getX() && x < rec.getX()+rec.getWidth() && y >= rec.getY() && y < rec.getY()+rec.getHeight();
    }
}