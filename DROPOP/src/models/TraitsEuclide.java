package models;

import utils.OperationElementType;

public class TraitsEuclide extends Element {
	
	/**
	 * 
	 */
	
	private int valeurVerticale;
	private int valeurHorizontale;

	public TraitsEuclide(OperationElementType oet, Operation operationParente, int xPosition, int yposition) {
		super(oet, operationParente, xPosition, yposition);
		
		this.valeurVerticale = 4;
		this.valeurHorizontale = 2;

	}
	
	public int getValeurVerticale() {
		return valeurVerticale;
	}

	public void setValeurVerticale(int valeurVerticale) {
		this.valeurVerticale = valeurVerticale;
	}

	public int getValeurHorizontale() {
		return valeurHorizontale;
	}

	public void setValeurHorizontale(int valeurHorizontale) {
		this.valeurHorizontale = valeurHorizontale;
	}

}
