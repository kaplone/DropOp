package dropop;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

public class Chiffre extends Positionnable {
	
	final private StringProperty label = new SimpleStringProperty();
	final private ObjectProperty button = new SimpleObjectProperty();
	
	public Chiffre(){}
	
	public Chiffre(Rectangle hl, Button copie){
		
		this.button.set(copie);
		this.label.set(copie.getText());
		this.topX.set(Utils.arrondir(hl.getLayoutX()));
		this.topY.set(Utils.arrondir(hl.getLayoutX()));
		
	}
	
	public String getLabel(){
		return this.label.get();
	}
	public StringProperty labelProperty(){
		return this.label;
	}
	
	public Button getButton(){
		return (Button) this.button.get();
	}
	public ObjectProperty buttonProperty(){
		return this.button;
	}
	
	

}
