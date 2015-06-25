package dropop;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.sun.javafx.collections.ImmutableObservableList;
import com.sun.javafx.collections.UnmodifiableListSet;

import utils.MapDeplacement;
import utils.MiseAJour;
import utils.Utils;
import utils.Visibilite;
import models.Chiffre;
import models.Commun;
import models.Ligne;
import models.Positionnable;
import models.UneCase;
import javafx.scene.Node;
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
	
	int tailleQuadrillage = 25;
	
	private Object source;
	
	private Button copie;
	
	private Pane cursorPane;
	
	private StringProperty selectionMode = new SimpleStringProperty("Case");
	
	private Ligne ligne;
	private Ligne ligne_source;
	private Ligne ligne_destination;
	
	private ObservableMap<Integer, Ligne> rows = FXCollections.observableMap(new HashMap<Integer, Ligne>());
	
	private boolean ligne_grabbed = false;
	
	private ObservableList<Commun> currentLine;

	ArrayList<Pane> liste_panes;
	
	Line ligne_v;
	Line ligne_h;
	
	ArrayList<Line> lignes_v;
	ArrayList<Line> lignes_h;
	private int colonne_source;

	
	@FXML
	public void on_radio_select_action(Event e){
		
		System.out.println(e.getSource().toString().split("'")[1]);
		selectionMode.set(e.getSource().toString().split("'")[1]);
		
	}

	@FXML
	public void detect(Event e0){
        Dragboard db = un.startDragAndDrop(TransferMode.MOVE);
        
        source = e0.getSource();
        
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(un.getText());
        db.setContent(content);
        
        zero_.setText(((Button)e0.getSource()).getText());
        
        zero_.relocate(50.0, 100.0);
	}
	@FXML
	public void detect2(Event e0){
		
			
        Dragboard db = un.startDragAndDrop(TransferMode.MOVE);
        
        source = e0.getSource();
        ((Button) source).setVisible(false);
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(un.getText());
        db.setContent(content);
        
        zero_.setText(((Button)source).getText());
        
        zero_.relocate(50.0, 100.0);
	}
	@FXML
	public void done(){
		
		if(source.toString().startsWith("Button") && selectionMode.get().equals("Ligne")){
			
			copie = Visibilite.visibiliteDeplacement(zero_, this);
			
			copie.setText(zero_.getText());
			copie.setMinWidth(27.0);
			
			
			
			MiseAJour.miseAJourPosition(copie, case_hl, colonne_source, ligne_source);

			int row_key = Utils.arrondir(copie.getLayoutY());
			
			if (rows.containsKey(row_key)){
						
				ligne_destination = rows.get(row_key);	

				System.out.println(ligne_source);
				System.out.println(ligne_destination);

				if (ligne_destination.getYPosition() == ligne_source.getYPosition()){

				// deplace sur la même ligne
					
					System.out.println("deplace sur la même ligne");
					
					
					ligne_destination.setContenu(ligne_source.deplaceCommun(rootPane,
							                                                ligne_source,
							                                                ligne_destination,
							                                                MiseAJour.getDeltaX(colonne_source, case_hl),
							                                                MiseAJour.getDeltaY(ligne_source, case_hl) ));
//					currentLine.stream()
//					           .filter(a -> ! ((Chiffre)a).getButton().equals(source))
//					           .map(a -> MapDeplacement.deplace(rootPane, ligne_source, ligne_destination, a, deltaX, deltaY ));

	
				}
			}
			// deplace sur une nouvelle ligne
			else {
				System.out.println("création ligne");
				ligne_destination = new Ligne();
				ligne_destination.setYPosition(Utils.arrondir(copie.getLayoutY()));
				
				System.out.println("source : " + source);
				System.out.println("currentline size() : " + currentLine.size());
				
				ligne_destination.setContenu(ligne_source.deplaceCommun(rootPane,
                                                                        ligne_source,
                                                                        ligne_destination,
                                                                        MiseAJour.getDeltaX(colonne_source, case_hl),
						                                                MiseAJour.getDeltaY(ligne_source, case_hl) ));
				
				//currentLine.stream()
				           //.filter(a -> ! ((Chiffre)a).getButton().equals(source))
				           //.map(a -> MapDeplacement.deplace(rootPane, ligne_source, ligne_destination, a, deltaX, deltaY ));
				
				//rows.put(Utils.arrondir(copie.getLayoutY()), ligne_destination);
			}
			
			ligne_grabbed = false;
			
			System.out.println("source : " + ligne_source.getYPosition());
			System.out.println("destination : " + ligne_destination.getYPosition());
			
			
		}
		else if(selectionMode.get().equals("Case")){

			if (source.toString().startsWith("Button")){
				
				ligne_source = rows.get(Utils.arrondir(((Button) source).getLayoutY()));
				
				// objet visible pendant le déplacement
				zero_.setVisible(false);
				copie = new Button();
				copie.setOnDragDetected(new EventHandler<Event>() {
	
					@Override
					public void handle(Event event) {
						detect2( event);
						
					}
				});
				copie.setText(zero_.getText());
				copie.setMinWidth(27.0);
				
				Chiffre ch = new Chiffre(case_hl, copie);
				copie.setLayoutX(case_hl.getLayoutX());
				copie.setLayoutY(case_hl.getLayoutY());
				rootPane.getChildren().add(copie);
				copie.setVisible(true);
				
				//liste_boutons.add(copie);
			
				
				// deplace sur la même ligne
				if (rows.containsKey(Utils.arrondir(copie.getLayoutY())) && 
				    rows.get(Utils.arrondir(copie.getLayoutY())).getContenu()
				                                                .stream()
				                                                .map(a -> ((Chiffre) a).getButton())
				                                                .filter(a ->  source.toString().equals(a.toString()))
				                                                .count() > 0 ){
					
					System.out.println("deplace sur la même ligne");
					ligne_destination = rows.get(Utils.arrondir(copie.getLayoutY()));
					System.out.println("-- " + ligne_destination.getContenu().size());
				}
				// deplace sur une autre ligne existante
				else if(rows.containsKey(Utils.arrondir(copie.getLayoutY()))){
//					
//					System.out.println(rows.get(Utils.arrondir(copie.getLayoutY())).getContenu()
//                            .stream()
//                            .map(a -> ((Chiffre) a).getButton())
//                            .filter(a -> source.toString().equals(a.toString()))
//                            .count());
//					
					System.out.println("deplace sur une autre ligne existante");
					ligne_destination = rows.get(Utils.arrondir(copie.getLayoutY()));
					ligne_destination.getContenu().add(ch);
					
					//////////////////
					//ligne_source.getContenu().remove(ch); // définir ligne_source
					/////////////////
					
					System.out.println("++ " + ligne_destination.getContenu().size());
				}
				// deplace sur une nouvelle ligne
				else {
					System.out.println("deplace sur une nouvelle ligne");
					ligne_destination = new Ligne();
					ligne_destination.setYPosition(Utils.arrondir(copie.getLayoutY()));
					ligne_destination.getContenu().add(ch);
					rows.put(Utils.arrondir(copie.getLayoutY()), ligne_destination);
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
	}
	
	
	
	@FXML
	public void over1(DragEvent e1){
		
		if(selectionMode.get().equals("Ligne")){			
			
			if (! ligne_grabbed){
				
				colonne_source = Utils.arrondir(e1.getSceneX());

				
				ligne_source = rows.get(Utils.arrondir(e1.getSceneY()));
				ligne_source.setYPosition(Utils.arrondir(e1.getSceneY()));
			    currentLine = ligne_source.getContenu();
			    ligne_grabbed = true;
			}
		}
		
		
		zero_.relocate(e1.getSceneX(), e1.getSceneY());
		zero_.toFront();
		
		if (source.toString().startsWith("Button")){
		    case_hl.relocate(Utils.arrondir(e1.getSceneX()) + 25, Utils.arrondir(e1.getSceneY()) + 25);
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
	
		//System.out.println("init");
		zero_.setVisible(true);
		zero_.relocate(-30.0, -30.0);
		case_hl.relocate(-30.0, -30.0);
		
		//liste_boutons = new ArrayList<>();
		lignes_h = new ArrayList<>();
		lignes_v = new ArrayList<>();
		
		clavier.getChildren().remove(zero);
		zero.setMinWidth(61.0);
		clavier.add(zero, 0, 3 , 2, 1);
		
		
		
		
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
	}
	

	
	

}
