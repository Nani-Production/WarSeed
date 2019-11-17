package controls;

import gamestate.Gamestate;
import gamestate.Gamestate_e;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.EventListener;

public class KeyPressed implements EventHandler<KeyEvent> {
    private int camspeed = 2;
    @Override
    public void handle(KeyEvent keyEvent) {
        if (Gamestate.state == Gamestate_e.ingame){
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
