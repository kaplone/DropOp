package dropop;

import java.beans.Visibility;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.sun.javafx.collections.ImmutableObservableList;
import com.sun.javafx.collections.UnmodifiableListSet;

import utils.ElementType;
import utils.MiseAJour;
import utils.Utils;
import utils.Visibilite;
import models.Commun;
import models.Positionnable;
import models.elements.Bloc;
import models.elements.Chiffre;
import models.elements.Ligne;
import models.elements.UneCase;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;

public class Gui_controller implements Initializable {
	
	@FXML
	private Button un;
	@FXML
	private Button deux;
	@FXML
	private Button trois;
	@FXML
	private Button zero_;
	@FXML
	private Button zero;
	@FXML
	private Rectangle case_hl;
	@FXML
	private Pane grille;
	@FXML
	private Pane rootPane;
	@FXML
	private GridPane clavier;
	@FXML
	private Line div_h;
	@FXML
	private Line div_v;
	@FXML
	private Line line_h;
	
    @FXML
    private Button exportButton;
	
	@FXML
	private RadioButton radio_element;
	@FXML
	private RadioButton radio_ligne;
	@FXML
	private RadioButton radio_bloc;
	@FXML
	private RadioButton radio_operation;
	
	@FXML
	private Pane potence;
	@FXML
	private Pane Horizontale;
	
	@FXML
	private VBox toolbox;
	
	private static int tailleQuadrillage = 50;
	
	private Object source;
	
	private Button copie;
	
	private Pane cursorPane;
	
	private StringProperty selectionMode = new SimpleStringProperty("Case");
	
	private Bloc bloc;
	
	private Ligne ligne;
	private Ligne ligne_source;
	private Ligne ligne_destination;
	
	private ObservableMap<Integer, Ligne> rows = FXCollections.observableMap(new HashMap<Integer, Ligne>());
	
	private boolean ligne_grabbed = false;
	
	private boolean deleted = false;
	
	private ObservableList<Commun> currentLine;

	private ArrayList<Pane> liste_panes;
	
	private Line ligne_v;
	private Line ligne_h;
	
	private ArrayList<Line> lignes_v;
	private ArrayList<Line> lignes_h;
	private int colonne_source;
	
	private Label affPositionVerticale = new Label();
	private Label affPositionHorizontale = new Label();
	
	private Pane paneDeplacement;
	private Pane pane_hl;
	
	private Button sourceButton;
	
	private double deltaXStart;
	private double deltaXEnd;
	private double deltaX;
	
	
	
	// utile pour retrouver les cases à partir de la référence d'un bouton
	private Map<Button, UneCase> mapBoutons = new HashMap();
	

	@FXML
	public void on_radio_select_action(Event e){

		selectionMode.set(e.getSource().toString().split("'")[1]);
		
	}

	@FXML
	public void detect(Event e0){
		
		/**
		 * Deplace un élément depuis la boite "clavier"
		 * 
		 */
		
        Dragboard db = un.startDragAndDrop(TransferMode.MOVE);
        
        source = e0.getSource();
        
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(un.getText());
        db.setContent(content);
        
        zero_.setText(((Button)e0.getSource()).getText());
	}
	
