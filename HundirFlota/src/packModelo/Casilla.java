package packModelo;

public class Casilla {
	private boolean tieneBomba;
	private int fila;
	private int columna;
	private Barco barco;
	
	public Casilla(int pFila,int pColumna) {
		fila=pFila;
		columna=pColumna;
		barco = null;
		tieneBomba = false;
	}
	
	public int getFila() {
		return fila;
	}
	public int getColumna() 
	{
		return columna;
	}
	
	public boolean estaTocada() {
		return tieneBomba;
	}
	
	public void bombardear() 
	{
		tieneBomba = true;
		if(tieneBarco()) 
		{
			this.barco.recibirImpacto();
		}
	}
	
	public void colocarBarco(Barco pBarco) {
		
		barco = pBarco;
	}
	
	public Barco getBarco() {
		return barco;
	}
	
	public boolean tieneBarco()
	{
		return (!(this.barco==null));
	}
	
	
}
