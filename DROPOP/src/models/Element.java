package models;

import utils.OperationElementType;

public class Element {
	
	/**
	 * 
	 */
    
	private OperationElementType oet;
	
	private Operation operationParente;
	
	private int XPosition;
	
	private int Yposition;

	public Element(OperationElementType oet, Operation operationParente,
			int xPosition, int yposition) {
		super();
		this.oet = oet;
		this.operationParente = operationParente;
		XPosition = xPosition;
		Yposition = yposition;
	}

	public OperationElementType getOet() {
		return oet;
	}

	public void setOet(OperationElementType oet) {
		this.oet = oet;
	}

	public Operation getOperationParente() {
		return operationParente;
	}

	public void setOperationParente(Operation operationParente) {
		this.operationParente = operationParente;
	}

	public int getXPosition() {
		return XPosition;
	}

	public void setXPosition(int xPosition) {
		XPosition = xPosition;
	}

	public int getYposition() {
		return Yposition;
	}

	public void setYposition(int yposition) {
		Yposition = yposition;
	}
	
	
}