	@FXML
	public void detect2(Event e0){
		
		/**
		 * Deplace un élément déjà sur la grille.
		 * pour l'instant, il s'agit uniquement d'un bouton.
		 * 
		 * on commence avec une ligne
		 */
		
		 Dragboard db = un.startDragAndDrop(TransferMode.MOVE);
	        
        source = e0.getSource();
        
        sourceButton = ((Button) source);
        deltaXStart = sourceButton.getLayoutX();
        
        sourceButton.setVisible(false);
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(un.getText());
        db.setContent(content);
        
        zero_.setText(sourceButton.getText());
        zero_.setStyle("-fx-font-size: 18pt");
		
		if (selectionMode.get().equals("Case")){
			
	        case_hl.setVisible(true);
		}
	        
        else if (selectionMode.get().equals("Ligne")){
	        
	        if (! ligne_grabbed){
	        	
				if (mapBoutons.containsKey(source)){
					
					int num_ligne_source = Utils.arrondirVersPosition(((Button) source).getLayoutY());

					ligne_source = rows.get(num_ligne_source);
				    ligne_grabbed = true; 
				    case_hl.setVisible(false);
				}
			}
	
			paneDeplacement = new Pane();
			pane_hl = new Pane();
			
			grille.getChildren().add(pane_hl);
			grille.getChildren().add(paneDeplacement);
			
			paneDeplacement.relocate(0, sourceButton.getLayoutY());
			pane_hl.relocate(0, sourceButton.getLayoutY());

			ligne_source.getContenu().stream().forEach(a -> {Button tempButton = (Button) a.getNode();  // tempButton.equals(sourceButton)
			                                                 tempButton.setVisible(true);
			                                                 tempButton.relocate(tempButton.getLayoutX(),0);
			                                                 
			                                                 try {
			                                                     paneDeplacement.getChildren().add(tempButton);
			                                                 }
			                                                 //TODO regler ici :
			                                                 // exception quand on deplace sur une meme ligne
			                                                 // paneDeplacement contient déjà tempButton
			                                                 
			                                                 catch (IllegalArgumentException iae){
			                                                 }
			
			                                                 Rectangle hl_temp = new Rectangle(tailleQuadrillage, tailleQuadrillage);
			                                                 hl_temp.setFill(Color.web("#97bbda"));
			                                                 hl_temp.relocate(tempButton.getLayoutX(),0);
			                                                 
			                                                 pane_hl.getChildren().add(hl_temp);
	
			                                                }
			                                          );
        }
	}
	
