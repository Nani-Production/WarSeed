package controls;

import gui.Game_Gui;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
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
        if (mouseEvent.isPrimaryButtonDown()){
            double x = mouseEvent.getX(), y = mouseEvent.getY();
            if (collisionRequest(new Rectangle(gui.getUnitinfo().getX(), gui.getUnitinfo().getY(), gui.getUnitinfo().getWidth(), gui.getUnitinfo().getHeight()), x, y)){
                //TODO Die Funktionalität der Unitinfo machen
            } else if (collisionRequest(new Rectangle(gui.getMinimap().getX(), gui.getMinimap().getY(), gui.getMinimap().getWidth(), gui.getMinimap().getHeight()), x, y)){
                //TODO Die Funktionalität der Minimap machen
            } else {
                for (int i = 0; i < Player.getCharacters().size(); i++){
                    if (collisionRequest(new Rectangle(Player.getCharacters().get(i).getX()-Camera.getCamX(), Player.getCharacters().get(i).getY()-Camera.getCamY(), Player.getCharacters().get(i).getWidth(), Player.getCharacters().get(i).getHeight()), x, y)){
                        Player.selectCharacter(Player.getCharacters().get(i));
                    }
                }
                for (int i = 0; i < Player.getBuildings().size(); i++){
                    if (collisionRequest(new Rectangle(Player.getBuildings().get(i).getX()-Camera.getCamX(), Player.getBuildings().get(i).getY()-Camera.getCamY(), Player.getBuildings().get(i).getWidth(), Player.getBuildings().get(i).getHeight()), x, y)){
                        Player.selectBuilding(Player.getBuildings().get(i));
                    }
                }
            }
            //TODO Mausauswahl
            //Überprüfen, ob schon eine Einheit asugewählt ist
        } else if (mouseEvent.isSecondaryButtonDown()){
            if (Player.hasSelected()){
                boolean enemy = false;
                //TODO Überprüfen, ob da eine gegnerische Einheit steht
                //Dann angrifffunktion ausführen?

                if (!enemy && Player.getSelectedCharacter() != null){
                    Player.getSelectedCharacter().setMoveX(mouseEvent.getX()+Camera.getCamX());
                    Player.getSelectedCharacter().setMoveY(mouseEvent.getY()+Camera.getCamY());
                }
            }
        }
    }

    private boolean collisionRequest(Rectangle rec, double x, double y){
        return x >= rec.getX() && x < rec.getX()+rec.getWidth() && y >= rec.getY() && y < rec.getY()+rec.getHeight();
    }
}