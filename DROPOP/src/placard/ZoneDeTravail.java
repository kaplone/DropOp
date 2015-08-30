package placard;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Représentation de la zone de travail.
 * C'est la zone de la grille qui accueille l'opération
 * et qui sera exportée / imprimée
 * @author kaplone
 *
 */
public class ZoneDeTravail {
	
	private IntegerProperty hauteur;
	private IntegerProperty largeur;
	
	public ZoneDeTravail(){

		this.hauteur = new SimpleIntegerProperty(3);
		this.largeur = new SimpleIntegerProperty(4);
	}

	public ZoneDeTravail(IntegerProperty hauteur, IntegerProperty largeur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
	}
	
	
	

}
