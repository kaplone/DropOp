package dropop;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
	private RadioButton radio_element;
	@FXML
	private RadioButton radio_ligne;
	@FXML
	private RadioButton radio_bloc;
	@FXML
	private RadioButton radio_operation;
	
	private static int tailleQuadrillage = 25;
	
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

	ArrayList<Pane> liste_panes;
	
	Line ligne_v;
	Line ligne_h;
	
	ArrayList<Line> lignes_v;
	ArrayList<Line> lignes_h;
	private int colonne_source;
	
	Label affPositionVerticale = new Label();
	Label affPositionHorizontale = new Label();

	
	// utile pour retrouver les cases à partir de la référence d'un bouton
	Map<Button, UneCase> mapBoutons = new HashMap();

	
	@FXML
	public void on_radio_select_action(Event e){
		
		System.out.println(e.getSource().toString().split("'")[1]);
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
        
        //zero_.relocate(50.0, 100.0);
	}
	@FXML
	public void detect2(Event e0){
		
		/**
		 * Deplace un élément déjà sur la grille.
		 * pour l'instant, il s'agit uniquement d'un bouton.
		 */
			
        Dragboard db = un.startDragAndDrop(TransferMode.MOVE);
        
        source = e0.getSource();
        ((Button) source).setVisible(false);
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(un.getText());
        db.setContent(content);
        
        zero_.setText(((Button)source).getText());
	}
	
	public void ligne_vers_ligne_existante(){
		
	}
	
	public void ligne_vers_nouvelle_ligne(){
		
	}
	
	@FXML
	public void done(){
		
		System.out.println("\n\nnouveau done()\n");
		
		////////////////////////////
		//
		//   traitement par ligne
		//
		////////////////////////////
		
		if(source.toString().startsWith("Button") && selectionMode.get().equals("Ligne")){
			
			ligne_source = rows.get(Utils.arrondirVersPosition(((Button) source).getLayoutY()));
			
			//TODO : meme modifs que pour une case 
			// zero_.setVisibility()
			//arrondirVersPosition()
			//(Button) source
			// ... mais adapté.
			
			// visibilité lors du déplacement
			copie = Visibilite.visibiliteDeplacement(zero_, this);
			
			copie.setText(zero_.getText());
			copie.setMinWidth(27.0);
			
			Chiffre ch = new Chiffre(case_hl, copie);
			
//			MiseAJour.miseAJourPosition(copie, case_hl);

			int row_key = Utils.arrondirVersPosition(copie.getLayoutY());
			
			if (rows.containsKey(row_key)){
						
				ligne_destination = rows.get(row_key);	

				System.out.println("ligne source : " + ligne_source);
				System.out.println("ligne destination : " + ligne_destination);
				
//				System.out.println(ligne_destination.getPositionVericaleDansBloc());
//				System.out.println(ligne_source.getPositionVericaleDansBloc());
				

				if (ligne_destination.getPositionVericaleDansBloc()	 == ligne_source.getPositionVericaleDansBloc()){

				// deplace sur la même ligne
					
					System.out.println("\ndeplace sur la même ligne\n");

	
				}
				
				else {
					
					System.out.println("\ndeplace sur une ligne déjà créée\n");
					
				}
			}
			// deplace sur une nouvelle ligne
			else {
				System.out.println("\ncréation ligne\n");
				ligne_destination = new Ligne();
				ligne_destination.setPositionVericaleDansBloc(Utils.arrondirVersPosition(copie.getLayoutY()));
				
				System.out.println("source : " + source);
				//System.out.println("currentline size() : " + currentLine.size());
				System.out.println("ligne_destination : " + ligne_destination);
				System.out.println("ligne_source : " + ligne_source);
				System.out.println("colonne_source : " + colonne_source);

			}
			
			ligne_grabbed = false;
			
			System.out.println("source : " + ligne_source.getPositionVericaleDansBloc());
			System.out.println("destination : " + ligne_destination.getPositionVericaleDansBloc());
			
			
		}
		
		////////////////////////////
		//
		//   traitement par case
		//
		////////////////////////////
				
		else if(selectionMode.get().equals("Case") && source.toString().startsWith("Button")){
				
			ligne_source = rows.get(Utils.arrondirVersPosition(((Button) source).getLayoutY()));
			
			System.out.println("liste des rows : " + rows.entrySet());
			System.out.println(case_hl);
			System.out.println(case_hl.getLayoutY());
			System.out.println("Utils.arrondirVersPosition(case_hl.getLayoutY()) : " + Utils.arrondirVersPosition(case_hl.getLayoutY()));
			System.out.println("Utils.arrondirVersPosition(case_hl.getLayoutX()) : " + Utils.arrondirVersPosition(case_hl.getLayoutX()));
			
			System.out.println("mapDesBoutons : " + mapBoutons.entrySet());
			
			if (Utils.arrondirVersPosition(case_hl.getLayoutY()) < 0
			 || Utils.arrondirVersPosition(case_hl.getLayoutY()) > 26
			 || Utils.arrondirVersPosition(case_hl.getLayoutX()) < 0
			 || Utils.arrondirVersPosition(case_hl.getLayoutX()) > 37){
				
				System.out.println("___ cas delete ___");
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
				System.out.println("ligne déjà présente");
				ligne_destination = rows.get(Utils.arrondirVersPosition(case_hl.getLayoutY()));
				deleted = false;
			}
			else{
				System.out.println("ligne à créer");
				ligne_destination = new Ligne(null, bloc, Utils.arrondirVersPosition(case_hl.getLayoutY()));
				rows.put(Utils.arrondirVersPosition(case_hl.getLayoutY()), ligne_destination);
				deleted = false;
			}

			System.out.println("ligne_destination : " + ligne_destination);
			System.out.println("ligne_source : " + ligne_source);
			
			// deplacement d'une case existante
			if (! deleted && ligne_source != null){

				System.out.println("depuis la grille");

				// déplacement sur la meme ligne
				if (ligne_source.equals(ligne_destination)){
					System.out.println("sur une meme ligne");
				
					MiseAJour.miseAJourPosition(((Button) source), case_hl);
					((Button) source).setVisible(true);
					zero_.setVisible(false);
					
					System.out.println(mapBoutons);

					mapBoutons.get(source).setPositionSurLigne(Utils.arrondirVersPosition(case_hl.getLayoutX()));

				}
				// déplacement sur une autre ligne
				else{
					System.out.println("sur une autre ligne");
					MiseAJour.miseAJourPosition(((Button) source), case_hl);
					((Button) source).setVisible(true);
					zero_.setVisible(false);
					
					if (rows.containsKey(Utils.arrondirVersPosition(case_hl.getLayoutY()))){
						
						// deplace de la grille vers une ligne existante
						System.out.println("vers ligne existante");
						
						System.out.println("copie : " + copie.getProperties());
						System.out.println("source : " + ((Button) source).getProperties());
						System.out.println("bouton source : " + ((Button) source).getLayoutX());
						
						ligne_destination = rows.get(Utils.arrondirVersPosition(case_hl.getLayoutY()));
						
						ligne_destination.addContenu(mapBoutons.get(source));
						ligne_source.delContenu(mapBoutons.get(source));
						
						MiseAJour.miseAJourPosition(((Button) source), case_hl);
						
		
					}
					else {
						
						// deplace de la grille vers une nouvelle ligne
						System.out.println("vers une nouvelle ligne");

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
				
				System.out.println("depuis le pavé");
				
				// visibilité lors du déplacement
				copie = Visibilite.visibiliteDeplacement(zero_, this);
				
				copie.setText(zero_.getText());
				copie.setMinWidth(27.0);

				if (rows.containsKey(Utils.arrondirVersPosition(case_hl.getLayoutY()))){
					
					// deplace du pavé numerique vers une ligne existante
					System.out.println("vers ligne existante");
					
					ligne_destination = rows.get(Utils.arrondirVersPosition(case_hl.getLayoutY()));
					UneCase nouvelleCase = new UneCase(copie, ligne_destination, Utils.arrondirVersPosition(case_hl.getLayoutX()));
					
					mapBoutons.put(copie, nouvelleCase);
					
					ligne_destination.addContenu(nouvelleCase);
					
					MiseAJour.miseAJourPosition(copie, case_hl);
					
	
				}
				else {
					
					// deplace du pavé numerique vers une nouvelle ligne
					System.out.println("vers une nouvelle ligne");

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
			cursorPane.toFront();
	        liste_panes.add(cursorPane);
			
	        WritableImage image = cursorPane.snapshot(new SnapshotParameters(), null);

	        // TODO: probably use a file chooser here
	        File file = new File("/home/kaplone/Desktop/chart.png");

	        try {
	            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
	        } catch (IOException e) {
	            // TODO: handle exception here
	        }
		}
     }
	
	
	
	@FXML
	public void over1(DragEvent e1){
		
		if(selectionMode.get().equals("Ligne")){			
			
			if (! ligne_grabbed){
				
				colonne_source = Utils.arrondir(e1.getSceneX());

				ligne_source = rows.get(Utils.arrondirVersPosition(e1.getSceneY()));
				
				System.out.println("ligne source d'après rows.get() : " +  ligne_source);
				
				System.out.println("max vers droite : " + ligne_source.getMaxXVersDroite());
				System.out.println("max vers gauche : " + ligne_source.getMaxXVersGauche());
				
				if (rows.containsKey(copie)){
				
				    ligne_source.setPositionHorizontaleDansBloc(Utils.arrondir(e1.getSceneY()));
			        currentLine = ligne_source.getContenu();
				}
			    ligne_grabbed = true;
			}
		}
		
		
		zero_.relocate(e1.getSceneX(), e1.getSceneY());
		zero_.toFront();
		
		
		
		
		if (source.toString().startsWith("Button")){
			
		    case_hl.relocate(Utils.arrondir(e1.getSceneX()) + 25, Utils.arrondir(e1.getSceneY()) + 25);
		    case_hl.setVisible(true);
		    
		    affPositionVerticale.textProperty().set((Utils.arrondirVersPosition(e1.getSceneY())) + "");
		    affPositionVerticale.relocate(Utils.arrondir(e1.getSceneX())  + 30, Utils.arrondir(e1.getSceneY()) + 5);
		    affPositionVerticale.setVisible(true);
		    
		    
		    affPositionHorizontale.textProperty().set((Utils.arrondirVersPosition(e1.getSceneX())) + "");
		    affPositionHorizontale.relocate(Utils.arrondir(e1.getSceneX()) + 5, Utils.arrondir(e1.getSceneY()) + 30);
		    affPositionHorizontale.setVisible(true);
		    
		    zero_.setVisible(true);
		}
		else if (source.toString().startsWith("Pane")){
			cursorPane.relocate(Utils.arrondir(e1.getSceneX()) + 25, Utils.arrondir(e1.getSceneY()) + 25);
			
		    zero_.setVisible(false);
		}
		
		
		
		
		//zero_.relocate(50.0, 100.0);
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
        
        Circle circle = new Circle();
        circle.setCenterX(100.0f);
        circle.setCenterY(100.0f);
        circle.setRadius(50.0f);
        
        Line lh = new Line();
        Line lv = new Line();
        lh.setStartX(div_h.getStartX());
        lh.setStartY(div_h.getStartY());
        lh.setEndX(div_h.getEndX());
        lh.setEndY(div_h.getEndY());
        lv.setStartX(div_v.getStartX());
        lv.setStartY(div_v.getStartY() - 50);
        lv.setEndX(div_v.getEndX());
        lv.setEndY(div_v.getEndY() - 50);
        
        
        
        cursorPane.getChildren().add(lh);
        cursorPane.getChildren().add(lv);
        
        rootPane.getChildren().add(cursorPane);

	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		liste_panes = new ArrayList<>();
		
		bloc = new Bloc();
	
		//System.out.println("init");
		zero_.setVisible(false);
		zero_.relocate(-30.0, -30.0);
		case_hl.relocate(-30.0, -30.0);
		
		//liste_boutons = new ArrayList<>();
		lignes_h = new ArrayList<>();
		lignes_v = new ArrayList<>();
		
		clavier.getChildren().remove(zero);
		zero.setMinWidth(61.0);
		clavier.add(zero, 0, 3 , 2, 1);
		
		affPositionHorizontale.setTextFill(Color.web("#0076a390"));
		affPositionVerticale.setTextFill(Color.web("#0076a390"));
		
		for (int i = 1; i < 40; i++){
			ligne_v = new Line();
			ligne_v.setOpacity(0.3);
			ligne_v.getStrokeDashArray().addAll(5.0, 5.0);
			ligne_v.setStartX(25);
			ligne_v.setEndX(975);
			ligne_v.setStartY(i * 25);
			ligne_v.setEndY(i * 25);
			lignes_v.add(ligne_v);
			
			ligne_h = new Line();
			ligne_h.setOpacity(0.3);
			ligne_h.getStrokeDashArray().addAll(5.0, 5.0);
			ligne_h.setStartY(25);
			ligne_h.setStartX(i * 25);
			ligne_h.setEndX(i * 25);
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
