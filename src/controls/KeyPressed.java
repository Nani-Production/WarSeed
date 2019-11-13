package controls;

import gamestate.Gamestate;
import gamestate.Gamestate_e;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.EventListener;

public class KeyPressed implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent keyEvent) {
        if (Gamestate.state == Gamestate_e.ingame){
            if (keyEvent.getCode() == KeyCode.ESCAPE){
                Gamestate.state = Gamestate_e.pause;
            }
            if (keyEvent.getCode() == KeyCode.UP){
                Camera.moveUp(2);
            } if (keyEvent.getCode() == KeyCode.DOWN){
                Camera.moveDown(2);
            } if (keyEvent.getCode() == KeyCode.RIGHT){
                Camera.moveRight(2);
            } if (keyEvent.getCode() == KeyCode.LEFT){
                Camera.moveLeft(2);
            }
        } else if (Gamestate.state == Gamestate_e.pause){
            if (keyEvent.getCode() == KeyCode.ESCAPE){
                Gamestate.state = Gamestate_e.ingame;
            }
        }
    }
}
