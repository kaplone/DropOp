package dropop;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

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
	
	private String source;
	
	private Button copie;
	
	private Pane cursorPane;
	
	ArrayList<Button> liste_boutons;
	ArrayList<Pane> liste_panes;
	
	Line ligne_v;
	Line ligne_h;
	
	ArrayList<Line> lignes_v;
	ArrayList<Line> lignes_h;

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
	public void done(){
		
		if (source.toString().startsWith("Button")){
			zero_.setVisible(false);
			copie = new Button();
			copie.setText(zero_.getText());
			copie.setMinWidth(27.0);
			copie.setLayoutX(case_hl.getLayoutX());
			copie.setLayoutY(case_hl.getLayoutY());
			rootPane.getChildren().add(copie);
			copie.setVisible(true);
			
			liste_boutons.add(copie);
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
		zero_.relocate(e1.getSceneX(), e1.getSceneY());
		zero_.toFront();
		
		if (source.toString().startsWith("Button")){
		    case_hl.relocate(Math.round(e1.getSceneX()) /25 * 25, Math.round(e1.getSceneY()) /25 * 25);
		    zero_.setVisible(true);
		}
		else if (source.toString().startsWith("Pane")){
			cursorPane.relocate(Math.round(e1.getSceneX()) /25 * 25, Math.round(e1.getSceneY()) /25 * 25);
			
		    //zero_.setVisible(true);
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
		
		liste_boutons = new ArrayList<>();
		lignes_h = new ArrayList<>();
		lignes_v = new ArrayList<>();
		
		clavier.getChildren().remove(zero);
//		GridPane.setRowIndex(zero, 3);
//	    GridPane.setColumnIndex(zero, 0);
//		GridPane.setColumnSpan(zero, 2);
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
