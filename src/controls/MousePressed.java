package controls;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MousePressed implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()){
            //TODO Mausauswahl
            //Zuerst überprüfen, ob an der Stelle ein Interfaceelement ist
            //Überprüfe, ob an der Stelle eine Einheit oder Gebäude steht
            //Überprüfen, ob schon eine Einheit asugewählt ist
        } else if (mouseEvent.isSecondaryButtonDown()){
            //Überpfüfen, ob feindliche Einheit dort ist
        }
    }
}
