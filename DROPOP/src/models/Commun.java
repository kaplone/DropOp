package models;

import utils.ElementType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Commun {
	
    private IntegerProperty xPosition;
	
	private IntegerProperty yPosition;
	
	private ElementType oet;
	
	private ObservableList<Commun> contenu;
	
	private ObjectProperty<Commun> parent;
	
	private Node node;
	
	public Commun(int x, int y) {
		this.xPosition = new SimpleIntegerProperty(x);
		this.yPosition = new SimpleIntegerProperty(y);
		this.node = null;
		this.oet = null;
		this.parent = new SimpleObjectProperty<>();
		this.contenu  = FXCollections.observableArrayList(); 
	}
	
	public Commun() {
		this(0, 0);
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

	public int getYPosition() {
		return this.yPosition.get();
	}

	public void setYPosition(int yposition) {
		this.yPosition.set(yposition);
	}
	
	public IntegerProperty YPositionProperty(){
		return this.yPosition;
	}
	
	public Commun getParent(){
		return this.parent.get();
	}
	
	public void setParent(Commun parent){
		this.parent.set(parent);
	}
	
	public ObservableList<Commun> getContenu() {
		return contenu;
	}
	
	public void setContenu(ObservableList<Commun> contenu) {
		this.contenu = contenu;
	}

	public ElementType getOet() {
		return oet;
	}
	
	public void setParent(ObjectProperty<Commun> parent) {
		this.parent = parent;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
	}

	public void setOet(ElementType oet) {
		this.oet = oet;
	}
	
	public void draw(){
		for (Commun nouveau : this.contenu){
			nouveau.draw();
		}
	}

	public ObservableList<Commun> deplaceCommun(Pane rootPane, Commun ligne_source, Commun ligne_destination, int deltaX, int deltaY) {
		return contenu;
		
	}
	
	public Commun copie(){
		
		Commun copie = new Commun();
		
		copie.setContenu(this.contenu);
		System.out.println(contenu + " -> contenu ");
		
		copie.setParent(this.parent);
		System.out.println(parent + " -> parent ");
		
		copie.setOet(this.oet);
		System.out.println(oet + " -> oet");
		
		copie.setNode(this.node);
		System.out.println(node + " -> node ");
		
		copie.setXPosition(this.getXPosition());
		System.out.println(xPosition + " -> xpos ");
		
		copie.setYPosition(this.getYPosition());
		System.out.println(yPosition + " -> ypos ");
				
		return copie;
	}

}
