package controls;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyReleased implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.UP){
            Camera.stopUp();
        } if (keyEvent.getCode() == KeyCode.DOWN){
            Camera.stopDown();
        } if (keyEvent.getCode() == KeyCode.RIGHT){
            Camera.stopRight();
        } if (keyEvent.getCode() == KeyCode.LEFT){
            Camera.stopLeft();
        }
    }
}
