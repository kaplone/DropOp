package models;

import utils.ElementType;

public class UneCase extends Element {
	
	private String valeur;

	public UneCase(ElementType oet, Commun elementParent, int xPosition,
			int yposition, String valeur) {
		super(oet, elementParent, xPosition, yposition);
		this.valeur = valeur;

	}

}
