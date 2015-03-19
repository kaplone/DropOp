package dropop;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
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
	private Button zero;
	@FXML
	private Rectangle case_hl;
	@FXML
	private Pane grille;
	@FXML
	private Pane rootPane;
	
	private Button copie;
	
	ArrayList<Button> liste_boutons;
	
	Line ligne_v;
	Line ligne_h;
	
	ArrayList<Line> lignes_v;
	ArrayList<Line> lignes_h;

	@FXML
	public void detect(Event e0){
		System.out.println("detect");
        Dragboard db = un.startDragAndDrop(TransferMode.MOVE);
        
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(un.getText());
        db.setContent(content);
        
        zero.setText(((Button)e0.getSource()).getText());
        
        zero.relocate(50.0, 100.0);
	}
	@FXML
	public void entre(){
		System.out.println("entre");
		grille.setOpacity(0.5);
		
		
	}
	@FXML
	public void done(){
		System.out.println("done");
		zero.setVisible(false);
		copie = new Button();
		copie.setText(zero.getText());
		copie.setLayoutX(case_hl.getLayoutX());
		copie.setLayoutY(case_hl.getLayoutY());
		rootPane.getChildren().add(copie);
		copie.setVisible(true);
		
		liste_boutons.add(copie);

		
	}
	@FXML
	public void over1(DragEvent e1){
		//System.out.println(e1.getSceneX() + ", " + e1.getSceneY());
		zero.relocate(e1.getSceneX(), e1.getSceneY());
		case_hl.relocate(Math.round(e1.getSceneX()) /25 * 25, Math.round(e1.getSceneY()) /25 * 25);
		zero.setVisible(true);
		
		//zero.relocate(50.0, 100.0);
	}
	@FXML
	public void dropped(){
		System.out.println("dropped");
		
		
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		System.out.println("init");
		zero.setVisible(true);
		zero.relocate(-30.0, -30.0);
		case_hl.relocate(-30.0, -30.0);
		
		liste_boutons = new ArrayList<>();
		lignes_h = new ArrayList<>();
		lignes_v = new ArrayList<>();
		
		
		
		
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
