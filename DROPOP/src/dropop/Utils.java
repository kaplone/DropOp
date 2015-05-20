package dropop;

public class Utils {
	
	private static int l = 25;
	private static int h = 25;
	
	
	public static int arrondir(double d){
		return (int) (Math.round(d) /25 * 25);
	}
}
