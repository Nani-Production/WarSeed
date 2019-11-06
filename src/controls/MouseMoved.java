package controls;

import gui.Gui;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseMoved implements EventHandler<MouseEvent> {
    private double frame1 = 0.1, frame2 = 0.05, frame3 = 0.025; //der prozentuale Rahmen, in dem man sich mit der Maus über die Map bewegen kann
    private double frameSpeed; //?
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getX() < Gui.width*frame1 && mouseEvent.getX() >= Gui.width-(Gui.width*frame1) && mouseEvent.getY() < Gui.height*frame1 && mouseEvent.getY() >= Gui.height-(Gui.height*frame1)){
            frameSpeed = 1; //langsam
            if (mouseEvent.getX() < Gui.width*frame2 && mouseEvent.getX() >= Gui.width-(Gui.width*frame2) && mouseEvent.getY() < Gui.height*frame2 && mouseEvent.getY() >= Gui.height-(Gui.height*frame2)){
                frameSpeed = 2; //schneller
                if (mouseEvent.getX() < Gui.width*frame3 && mouseEvent.getX() >= Gui.width-(Gui.width*frame3) && mouseEvent.getY() < Gui.height*frame3 && mouseEvent.getY() >= Gui.height-(Gui.height*frame3)){
                    frameSpeed = 3; //am schnellsten
                }
            }
        }
    }
}
