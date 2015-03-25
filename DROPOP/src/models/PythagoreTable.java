package models;

import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PythagoreTable {
	
	/**
	 * Modèle pour une table de mutiplication.
	 * @param base la base de la table à construire 
	 */
	
	private static final int longueur = 10; 
	
	private int base;
	
	private ObservableList<Integer> contenuTable;
	
	// empty constructor
	public PythagoreTable(){
		this(0);
	}
	
	// canonic constructor 
	public PythagoreTable(int b){
		
		this.base = base;
		this.contenuTable = FXCollections.observableArrayList();
		
		IntStream.range(1, longueur + 1).forEach(
				nbr -> contenuTable.add(nbr * base));
	}

	public int getBase() {
		return base;
	}

	public ObservableList<Integer> getContenuTable() {
		return contenuTable;
	}
}
