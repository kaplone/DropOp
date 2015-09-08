package models.elements.traits;

import models.Commun;
import models.Positionnable;
import utils.ElementType;

public class TraitsEuclide extends Positionnable {
	
	/**
	 * 
	 */
	
	private int longueurVerticale;
	private int longueurHorizontale;

	public TraitsEuclide(int xPosition, int yposition, Positionnable elementParent) {
		super(xPosition, yposition, elementParent);
		
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
