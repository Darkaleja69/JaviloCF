package packVista;

import javax.swing.JLabel;

public class Label extends JLabel{
	private int x;
	private int y;
	
	public Label(int pX,int pY) {
		x=pX;
		y=pY;
	}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
