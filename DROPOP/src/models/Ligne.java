package models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import utils.MapDeplacement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.layout.Pane;

public class Ligne extends Commun{
	
	public Ligne(int xPosition, int yposition, Commun elementParent) {
		super(xPosition, yposition);
		// TODO Auto-generated constructor stub
	}
	
	public Ligne(){
		this(0, 0, null);
	}
	
	@Override
	public ObservableList<Commun> deplaceCommun(Pane rootPane, Commun ligne_source, Commun ligne_destination, int deltaX, int deltaY){
		
		ObservableList<Commun> retour;
		List retour_liste = new ArrayList<>();
		
		retour_liste = this.getContenu().stream()
	                                    .map(a -> MapDeplacement.deplaceLigne(rootPane,
	                                        		                          (Ligne) ligne_source,
	                                        		                          (Ligne) ligne_destination,
	                                        		                          a, 
	                                        		                          deltaX,
	                                        		                          deltaY))// mmmfff !
	                                      .collect(Collectors.toList());
		
		retour = FXCollections.observableArrayList(retour_liste);
		
		return retour;
	}
}
