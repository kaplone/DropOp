package models.operations;

import models.Positionnable;
import models.elements.Bloc;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Addition extends Positionnable {
	
	public Addition(int xPosition, int yposition, Positionnable elementParent) {
		super(xPosition, yposition, elementParent);
		// TODO Auto-generated constructor stub
	}

	final private StringProperty signeOperation = new SimpleStringProperty("+");
	
	final private ObservableList<Bloc> contenuOperation = FXCollections.observableArrayList();

	public ObservableList<Bloc> getContenuLigne() {
		return contenuOperation;
	}

}
