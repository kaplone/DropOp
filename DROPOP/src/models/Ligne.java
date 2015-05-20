package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ligne {
	
	private ObservableList<UneCase> contenu = FXCollections.observableArrayList();
	
	public ObservableList<UneCase> getContenu() {
		return contenu;
	}
	
	public void setContenu(ObservableList<UneCase> contenu) {
		this.contenu = contenu;
	}

}
