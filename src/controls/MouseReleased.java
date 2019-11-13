package controls;

import gamestate.Gamestate;
import gamestate.Gamestate_e;
import javafx.event.EventHandler;
import javafx.print.PageLayout;
import javafx.scene.input.MouseEvent;
import player.Player;

public class MouseReleased implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (Gamestate.state == Gamestate_e.ingame){
        }
    }
}