	@FXML
	public void done(){

		ligne_grabbed = false;
		
		////////////////////////////
		//
		//   traitement par ligne
		//
		////////////////////////////
		
		if(source.toString().startsWith("Button") && selectionMode.get().equals("Ligne")){

			deltaX = deltaXEnd - deltaXStart;

			// ligne_source = rows.get(Utils.arrondirVersPosition(sourceButton.getLayoutY())); // deplacement vers detect2

			int row_key = Utils.arrondirVersPosition(pane_hl.getLayoutY());
			
			/////////
			//
			// première conditionnelle :
			// 
			// ligne existante / nouvelle ligne
			//
			////////
			
			// deplace sur une ligne existante (merge ?)
			if (rows.containsKey(row_key)){
						
				ligne_destination = rows.get(row_key);	
				
				//TODO
				// deplace les elements deja presents
				
				
			}
			// deplace sur une nouvelle ligne
			else {
				ligne_destination = new Ligne();
				
				Integer nouveau = new Integer(Utils.arrondirVersPosition(pane_hl.getLayoutY()));
				
				rows.put( nouveau , ligne_destination);
			}
			
			paneDeplacement.setLayoutY(pane_hl.getLayoutY());
			paneDeplacement.setLayoutX(pane_hl.getLayoutX());
			
			/////////
			//
			// deuxième conditionnelle :
			// 
			// ligne différente / même ligne
			// sinon problème avec "ligne_source.getContenu().remove(a)"
			//
			////////
			
			if (! ligne_destination.equals(ligne_source)){
							
				paneDeplacement.getChildren()
				               .stream()
				               .forEach(a -> {ligne_destination.getContenu()
				            	                               .add(new UneCase(a, ligne_destination, Utils.arrondirVersPosition(a.getLayoutX())));
				                              ligne_source.getContenu()
				                                          .remove(a);
				                             });	
			}
			
			ligne_destination.getContenu().stream()
							              .forEach(a -> {utils.ChangeParent.changeParent(a.getNode(),grille);
															                             a.getNode().setLayoutY(paneDeplacement.getLayoutY());
															                             // il faut un deltaX
															                             a.getNode().setLayoutX(Utils.arrondirSimple(a.getNode().getLayoutX() + deltaX));
														 });
           
			pane_hl.setVisible(false);
			affPositionHorizontale.setVisible(false);
			affPositionVerticale.setVisible(false);

		}
		
		
		////////////////////////////
		//
		//   traitement par case
		//
		////////////////////////////
				
		else if(selectionMode.get().equals("Case") && source.toString().startsWith("Button")){
			
			case_hl.setVisible(false);

				
			ligne_source = rows.get(Utils.arrondirVersPosition(((Button) source).getLayoutY()));
			
			// gestion de l'effacement
			if (Utils.arrondirVersPosition(case_hl.getLayoutY()) < 0
			 || Utils.arrondirVersPosition(case_hl.getLayoutY()) > 12
			 || Utils.arrondirVersPosition(case_hl.getLayoutX()) < 0
			 || Utils.arrondirVersPosition(case_hl.getLayoutX()) > 18){

				if (source != null){
					ligne_source.delContenu(mapBoutons.get(source));
					mapBoutons.remove(source);
				}
				case_hl.setVisible(false);
				affPositionHorizontale.setVisible(false);
				affPositionVerticale.setVisible(false);
				zero_.setVisible(false);
				deleted = true;
			}
			else if (rows.containsKey((Utils.arrondirVersPosition(case_hl.getLayoutY())))){
				ligne_destination = rows.get(Utils.arrondirVersPosition(case_hl.getLayoutY()));
				deleted = false;
			}
			else{
				ligne_destination = new Ligne(null, bloc, Utils.arrondirVersPosition(case_hl.getLayoutY()));
				rows.put(Utils.arrondirVersPosition(case_hl.getLayoutY()), ligne_destination);
				deleted = false;
			}
			
			// deplacement d'une case existante
			if (! deleted && ligne_source != null){
				
				sourceButton.toFront();

				// déplacement sur la meme ligne
				if (ligne_source.equals(ligne_destination)){
				
					MiseAJour.miseAJourPosition(((Button) source), case_hl);
					((Button) source).setVisible(true);
					zero_.setVisible(false);

					mapBoutons.get(source).setPositionSurLigne(Utils.arrondirVersPosition(case_hl.getLayoutX()));

				}
				// déplacement sur une autre ligne
				else{
					MiseAJour.miseAJourPosition(((Button) source), case_hl);
					((Button) source).setVisible(true);
					zero_.setVisible(false);
					
					if (rows.containsKey(Utils.arrondirVersPosition(case_hl.getLayoutY()))){
						
						// deplace de la grille vers une ligne existante

						ligne_destination = rows.get(Utils.arrondirVersPosition(case_hl.getLayoutY()));
						
						ligne_destination.addContenu(mapBoutons.get(source));
						ligne_source.delContenu(mapBoutons.get(source));
						
						MiseAJour.miseAJourPosition(((Button) source), case_hl);
						
		
					}
					else {
						
						// deplace de la grille vers une nouvelle ligne
						
						ligne_destination = new Ligne(null, bloc, Utils.arrondirVersPosition(case_hl.getLayoutY()));
						
						ligne_destination.addContenu(mapBoutons.get(source));
						ligne_source.delContenu(mapBoutons.get(source));
						ligne_destination.setPositionVericaleDansBloc(Utils.arrondirVersPosition(case_hl.getLayoutY()));
						
						rows.put(Utils.arrondirVersPosition(case_hl.getLayoutY()), ligne_destination);
						
						MiseAJour.miseAJourPosition(((Button) source), case_hl);
						
					}
				}
			}
			
			// deplacement d'une case depuis la pavé
			
			
			else if (! deleted){
				
				// visibilité lors du déplacement
				copie = Visibilite.visibiliteDeplacement(zero_, this);
				
				copie.setText(zero_.getText());
				
				if (copie.getText().equals(".")){
					copie.setStyle("-fx-font-size: 12pt");
					copie.setMinWidth(tailleQuadrillage / 2);
					copie.setMinHeight(tailleQuadrillage / 2);
				}
				else {
					copie.setStyle("-fx-font-size: 18pt");
					copie.setMinWidth(tailleQuadrillage);
					copie.setMinHeight(tailleQuadrillage);
				}

				if (rows.containsKey(Utils.arrondirVersPosition(case_hl.getLayoutY()))){
					
					// deplace du pavé numerique vers une ligne existante
					
					ligne_destination = rows.get(Utils.arrondirVersPosition(case_hl.getLayoutY()));
					UneCase nouvelleCase = new UneCase(copie, ligne_destination, Utils.arrondirVersPosition(case_hl.getLayoutX()));
					
					mapBoutons.put(copie, nouvelleCase);
					
					ligne_destination.addContenu(nouvelleCase);
					
					MiseAJour.miseAJourPosition(copie, case_hl);
					
	
				}
				else {
					
					// deplace du pavé numerique vers une nouvelle ligne

					ligne_destination = new Ligne(null, bloc, Utils.arrondirVersPosition(case_hl.getLayoutY()));
					UneCase nouvelleCase = new UneCase(copie, ligne_destination, Utils.arrondirVersPosition(case_hl.getLayoutX()));
				
					mapBoutons.put(copie, nouvelleCase);
					
					ligne_destination.addContenu(nouvelleCase);
					ligne_destination.setPositionVericaleDansBloc(Utils.arrondirVersPosition(case_hl.getLayoutY()));
					
					rows.put(Utils.arrondirVersPosition(case_hl.getLayoutY()), ligne_destination);
					
					MiseAJour.miseAJourPosition(copie, case_hl);
					
				}
				
				// pour l'affichage 
				
				rootPane.getChildren().add(copie);
				copie.setVisible(true);
			}
	
		}

		else if (source.toString().startsWith("Pane")){
			
	        liste_panes.add(cursorPane);
			
	        
		}
		if (cursorPane != null) {
		    cursorPane.toFront();
		}
     }
	
