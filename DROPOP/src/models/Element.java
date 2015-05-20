package models;

import utils.ElementType;

public class Element extends Commun {
	
	/**
	 * 
	 * 
	 */
	
    
	private ElementType oet;

	public Element(ElementType oet, Commun elementParent,
			int xPosition, int yposition) {
		super(xPosition, yposition, elementParent);
		this.oet = oet;
		
	}

	public ElementType getOet() {
		return oet;
	}

	public void setOet(ElementType oet) {
		this.oet = oet;
	}

	

	
	
	
}
