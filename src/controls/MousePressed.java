package controls;

import data.Data;
import data.UnitDatabank;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Game_Gui;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import player.Player;

public class MousePressed implements EventHandler<MouseEvent> {

    Game_Gui gui;

    public MousePressed(Game_Gui gui) {
        this.gui = gui;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (Gamestate.state == Gamestate_e.menu) {
            if (mouseEvent.isPrimaryButtonDown()) {
                double x = mouseEvent.getX(), y = mouseEvent.getY();
                gui.getConnect().checkClick(x, y);
                gui.getReady().checkClick(x, y);
                gui.getIptf().checkClick(x, y);
                gui.getNametf().checkClick(x, y);
            }
        }
        if (Gamestate.state == Gamestate_e.ingame) {
            if (mouseEvent.isPrimaryButtonDown()) {
                double x = mouseEvent.getX(), y = mouseEvent.getY();
                if (collisionRequest(new Rectangle(gui.getUnitinfo().getX(), gui.getUnitinfo().getY(), gui.getUnitinfo().getWidth(), gui.getUnitinfo().getHeight()), x, y)) {
                    //TODO Die Funktionalität der Unitinfo machen
                } else if (collisionRequest(new Rectangle(gui.getMinimap().getX(), gui.getMinimap().getY(), gui.getMinimap().getWidth(), gui.getMinimap().getHeight()), x, y)) {
                    //TODO Die Funktionalität der Minimap machen
                } else {
                    for (int i = 0; i < Player.getCharacters().size(); i++) {
                        if (collisionRequest(new Rectangle(Double.parseDouble(Player.getCharacters().get(i).get(5)) - Camera.getCamX(), Double.parseDouble(Player.getCharacters().get(i).get(6)) - Camera.getCamY(), 64, 64), x, y)) {
                            Player.selectUnit(Player.getCharacters().get(i));
                        }
                    }
                    for (int i = 0; i < Player.getBuildings().size(); i++) {
                        if (collisionRequest(new Rectangle(Double.parseDouble(Player.getBuildings().get(i).get(5)) - Camera.getCamX(), Double.parseDouble(Player.getBuildings().get(i).get(6)) - Camera.getCamY(), 64, 64), x, y)) {
                            Player.selectUnit(Player.getBuildings().get(i));
                        }
                    }
                }
            } else if (mouseEvent.isSecondaryButtonDown()) {
                if (Player.unitIsSelected()) {
                    boolean enemy = false;
                    /*
                    if (Player.getSelectedUnit() != null && Player.getSelectedUnit().get(0).equals("character")){
                        for (int i = 0; i < Data.getListofLists().size(); i++) {
                            if (Data.getListofLists().get(i).get(1) != Player.getUsername()) {
                                Rectangle r = new Rectangle(Double.parseDouble(Data.getListofLists().get(i).get(5)), Double.parseDouble(Data.getListofLists().get(i).get(6)), 64, 64);
                                if (collisionRequest(r, mouseEvent.getX() + Camera.getCamX(), mouseEvent.getY() + Camera.getCamY())) {
                                    enemy = true;
                                    Circle c = new Circle(Double.parseDouble(Player.getSelectedUnit().get(5)), Double.parseDouble(Player.getSelectedUnit().get(6)), Double.parseDouble(UnitDatabank.getUnitInfo(Integer.parseInt(Player.getSelectedUnit().get(2)), "range")));
                                    if (generalCollision(c, r)){ //attack this enemy
                                        String attack[] = new String[2];
                                        attack[0] = Player.getSelectedUnit().get(4);
                                        attack[1] = Data.getListofLists().get(i).get(4);
                                        Player.getAttacks().add(attack);
                                    } else { //nicht in range, also muss die Einheit sich erstmal dorthin bewegen
                                        enemy = false;
                                    }
                                }
                            }
                        }
                        if (!enemy) {
                            Player.getSelectedUnit().set(7, Double.toString(mouseEvent.getX() + Camera.getCamX()));
                            Player.getSelectedUnit().set(8, Double.toString(mouseEvent.getY() + Camera.getCamY()));
                        }
                    }
                    */
                    if (!enemy) {
                        int index = -1;
                        for (int i = 0; i < Player.getCharacters().size(); i++){
                            if (Player.getCharacters().get(i).equals(Player.getSelectedUnit())){
                                index = i;
                            }
                        }
                        System.out.println("character is moving");
                        System.out.println("old1  "+Player.getCharacters().get(index).get(7)+"   "+Player.getCharacters().get(index).get(8));
                        System.out.println("old2  "+Player.getSelectedUnit().get(7)+"   "+Player.getSelectedUnit().get(8));
                        Player.getSelectedUnit().set(7, Double.toString(mouseEvent.getX() + Camera.getCamX()));
                        Player.getSelectedUnit().set(8, Double.toString(mouseEvent.getY() + Camera.getCamY()));
                        System.out.println("new1  "+Player.getCharacters().get(index).get(7)+"   "+Player.getCharacters().get(index).get(8));
                        System.out.println("new2  "+Player.getSelectedUnit().get(7)+"   "+Player.getSelectedUnit().get(8));
                    }
                }
            }
        }
    }
    private boolean collisionRequest(Rectangle rec, double x, double y) {
        return x >= rec.getX() && x < rec.getX() + rec.getWidth() && y >= rec.getY() && y < rec.getY() + rec.getHeight();
    }

    public static boolean generalCollision (Circle circle, Rectangle rectangle){
        double testX = circle.getCenterX(), testY = circle.getCenterY();
        if (circle.getCenterX() < rectangle.getX()){
            testX = rectangle.getX();                           // left edge
        }
        else if (circle.getCenterX() > rectangle.getX()+rectangle.getWidth()){
            testX = rectangle.getX()+rectangle.getWidth();      // right edge
        }
        if (circle.getCenterY() < rectangle.getY()){
            testY = rectangle.getY();                           // top edge
        }
        else if (circle.getCenterY() > rectangle.getY()+rectangle.getHeight()){
            testY = rectangle.getY()+rectangle.getHeight();     // bottom edge
        }
        double distX = circle.getCenterX()-testX;
        double distY = circle.getCenterY()-testY;
        double distance = Math.sqrt( (distX*distX) + (distY*distY) );
        if (distance <= circle.getRadius()) {
            return true;
        }
        return false;
    }
}