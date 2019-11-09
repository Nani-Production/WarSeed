package controls;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.EventListener;

public class KeyPressed implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.UP){
            Camera.moveUp(2);
        } if (keyEvent.getCode() == KeyCode.DOWN){
            Camera.moveDown(2);
        } if (keyEvent.getCode() == KeyCode.RIGHT){
            Camera.moveRight(2);
        } if (keyEvent.getCode() == KeyCode.LEFT){
            Camera.moveLeft(2);
        }
    }
}