	@FXML
	public void export(){
		
		
		
		rootPane.getStylesheets().clear();  
		rootPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		double minX;
		double minY;
		double maxX;
		double maxY;
		
		Comparator<Button> compButtonsX = new Comparator<Button>() {

			@Override
			public int compare(Button o1, Button o2) {
				if (o1.getLayoutX() < o2.getLayoutX()){
					return -1;
				}
				else {
					return 1;
				}
				
			}
		};
		
		Comparator<Button> compButtonsY = new Comparator<Button>() {

			@Override
			public int compare(Button o1, Button o2) {
				if (o1.getLayoutY() < o2.getLayoutY()){
					return -1;
				}
				else {
					return 1;
				}
			}
		};

		
		minX = mapBoutons.keySet()
				         .stream()
				         .min(compButtonsX)
				         .get()
				         .getLayoutX();
		
		minY = mapBoutons.keySet()
				         .stream()
				         .min(compButtonsY)
				         .get()
				         .getLayoutY();
		
		maxX = mapBoutons.keySet()
				         .stream()
				         .max(compButtonsX)
				         .get()
				         .getLayoutX();

		maxY = mapBoutons.keySet()
				         .stream()
				         .max(compButtonsY)
				         .get()
				         .getLayoutY();

		grille.setVisible(false);
		
		SnapshotParameters sp = new SnapshotParameters();
		sp.setViewport(new Rectangle2D(minX,
				                       minY,
				                       maxX - minX + tailleQuadrillage,
				                       maxY - minY + tailleQuadrillage));

		WritableImage image = rootPane.snapshot(sp, null);
		
		grille.setVisible(true);
		rootPane.getStylesheets().clear();  
		rootPane.getStylesheets().add(getClass().getResource("application_neutre.css").toExternalForm());

        // TODO: probably use a file chooser here
        File file = new File("/home/kaplone/Desktop/chart.png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            // TODO: handle exception here
        }
	}
	
	@FXML
	public void over1(DragEvent e1){

		deltaXEnd = e1.getX();
		
		affPositionVerticale.setVisible(false);
		affPositionHorizontale.setVisible(false);
		
		////////////////////////////
		//
		//   traitement par ligne
		//
		////////////////////////////
		
		if(selectionMode.get().equals("Ligne")){	
			
			Visibilite.highlightPosSerie(e1, pane_hl, affPositionVerticale, affPositionHorizontale, paneDeplacement, sourceButton);

		}
	    	
		////////////////////////////
		//
		//   traitement par case
		//
		////////////////////////////
		
		else if (source.toString().startsWith("Button")){
			
			if (((Button)source).getText().equals(".")){
				rootPane.getChildren().remove(case_hl);
				rootPane.getChildren().add(case_hl);
				case_hl.setWidth(25);
				case_hl.setHeight(25);
				Visibilite.highlightPosDot(e1, case_hl, zero_);
	        }
			else {
				case_hl.toBack();
				case_hl.setWidth(50);
				case_hl.setHeight(50);
			    Visibilite.highlightPos(e1, case_hl, affPositionVerticale, affPositionHorizontale, zero_);
			}
			
		}
		else if (source.toString().startsWith("Pane")){
			
			cursorPane.relocate(Utils.arrondir(e1.getSceneX()) + tailleQuadrillage, Utils.arrondir(e1.getSceneY()) + tailleQuadrillage);
			
		    zero_.setVisible(false);
		}

	}
	
