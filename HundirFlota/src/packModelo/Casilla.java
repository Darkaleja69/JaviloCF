package packModelo;

public class Casilla {
	private boolean tieneBomba;
	private char fila;
	private int columna;
	private Barco barco;
	
	public Casilla(char pFila,int pColumna) {
		fila=pFila;
		columna=pColumna;
	}
	
	public char getFila() {
		return fila;
	}
	public int getColumna() 
	{
		return columna;
	}
	
	public boolean estaTocada() {
		return tieneBomba;
	}
	
	// public void bombardear()
	
	public void colocarBarco(Barco pBarco) {
		
		barco=pBarco;
	}
	
	public Barco getBarco() {
		return barco;
	}
}
