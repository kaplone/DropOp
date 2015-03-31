package dropop;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.Node;
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

public class gui_controller implements Initializable {
	
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
	private RadioButton radio_element,
	                    radio_ligne,
	                    radio_bloc,
	                    radio_operation;
	
	private Object source;
	
	private Button copie;
	
	private Pane cursorPane;
	
	private StringProperty selectionMode = new SimpleStringProperty("element");
	
	private Ligne ligne;
	
	private ObservableMap<Integer, Ligne> rows = FXCollections.observableMap(new HashMap<Integer, Ligne>());
	
	//ArrayList<Button> liste_boutons;
	ArrayList<Pane> liste_panes;
	
	Line ligne_v;
	Line ligne_h;
	
	ArrayList<Line> lignes_v;
	ArrayList<Line> lignes_h;
	
	@FXML
	public void on_radio_select_action(Event e){
		
		selectionMode.set(e.getSource().toString().split("'")[1]);
		
	}

	@FXML
	public void detect(Event e0){
        Dragboard db = un.startDragAndDrop(TransferMode.MOVE);
        
        source = e0.getSource().toString();
        
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
        
        source = e0.getSource().toString();
        ((Button) e0.getSource()).setVisible(false);
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(un.getText());
        db.setContent(content);
        
        zero_.setText(((Button)e0.getSource()).getText());
        
        zero_.relocate(50.0, 100.0);
	}
	@FXML
	public void done(){
		
		if (source.toString().startsWith("Button")){
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
			
			if (rows.containsKey(Utils.arrondir(copie.getLayoutX()))){
				
				rows.get(Utils.arrondir(copie.getLayoutX())).getContenuLigne().add(ch);
			}
			else {
				ligne = new Ligne();
				ligne.getContenuLigne().add(ch);
				rows.put(Utils.arrondir(copie.getLayoutX()), ligne);
			}
		}
		else if (source.toString().startsWith("Pane")){
			cursorPane.toFront();
	        liste_panes.add(cursorPane);
			
		}
	}
	@FXML
	public void over1(DragEvent e1){
		zero_.relocate(e1.getSceneX(), e1.getSceneY());
		zero_.toFront();
		
		if (source.toString().startsWith("Button")){
		    case_hl.relocate(Utils.arrondir(e1.getSceneX()), Utils.arrondir(e1.getSceneY()));
		    zero_.setVisible(true);
		}
		else if (source.toString().startsWith("Pane")){
			cursorPane.relocate(Utils.arrondir(e1.getSceneX()), Utils.arrondir(e1.getSceneY()));
			
		    zero_.setVisible(false);
		}
		
		//zero_.relocate(50.0, 100.0);
	}
	

	@FXML
	public void detect_op(Event e2){
        Dragboard db = un.startDragAndDrop(TransferMode.MOVE);
        
        source = e2.getSource().toString();
        
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
	
		System.out.println("init");
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
