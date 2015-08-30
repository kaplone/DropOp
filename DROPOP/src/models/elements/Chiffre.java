package models.elements;

import utils.Utils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

public class Chiffre extends UneCase {
	
	final private StringProperty label = new SimpleStringProperty();
	final private ObjectProperty<Button> button = new SimpleObjectProperty<Button>();

	public Chiffre(Rectangle hl, Button copie){
		super();
		this.button.set(copie);
		this.label.set(copie.getText());
		this.setNode(this.getButton()); 
	}
	
	public String getLabel(){
		return this.label.get();
	}
	public StringProperty labelProperty(){
		return this.label;
	}
	
	public Button getButton(){
		return this.button.get();
	}
	public ObjectProperty<Button> buttonProperty(){
		return this.button;
	}
	
	@Override
	public Button getNode(){
		return getButton();
	}
	

}
