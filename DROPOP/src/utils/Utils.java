package utils;

import dropop.Gui_controller;

public class Utils {
	
	private static int l = Gui_controller.getTailleQuadrillage();

	
	
	public static int arrondir(double d){
		return (int) (Math.round(d) /l * l - l);
	}
	
	public static int arrondirPlus(double d){
		return (int) (Math.round(d) /l * l + l);
	}
	
	public static int arrondirSimple(double d){
		return (int) (Math.round(d) /l * l);
	}
	
	public static int arrondirVersPosition(double d){	
		return (int) (Math.round(d) /l) - 1;
	}
}
