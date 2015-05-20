package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bloc extends Commun {
	
    private ObservableList<Ligne> contenu = FXCollections.observableArrayList();
    

    public Bloc(int xPosition, int yposition, Commun elementParent) {
 		super(xPosition, yposition, elementParent);
 		// TODO Auto-generated constructor stub
 	}


	public ObservableList<Ligne> getContenu() {
		return contenu;
	}
	
	public void setContenu(ObservableList<Ligne> contenu) {
		this.contenu = contenu;
	}

}
