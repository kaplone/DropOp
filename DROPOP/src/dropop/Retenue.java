package dropop;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Retenue extends Positionnable {
	
	final private StringProperty label = new SimpleStringProperty();
	
	public String getLabel(){
		return this.label.get();
	}
	public StringProperty labelProperty(){
		return this.label;
	}

}
