package utils;

import java.util.Map;

import utils.Utils;
import dropop.Gui_controller;
import javafx.collections.ObservableMap;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import models.elements.Chiffre;
import models.elements.Ligne;

public class MiseAJour {
	
	/**
	 * 
	 * @param bouton : le contenu
	 * @param case_hl : les coordonnées
	 */
	
	public static void miseAJourPosition(Button bouton, Rectangle case_hl){
	
		bouton.setLayoutX(case_hl.getLayoutX());
		bouton.setLayoutY(case_hl.getLayoutY());
		
	}
	
    public void miseAJourListe(){
		
	}


}
