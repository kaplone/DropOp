package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Positionnable {
	
    private IntegerProperty xPosition;
	
	private IntegerProperty yPosition;
	
	private ObjectProperty<Positionnable> parent;
	
	public Positionnable(int xPosition, int yposition, Positionnable elementParent) {
		this.xPosition.set(xPosition);
		this.yPosition.set(yposition);
		this.parent.set(elementParent);
	}

	public int getXPosition() {
		return this.xPosition.get();
	}

	public void setXPosition(int xPosition) {
		this.xPosition.set(xPosition);
	}
	
	public IntegerProperty XPositionProperty(){
		return this.xPosition;
	}	

	public int getYposition() {
		return this.yPosition.get();
	}

	public void setYposition(int yposition) {
		this.yPosition.set(yposition);
	}
	
	public IntegerProperty YPositionProperty(){
		return this.yPosition;
	}
	
	public Positionnable getParent(){
		return this.parent.get();
	}
	
	public void setParent(Positionnable parent){
		this.parent.set(parent);
	}

}
