package packVista;

import javax.swing.JLabel;

public class Label extends JLabel{
	private int x;
	private int y;
	private boolean esJugador;
	
	public Label(int pX,int pY, boolean pEsJugador) {
		super();
		x = pX;
		y = pY;
		esJugador = pEsJugador;
	}
	public int getCoordX() 
	{
		return x;
	}
	
	public int getCoordY() 
	{
		return y;
	}
	
	public boolean esJugador()
	{
		return esJugador;
	}
}
