package models;

import utils.ElementType;

public class Trait extends Element {
	
	/**
	 * 
	 */
	
	private int longueur;

	public Trait(ElementType oet, Commun parent, int xPosition, int yPosition) {
		super(oet, parent, xPosition, yPosition);
	
		this.longueur = 3;
	}

}
