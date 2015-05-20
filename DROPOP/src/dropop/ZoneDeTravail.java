package dropop;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ZoneDeTravail {
	
	private IntegerProperty topX;
	private IntegerProperty topY;
	private IntegerProperty hauteur;
	private IntegerProperty largeur;
	
	public ZoneDeTravail(){
		this.topX = new SimpleIntegerProperty(0);
		this.topY = new SimpleIntegerProperty(0);
		this.hauteur = new SimpleIntegerProperty(3);
		this.largeur = new SimpleIntegerProperty(4);
	}

	public ZoneDeTravail(IntegerProperty topX, IntegerProperty topY,
			IntegerProperty hauteur, IntegerProperty largeur) {
		this.topX = topX;
		this.topY = topY;
		this.hauteur = hauteur;
		this.largeur = largeur;
	}
	
	
	

}
