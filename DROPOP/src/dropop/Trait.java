package dropop;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Trait extends Positionnable {
	
    final private IntegerProperty trait = new SimpleIntegerProperty(4);
	
	public int getLabel(){
		return this.trait.get();
	}
	public IntegerProperty labelProperty(){
		return this.trait;
	}
	
	


}
