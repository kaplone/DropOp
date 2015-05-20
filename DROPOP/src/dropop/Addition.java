package dropop;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Addition extends Positionnable {
	
	final private StringProperty signeOperation = new SimpleStringProperty("+");
	
	final private ObservableList<Bloc> contenuOperation = FXCollections.observableArrayList();

	public ObservableList<Bloc> getContenuLigne() {
		return contenuOperation;
	}

}
