package controls;

import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Game_Gui;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.EventListener;

public class KeyPressed implements EventHandler<KeyEvent> {
    private int camspeed = 2;
    private Game_Gui gui;

    public KeyPressed(Game_Gui gui) {
        this.gui = gui;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (Gamestate.state == Gamestate_e.menu){
            gui.getIptf().checkKeyEvent(keyEvent);
            gui.getNametf().checkKeyEvent(keyEvent);
        } else if (Gamestate.state == Gamestate_e.ingame){
            if (keyEvent.getCode() == KeyCode.ESCAPE){
                Gamestate.state = Gamestate_e.pause;
            }
            if (keyEvent.getCode() == KeyCode.UP){
                Camera.moveUp(camspeed);
            } if (keyEvent.getCode() == KeyCode.DOWN){
                Camera.moveDown(camspeed);
            } if (keyEvent.getCode() == KeyCode.RIGHT){
                Camera.moveRight(camspeed);
            } if (keyEvent.getCode() == KeyCode.LEFT){
                Camera.moveLeft(camspeed);
            }
        } else if (Gamestate.state == Gamestate_e.pause){
            if (keyEvent.getCode() == KeyCode.ESCAPE){
                Gamestate.state = Gamestate_e.ingame;
            }
        }
    }
}
