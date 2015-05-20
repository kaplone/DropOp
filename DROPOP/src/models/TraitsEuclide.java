package models;

import utils.ElementType;

public class TraitsEuclide extends Element {
	
	/**
	 * 
	 */
	
	private int longueurVerticale;
	private int longueurHorizontale;

	public TraitsEuclide(ElementType oet, Commun parent, int xPosition, int yPosition) {
		super(oet, parent, xPosition, yPosition);
		
		this.longueurVerticale = 4;
		this.longueurHorizontale = 2;

	}
	
	public int getValeurVerticale() {
		return longueurVerticale;
	}

	public void setValeurVerticale(int valeurVerticale) {
		this.longueurVerticale = valeurVerticale;
	}

	public int getValeurHorizontale() {
		return longueurHorizontale;
	}

	public void setValeurHorizontale(int valeurHorizontale) {
		this.longueurHorizontale = valeurHorizontale;
	}

}
