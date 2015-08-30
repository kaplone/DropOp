package models.elements.traits;

import models.Positionnable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Trait_ extends Positionnable {
	
    public Trait_(int xPosition, int yposition, Positionnable elementParent) {
		super(xPosition, yposition, elementParent);
		// TODO Auto-generated constructor stub
	}
	final private IntegerProperty trait = new SimpleIntegerProperty(4);
	
	public int getLabel(){
		return this.trait.get();
	}
	public IntegerProperty labelProperty(){
		return this.trait;
	}
	
	


}