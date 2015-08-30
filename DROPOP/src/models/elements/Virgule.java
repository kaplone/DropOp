package models.elements;

import models.Positionnable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Virgule extends Positionnable {
	
	public Virgule(int xPosition, int yposition, Positionnable elementParent) {
		super(xPosition, yposition, elementParent);
		// TODO Auto-generated constructor stub
	}
	final private StringProperty label = new SimpleStringProperty(",");
	
	public String getLabel(){
		return this.label.get();
	}
	public StringProperty labelProperty(){
		return this.label;
	}

}
