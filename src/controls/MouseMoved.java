package controls;

import gui.Launcher_Gui;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseMoved implements EventHandler<MouseEvent> {
    private double frame1 = 0.1, frame2 = 0.05, frame3 = 0.025; //der prozentuale Rahmen, in dem man sich mit der Maus über die Map bewegen kann
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.getX() < Launcher_Gui.width*frame1 && mouseEvent.getX() >= Launcher_Gui.width-(Launcher_Gui.width*frame1) && mouseEvent.getY() < Launcher_Gui.height*frame1 && mouseEvent.getY() >= Launcher_Gui.height-(Launcher_Gui.height*frame1)){
            Camera.setMovementSpeed(1); //langsam
            if (mouseEvent.getX() < Launcher_Gui.width*frame2 && mouseEvent.getX() >= Launcher_Gui.width-(Launcher_Gui.width*frame2) && mouseEvent.getY() < Launcher_Gui.height*frame2 && mouseEvent.getY() >= Launcher_Gui.height-(Launcher_Gui.height*frame2)){
                Camera.setMovementSpeed(2); //schneller
                if (mouseEvent.getX() < Launcher_Gui.width*frame3 && mouseEvent.getX() >= Launcher_Gui.width-(Launcher_Gui.width*frame3) && mouseEvent.getY() < Launcher_Gui.height*frame3 && mouseEvent.getY() >= Launcher_Gui.height-(Launcher_Gui.height*frame3)){
                    Camera.setMovementSpeed(3); //am schnellsten
                }
            }
        }
    }
}