	@FXML
	public void detect_op(Event e2){
        Dragboard db = un.startDragAndDrop(TransferMode.MOVE);
        
        source = e2.getSource();
        
        
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(e2.getSource().toString());
        db.setContent(content);
        
        cursorPane = new Pane();
        cursorPane.getProperties().putAll(((Pane)e2.getSource()).getProperties());
        cursorPane.setLayoutX(100);
        cursorPane.setLayoutY(100);
        
        if (((Pane) source).getId().equals("potence")){
        	
        	Line lh = new Line();
        	lh.setStrokeWidth(4);
            Line lv = new Line();
            lv.setStrokeWidth(4);
            lh.setStartX(div_h.getStartX());
            lh.setStartY(div_h.getStartY());
            lh.setEndX(div_h.getEndX() + 10);
            lh.setEndY(div_h.getEndY());
            lv.setStartX(div_v.getStartX());
            lv.setStartY(div_v.getStartY() - 70);
            lv.setEndX(div_v.getEndX());
            lv.setEndY(div_v.getEndY());
            
            
            
            cursorPane.getChildren().add(lh);
            cursorPane.getChildren().add(lv);
        }
        else if (((Pane) source).getId().equals("horizontale")){
        	Line lh = new Line();
        	lh.setStrokeWidth(4);
        	lh.setStartX(line_h.getStartX() - 20);
            lh.setStartY(line_h.getStartY() - 20);
            lh.setEndX(line_h.getEndX() + 50);
            lh.setEndY(line_h.getEndY() - 20);
            
            cursorPane.getChildren().add(lh);
        }

       

        
        
        rootPane.getChildren().add(cursorPane);

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		liste_panes = new ArrayList<>();
		
		bloc = new Bloc();

		zero_.setVisible(false);
		zero_.relocate(-30.0, -30.0);
		case_hl.relocate(-30.0, -30.0);
		case_hl.setStyle("-fx-font-size: 18pt");
		case_hl.setWidth(tailleQuadrillage);
		case_hl.setHeight(tailleQuadrillage);
		case_hl.setVisible(false);
		
		//liste_boutons = new ArrayList<>();
		lignes_h = new ArrayList<>();
		lignes_v = new ArrayList<>();
		
		clavier.getChildren().remove(zero);
		zero.setMinWidth(61.0);
		clavier.add(zero, 0, 3 , 2, 1);
		
		affPositionHorizontale.setTextFill(Color.web("#0076a390"));
		affPositionVerticale.setTextFill(Color.web("#0076a390"));
		
		for (int i = 1; i < 21; i++){
			ligne_v = new Line();
			ligne_v.setOpacity(0.3);
			ligne_v.getStrokeDashArray().addAll(5.0, 5.0);
			ligne_v.setStartX(tailleQuadrillage);
			ligne_v.setEndX(1000);
			ligne_v.setStartY(i * tailleQuadrillage);
			ligne_v.setEndY(i * tailleQuadrillage);
			lignes_v.add(ligne_v);
			
			ligne_h = new Line();
			ligne_h.setOpacity(0.3);
			ligne_h.getStrokeDashArray().addAll(5.0, 5.0);
			ligne_h.setStartY(tailleQuadrillage);
			ligne_h.setStartX(i * tailleQuadrillage);
			ligne_h.setEndX(i * tailleQuadrillage);
			ligne_h.setEndY(700);
			lignes_h.add(ligne_h);
		}
		grille.getChildren().addAll(lignes_v);
		grille.getChildren().addAll(lignes_h);
		
		grille.getChildren().add(affPositionVerticale);
		grille.getChildren().add(affPositionHorizontale);
	}

	public static int getTailleQuadrillage() {
		return tailleQuadrillage;
	}

	public static void setTailleQuadrillage(int tailleQuadrillage) {
		Gui_controller.tailleQuadrillage = tailleQuadrillage;
	}
}
