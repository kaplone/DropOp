package models;

import utils.OperationElementType;

public class Trait extends Element {
	
	/**
	 * 
	 */
	
	private int valeur;

	public Trait(OperationElementType oet, Operation operationParente, int xPosition, int yposition) {
		super(oet, operationParente, xPosition, yposition);
	
		this.valeur = 3;
	}

	

}
