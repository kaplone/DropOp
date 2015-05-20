package dropop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ligne extends Positionnable {
	
	final private ObservableList<Positionnable> contenuLigne = FXCollections.observableArrayList();

	public ObservableList<Positionnable> getContenuLigne() {
		return contenuLigne;
	}
	
	

}
