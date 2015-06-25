package models;

import utils.ElementType;

public class UneCase extends Commun {
	
	private String valeur;
	
	private boolean a_deplacer = true;

	public UneCase(ElementType oet, Commun elementParent, int xPosition,
			int yposition, String valeur) {
		super(xPosition, yposition);
		this.valeur = valeur;

	}

}
