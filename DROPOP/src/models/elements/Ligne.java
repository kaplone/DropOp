package models.elements;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import models.Commun;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Ligne extends Commun{
	
	private IntegerProperty positionHorizontaleDansBloc = new SimpleIntegerProperty();
	private IntegerProperty positionVericaleDansBloc = new SimpleIntegerProperty();
	
	
	public Ligne(Node node, Commun parent, int positionVerticale) {
		super(node, parent);
		positionVericaleDansBloc.set(positionVerticale);
		positionHorizontaleDansBloc.set(0);
	}
	
	public Ligne(Node node, Commun parent) {
		this(node, parent, 0);
	}
	
	public Ligne() {
		super();
	}

	public int getPositionHorizontaleDansBloc() {
		return positionHorizontaleDansBloc.get();
	}

	public void setPositionHorizontaleDansBloc(int positionXDansBloc) {
		this.positionHorizontaleDansBloc.set(positionXDansBloc);
	}

	public int getPositionVericaleDansBloc() {
		return positionVericaleDansBloc.get();
	}

	public void setPositionVericaleDansBloc(int positionYDansBloc) {
		this.positionVericaleDansBloc.set(positionYDansBloc);
	}
	
	final Comparator<? super Commun> comp = (p1, p2) -> Integer.compare(((UneCase) p1).getPositionSurLigne(), ((UneCase) p2).getPositionSurLigne());

	
	public int getMaxXVersDroite(){
		
		Optional<Commun> maxi = this.getContenu().stream()
	               .max(comp);
        		   
		return ((UneCase) maxi.get()).getPositionSurLigne();
		}
	
	public int getMaxXVersGauche(){
		
		Optional<Commun> mini = this.getContenu().stream()
	               .min(comp);

		return ((UneCase) mini.get()).getPositionSurLigne();
		}
}
