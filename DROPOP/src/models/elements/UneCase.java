package models.elements;

import models.Commun;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import utils.ElementType;

public class UneCase extends Commun {
	
	private IntegerProperty positionSurLigne = new SimpleIntegerProperty();

	public UneCase(Node node, Commun parent, int position) {
		super(node, parent);
		setPositionSurLigne(position);
	}
	
	public UneCase(Node node, Commun parent) {
		this(node, parent, 0);
	}

	public UneCase() {
	}

	public int getPositionSurLigne() {
		return positionSurLigne.get();
	}

	public void setPositionSurLigne(int position_sur_ligne) {
		this.positionSurLigne.set(position_sur_ligne);
	}
}
