package dropop;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class Positionnable {
	
	final IntegerProperty topX = new SimpleIntegerProperty();
	final IntegerProperty topY = new SimpleIntegerProperty();

	public int getTopX(){
		return topX.get();
	}
	public IntegerProperty topXproperty(){
		return topX;
	}
	
	public int getTopY(){
		return topY.get();
	}
	public IntegerProperty topYproperty(){
		return topY;
	}
	
	public void setTopX(int x){
		topX.set(x);
	}
	
	public void setTopY(int y){
		topY.set(y);
	}

}
