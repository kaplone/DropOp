package models;

import utils.ElementType;

public class Trait extends Commun {
	
	/**
	 * 
	 */
	
	private int longueur;

	public Trait(ElementType oet, Commun parent, int xPosition, int yPosition) {
		super(xPosition, yPosition);
	
		this.longueur = 3;
	}

}
