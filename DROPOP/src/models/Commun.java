package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Commun {
	
    private IntegerProperty xPosition;
	
	private IntegerProperty yPosition;
	
	private ObjectProperty<Commun> parent;
	
	public Commun(int xPosition, int yposition, Commun elementParent) {
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
	
	public Commun getParent(){
		return this.parent.get();
	}
	
	public void setParent(Commun parent){
		this.parent.set(parent);
	}

}
