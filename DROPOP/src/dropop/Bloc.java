package dropop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bloc extends Positionnable{
	
	final private ObservableList<Ligne> contenuBloc = FXCollections.observableArrayList();

	public ObservableList<Ligne> getContenuBloc() {
		return contenuBloc;
	}

}
