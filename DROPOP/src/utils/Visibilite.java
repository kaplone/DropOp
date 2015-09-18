package utils;

import models.Commun;
import dropop.Gui_controller;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Visibilite {
	
	private static int l = Gui_controller.getTailleQuadrillage();
	
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
	
	public static void highlightPos(DragEvent e1,
			                        Rectangle case_hl,
			                        Label affPositionVerticale,
			                        Label affPositionHorizontale,
			                        Button zero_ ){

		case_hl.relocate(Utils.arrondir(e1.getSceneX()) + l,
				         Utils.arrondir(e1.getSceneY()) + l);
	    case_hl.setVisible(true);
	    case_hl.toFront();
	    
	    affPositionVerticale.textProperty().set((Utils.arrondirVersPosition(e1.getSceneY())) + "");
	    affPositionVerticale.relocate(Utils.arrondir(e1.getSceneX())  + 60,
	    		                      Utils.arrondir(e1.getSceneY()) + 25);
	    affPositionVerticale.setVisible(true);
	    
	    
	    affPositionHorizontale.textProperty().set((Utils.arrondirVersPosition(e1.getSceneX())) + "");
	    affPositionHorizontale.relocate(Utils.arrondir(e1.getSceneX()) + 25,
	    		                        Utils.arrondir(e1.getSceneY()) + 60);
	    affPositionHorizontale.setVisible(true);
	    
	    zero_.setVisible(true);

		zero_.relocate(e1.getSceneX(),
				       e1.getSceneY());
		zero_.toFront();
		
	}
	
	public static void highlightPosDot(DragEvent e1,
						               Rectangle case_hl,
						               Button zero_ ){
		
		
		case_hl.relocate(Utils.arrondir(e1.getSceneX()) + l + 37.5,
				         Utils.arrondir(e1.getSceneY()) + l + 12.5);
		case_hl.setVisible(true);
		
		zero_.setVisible(true);
		
		zero_.relocate(e1.getSceneX(),
				       e1.getSceneY());
		zero_.toFront();
		
		case_hl.toFront(); // n'est pourtant pas au premier plan
		
	}
	
	
	public static void highlightPosSerie(DragEvent e1,
			                             Pane cases_hl,
			                             Label affPositionVerticale,
			                             Label affPositionHorizontale,
			                             Pane contenu,
			                             Button sourceButton){
		
		affPositionVerticale.textProperty().set((Utils.arrondirVersPosition(e1.getSceneY())) + "");
	    affPositionVerticale.relocate(Utils.arrondir(e1.getSceneX()) + 60,
	    		                      Utils.arrondir(e1.getSceneY()) + 25);
	    affPositionVerticale.setVisible(true);
	    
	    
	    affPositionHorizontale.textProperty().set((Utils.arrondirVersPosition(e1.getSceneX())) + "");
	    affPositionHorizontale.relocate(Utils.arrondir(e1.getSceneX()) + 25,
	    		                        Utils.arrondir(e1.getSceneY()) + 60);
	    affPositionHorizontale.setVisible(true);
		
		contenu.setPrefHeight(l);
		contenu.setVisible(true);
		cases_hl.setVisible(true);
		
		cases_hl.relocate(Utils.arrondir(e1.getSceneX()) - sourceButton.getLayoutX() + l,
				          Utils.arrondir(e1.getSceneY()) - sourceButton.getLayoutY() + l);
		
		contenu.relocate(e1.getSceneX() - sourceButton.getLayoutX(),
				         e1.getSceneY() - sourceButton.getLayoutY());
	}
	
    public static void highlight(DragEvent e1,
    		                     Rectangle case_hl,
    		                     Button zero_ ){
    	
		case_hl.relocate(Utils.arrondir(e1.getSceneX()) + l,
				         Utils.arrondir(e1.getSceneY()) + l);
		
	    case_hl.setVisible(true);
	    
	    zero_.setVisible(true);
	    
	    zero_.relocate(e1.getSceneX(),
	    		       e1.getSceneY());
		zero_.toFront();
		
	}

}
