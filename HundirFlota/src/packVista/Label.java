package packVista;

import javax.swing.JLabel;

public class Label extends JLabel{
	private int x;
	private int y;
	
	public Label(int pX,int pY) {
		super();
		x=pX;
		y=pY;
	}
	public int getCoordX() {
		return x;
	}
	
	public int getCoordY() {
		return y;
	}
}
