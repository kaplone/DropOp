package utils;

import dropop.Gui_controller;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Visibilite {
	
	public static Button visibiliteDeplacement(Button zero_, Gui_controller gui){
		
		// objet visible pendant le déplacement
		Button copie;
		
		zero_.setVisible(false);
		copie = new Button();
		copie.setOnDragDetected(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				gui.detect2( event);
				
			}
		});
		
		return copie;
	}

}
