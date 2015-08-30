package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;

public class Commun extends Control{

	private ObservableList<Commun> contenu;
	
	private Commun parent;
	
	private Node node;
	
	public Commun(Node node, Commun parent) {
		
		this.node = node;
		this.parent = parent;
		this.contenu  = FXCollections.observableArrayList(); 
	}
	
	public Commun (){
		this(null, null);
	}
	
	public Commun getObjectParent(){
		return this.parent;
	}
	
	public void setParent(Commun parent){
		this.parent = parent;
	}
	
	public ObservableList<Commun> getContenu() {
		return contenu;
	}
	
	public void setContenu(ObservableList<Commun> contenu) {
		this.contenu = contenu;
	}
	
	public void addContenu(Commun commun){
		this.contenu.add(commun);
	}
	
	public void delContenu(Commun commun){
		this.contenu.remove(commun);
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
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
		//System.out.println(contenu + " -> contenu ");
		
		copie.setParent(this.parent);
		//System.out.println(parent + " -> parent ");

		copie.setNode(this.node);
		//System.out.println(node + " -> node ");
				
		return copie;
	}
}
